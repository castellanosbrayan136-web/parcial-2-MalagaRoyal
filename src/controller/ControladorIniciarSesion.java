/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.Usuario;
import model.UsuarioDAO;
import view.ScreenManager;
import view.VistaIniciarSesion;

/**
 *
 * @author UIS
 */
public class ControladorIniciarSesion implements ActionListener{
    private final VistaIniciarSesion vistaIniciarSesion;
    private final UsuarioDAO usuarioDAO;

    public ControladorIniciarSesion(VistaIniciarSesion vistaIniciarSesion, UsuarioDAO usuarioDAO) {
        this.vistaIniciarSesion = vistaIniciarSesion;
        this.usuarioDAO = usuarioDAO;
        activarBotones();
        limpiarMensajes();
    }
    
    public void limpiarMensajes() {
        vistaIniciarSesion.setJblMensajeContraseña("");
    }
    
    public void activarBotones() {
        vistaIniciarSesion.getBtnIniciarSesion().addActionListener(this);
        funcionBotonX();
    }
    
    public void funcionBotonX() {
        vistaIniciarSesion.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScreenManager.cerrarIniciarSesion(vistaIniciarSesion);
                ScreenManager.abrirLogin();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaIniciarSesion.getBtnIniciarSesion()) {
            abrirUsuario();
        }
    }
    
    public void abrirUsuario() {
        Usuario usuario = usuarioDAO.verificarInicioDeSesion(vistaIniciarSesion.getTxtUsuario(), vistaIniciarSesion.getTxtContraseña());
        
        if (usuario == null) {
            vistaIniciarSesion.setJblMensajeContraseña("Usuario o contraseña incorrecta.");
            return;
        }
        
        if (usuario.getRol().equals("ADMIN")) {
            ScreenManager.abrirHistorialApuestas(vistaIniciarSesion);
        } else {
            ScreenManager.abrirJuego(vistaIniciarSesion, usuario);
        }
    }
}
