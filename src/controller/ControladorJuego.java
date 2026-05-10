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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Apuesta;
import model.ApuestaDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.ScreenManager;
import view.VistaHistorialCasino;
import view.VistaJuego;

/**
 *
 * @author UIS
 */

public class ControladorJuego implements ActionListener{
    private Usuario usuario;
    private VistaJuego vistaJuego;
    private UsuarioDAO usuarioDAO;
    private ApuestaDAO apuestaDAO;

    public ControladorJuego(Usuario usuario, VistaJuego vistaJuego, UsuarioDAO usuarioDAO, ApuestaDAO apuestaDAO) {
        this.usuario = usuario;
        this.vistaJuego = vistaJuego;
        this.usuarioDAO = usuarioDAO;
        this.apuestaDAO = apuestaDAO;
        activarBotones();
        iniciarDatos();
        vistaJuego.setJblResultado("RESULTADO", Color.black);
    }
    
    public void iniciarDatos() {
        vistaJuego.setJblSaldo(String.valueOf(usuario.getSaldo()));
        vistaJuego.setJblUsuario(usuario.getNombre());
    }
    
    public void activarBotones() {
        vistaJuego.getBtnRecargar().addActionListener(this);
        vistaJuego.getBtnApostar().addActionListener(this);
        funcionBotonXJuego();
    }
    
    public void funcionBotonXJuego() {
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
        
        vistaJuego.setJblResultado("", Color.yellow);
        
        if (vistaJuego.getTxtMonto().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto a apostar.");
            return;
        }
        int numeroRandom = generarNumeroRandom();
        int numeroDeSpin = vistaJuego.getSpnNumeroSeleccionado();
        
        if (numeroDeSpin == -1) {
            JOptionPane.showMessageDialog(vistaJuego, "Numero de apuesta invalido, (0 - 36).");
            return;
        }
        
        double saldoActual = usuario.getSaldo();
        double montoApostado;
        
        try {
            montoApostado = Double.parseDouble(vistaJuego.getTxtMonto());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto valido a apostar.");
            return;
        }
        
        if (montoApostado > saldoActual){
            JOptionPane.showMessageDialog(vistaJuego, "Fondos insuficientes.");
            return;
        }
        
        animarRuleta(numeroRandom, numeroDeSpin, montoApostado);
        
        
    }
    
    
    public void animarRuleta(int numeroGanador, int numeroApostado, double montoApostado) {
        Timer timer = new Timer(100, null);
        
        final int[] vueltas = {0};
        
        timer.addActionListener(e -> {
            int numeroTemporal = generarNumeroRandom();
            
            vistaJuego.setJblNumeroGenerado(String.valueOf(numeroTemporal));
            
            vueltas[0]++;
            
            if (vueltas[0] == 30) {
                timer.stop();
                
                vistaJuego.setJblNumeroGenerado(String.valueOf(numeroGanador));
                
                double saldoActual = usuario.getSaldo();
                
                if (numeroApostado == numeroGanador) {
                    double nuevoSaldo = saldoActual + montoApostado*35;
                    
                    usuario.setSaldo(nuevoSaldo);
                    usuarioDAO.actualizarSaldo(nuevoSaldo, usuario);

                    vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));
                    
                    double perdida = montoApostado * -35;
                    
                    Apuesta apuesta = new Apuesta(usuario.getNombre(), numeroApostado, numeroGanador, montoApostado, perdida, LocalDateTime.now());
                    
                    apuestaDAO.registrarApuesta(apuesta);

                    vistaJuego.setJblResultado("GANASTE!!!", Color.yellow);
                }else {
                    double nuevoSaldo2 = saldoActual - montoApostado;

                    usuario.setSaldo(nuevoSaldo2);

                    usuarioDAO.actualizarSaldo(nuevoSaldo2, usuario);

                    vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo2));
                    
                    double ganancia = montoApostado;
                    
                    Apuesta apuesta = new Apuesta(usuario.getNombre(), numeroApostado, numeroGanador, montoApostado, ganancia, LocalDateTime.now());
                    
                    apuestaDAO.registrarApuesta(apuesta);

                    vistaJuego.setJblResultado("PERDISTE!!", Color.RED);
                }
            }
        });
        timer.start();
    }
    
    
    public void recargar() {  
        try {
            String txtRecarga = JOptionPane.showInputDialog("Ingresa el monto a recargar.");
            
            if (txtRecarga == null) {
                return;
            }
            
            double recarga = Double.parseDouble(txtRecarga);
            
            if (recarga > 0) {
                double nuevoSaldo = usuario.getSaldo() + recarga;
                
                vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));
                
                usuario.setSaldo(nuevoSaldo);
                
                usuarioDAO.actualizarSaldo((nuevoSaldo), usuario);
                
                System.out.println(usuario.getSaldo());
            } else {
                JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto valido de recarga.");
            }
            
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un numero valido!.");
        }
    }
    
    
}
