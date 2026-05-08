/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.ScreenManager;
import view.VistaJuego;

/**
 *
 * @author UIS
 */
public class ControladorJuego implements ActionListener{
    Usuario usuario;
    VistaJuego vistaJuego;
    UsuarioDAO usuarioDAO;

    public ControladorJuego(Usuario usuario, VistaJuego vistaJuego, UsuarioDAO usuarioDAO) {
        this.usuario = usuario;
        this.vistaJuego = vistaJuego;
        this.usuarioDAO = usuarioDAO;
        activarBotones();
        iniciarDatos();
    }
    
    public void iniciarDatos() {
        vistaJuego.setJblSaldo(String.valueOf(usuario.getSaldo()));
        vistaJuego.setJblUsuario(usuario.getNombre());
    }
    
    public void activarBotones() {
        vistaJuego.getBtnRecargar().addActionListener(this);
        vistaJuego.getBtnApostar().addActionListener(this);
        funcionBotonX();
    }
    
    public void funcionBotonX() {
        vistaJuego.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScreenManager.cerrarJuego(vistaJuego);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaJuego.getBtnRecargar()) {
             recargar();
        } 
        if (e.getSource() == vistaJuego.getBtnApostar()){
            apostar();
        }
    }
    
    public int generarNumeroRandom() {
        return (int) (Math.random() * 37) ;
    }
    
    public void apostar() {
        
        if (vistaJuego.getTxtMonto().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto a apostar.");
            return;
        }
        int numeroRandom = generarNumeroRandom();
        int numeroDeSpin = vistaJuego.getSpnNumeroSeleccionado();
        
        double saldoActual = usuario.getSaldo();
        double montoApostado;
        
        try {
            montoApostado = Double.parseDouble(vistaJuego.getTxtMonto());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto valido a apostar.");
            return;
        }
        
        vistaJuego.setJblNumeroGenerado(String.valueOf(numeroRandom));
        
        if (montoApostado > saldoActual){
            JOptionPane.showMessageDialog(vistaJuego, "Fondos insuficientes.");
            return;
        }
        
        if (numeroDeSpin == numeroRandom) {
            double nuevoSaldo = saldoActual + montoApostado*35;
            usuario.setSaldo(nuevoSaldo);
            
            vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));
            
            vistaJuego.setJblResultado("GANASTE!!!", Color.yellow);
        }else{
            double nuevoSaldo = saldoActual - montoApostado;
            usuario.setSaldo(nuevoSaldo);
            
            vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));
            
            vistaJuego.setJblResultado("PERDISTE!!", Color.RED);
        }
    }
    
    
    
    public void recargar() {  
        try {
            String txtRecarga = JOptionPane.showInputDialog("Ingresa el monto a recargar.");
            
            if (txtRecarga == null) {
                return;
            }
            
            double recarga = Double.parseDouble(txtRecarga);
            
            if (recarga > 0) {
                vistaJuego.setJblSaldo(String.valueOf(usuario.getSaldo() + recarga));
                usuarioDAO.actualizarSaldo((usuario.getSaldo() + recarga), usuario);
            } else {
                JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto valido de recarga.");
            }
            
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un numero valido!.");
        }
    }
    
    
}
