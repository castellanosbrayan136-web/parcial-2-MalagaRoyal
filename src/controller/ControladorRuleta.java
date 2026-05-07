/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.VistaRuleta;

/**
 *
 * @author UIS
 */
public class ControladorRuleta {
    ControladorVistaIniciarSesion controladorInciarSesion;
    VistaRuleta vistaRuleta;
    UsuarioDAO usuarioDAO;

    public ControladorRuleta(ControladorVistaIniciarSesion controladorInciarSesion, VistaRuleta vistaRuleta, UsuarioDAO usuarioDAO) {
        this.controladorInciarSesion = controladorInciarSesion;
        this.vistaRuleta = vistaRuleta;
        this.usuarioDAO = usuarioDAO;
    }
    
    public void apostar() {
        Usuario usuario = controladorInciarSesion.iniciarSesion();
        
        if (vistaRuleta.getTxtMontoApostar() > usuario.getSaldo()) {
            JOptionPane.showMessageDialog(vistaRuleta, "Saldo insuficiente");
        }
        
        
    }
    
    public void leerMontoApuesta() {
        
    }
}
