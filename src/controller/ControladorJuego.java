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
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Apuesta;
import model.ApuestaDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.ScreenManager;
import view.VistaJuego;

/**
 *
 * @author UIS
 */

public class ControladorJuego implements ActionListener{
    private final Usuario usuario;
    private final VistaJuego vistaJuego;
    private final UsuarioDAO usuarioDAO;
    private final ApuestaDAO apuestaDAO;

    public ControladorJuego(Usuario usuario, VistaJuego vistaJuego, UsuarioDAO usuarioDAO, ApuestaDAO apuestaDAO) {
        this.usuario = usuario;
        this.vistaJuego = vistaJuego;
        this.usuarioDAO = usuarioDAO;
        this.apuestaDAO = apuestaDAO;
        activarBotones();
        iniciarDatos();
    }
    
    public void iniciarDatos() {
        vistaJuego.setJblSaldo(String.valueOf(usuario.getSaldo()));
        vistaJuego.setJblUsuario(usuario.getNombre());
        vistaJuego.setJblResultado("", Color.black);
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
        vistaJuego.setJblResultado("", Color.yellow);
        
        int numeroGanador = generarNumeroRandom();
        int numeroApostado = vistaJuego.getSpnNumeroSeleccionado();
        double montoApostado = vistaJuego.getDblMonto();
        
        if (montoApostado == -1.0) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto a apostar valido.");
            return;
        }
        
        if (montoApostado > usuario.getSaldo()){
            JOptionPane.showMessageDialog(vistaJuego, "Fondos insuficientes.");
            return;
        }
        
        animarRuleta(numeroGanador, numeroApostado, montoApostado);
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
                    vistaJuego.setJblResultado("GANASTE!!!", Color.yellow);
                    
                    double nuevoSaldo = saldoActual + montoApostado*35;
                    
                    usuario.setSaldo(nuevoSaldo);
                    usuarioDAO.actualizarSaldo(nuevoSaldo, usuario);
                    
                    vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));
                    
                    double perdidaCasino = montoApostado * -35;
                    
                    Apuesta apuesta = new Apuesta(
                            usuario.getNombre(), 
                            numeroApostado, 
                            numeroGanador, 
                            montoApostado,
                            perdidaCasino, 
                            LocalDateTime.now());
                    
                    apuestaDAO.registrarApuesta(apuesta);
                } else {
                    vistaJuego.setJblResultado("PERDISTE!!", Color.RED);
                    double nuevoSaldo = saldoActual - montoApostado;

                    usuario.setSaldo(nuevoSaldo);
                    usuarioDAO.actualizarSaldo(nuevoSaldo, usuario);

                    vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));
                    
                    double gananciaCasino = montoApostado;
                    
                    Apuesta apuesta = new Apuesta(
                            usuario.getNombre(),
                            numeroApostado,
                            numeroGanador,
                            montoApostado, 
                            gananciaCasino, 
                            LocalDateTime.now());
                    
                    apuestaDAO.registrarApuesta(apuesta);
                }
            }
        });
        timer.start();
    }
    
    
    public void recargar() {  
        String txtRecarga = JOptionPane.showInputDialog("Ingresa el monto a recargar.");

        if (txtRecarga == null) {
            return;
        }
        
        double recarga = 0;
        
        try {
            recarga = Double.parseDouble(txtRecarga);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un numero valido!.");
        }
        

        if (recarga > 0) {
            double nuevoSaldo = usuario.getSaldo() + recarga;

            vistaJuego.setJblSaldo(String.valueOf(nuevoSaldo));

            usuario.setSaldo(nuevoSaldo);

            usuarioDAO.actualizarSaldo((nuevoSaldo), usuario);

            System.out.println(usuario.getSaldo());
        } else {
            JOptionPane.showMessageDialog(vistaJuego, "Ingresa un monto valido de recarga.");
        }
    }
}
