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
import view.VistaIniciarSesion;

/**
 *
 * @author UIS
 */
public class ControladorVistaIniciarSesion implements ActionListener{
    private VistaIniciarSesion vistaIniciarSesion;
    private UsuarioDAO usuarioDAO;

    public ControladorVistaIniciarSesion(VistaIniciarSesion vistaIniciarSesion, UsuarioDAO usuarioDAO) {
        this.vistaIniciarSesion = vistaIniciarSesion;
        this.usuarioDAO = usuarioDAO;
        activarBotones();
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
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaIniciarSesion.getBtnIniciarSesion()) {
            abrirUsuario();
        }
    }
    
    public Usuario iniciarSesion() {
        Usuario usuario = usuarioDAO.verificarInicioDeSesion(leerUsuario(), leerContraseña());
        
        if (usuario == null) {
            vistaIniciarSesion.setJblMensajeContraseña("Usuario o contraseña incorrecta.");
            return null;
        }
        
        return usuario;
        
    }
    
    public void abrirUsuario() {
        Usuario usuario = iniciarSesion();
        
        if (usuario != null) {
            ScreenManager.abrirJuego(vistaIniciarSesion, usuario);
        }
    }
    
    public String leerUsuario() {
        return vistaIniciarSesion.getTxtUsuario();
    }
    
    public String leerContraseña() {
        return vistaIniciarSesion.getTxtContraseña();
    }
    
    
}
