/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
    private ControladorVistaIniciarSesion controladorIniciarSesion;
    VistaJuego vistaJuego;
    UsuarioDAO usuarioDAO;

    public ControladorJuego(ControladorVistaIniciarSesion controladorIniciarSesion, VistaJuego vistaJuego, UsuarioDAO usuarioDAO) {
        this.controladorIniciarSesion = controladorIniciarSesion;
        this.vistaJuego = vistaJuego;
        this.usuarioDAO = usuarioDAO;
        activarBotones();
    }
    
    public void activarBotones() {
        vistaJuego.getBtnRecargar().addActionListener(this);
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
    }
    
    public int generarNumeroRandom() {
        return (int) (Math.random() * 37) ;
    }
    
    public void apostar() {
        int numeroRandom = generarNumeroRandom();
        int numeroDeSpin = vistaJuego.getSpnNumeroSeleccionado();
        
        if (numeroDeSpin == numeroRandom) {
            System.out.println("Ganaste");
        }
        
        
        
        }
    
    
    
    public void recargar() {
        Usuario usuario = controladorIniciarSesion.iniciarSesion();    
        try {
            String txtRecarga = JOptionPane.showInputDialog("Ingresa el monto a recargar.");
            double recarga = Double.parseDouble(txtRecarga);
            vistaJuego.setJblSaldo(String.valueOf(usuario.getSaldo() + recarga));
            usuarioDAO.actualizarSaldo((usuario.getSaldo() + recarga), usuario);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un numero valido!.");
        }
        
        
    }
    
    
}
