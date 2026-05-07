/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControladorJuego;
import controller.ControladorRuleta;
import controller.ControladorVistaCrearCuenta;
import controller.ControladorVistaIniciarSesion;
import controller.ControladorVistaLogin;
import model.UsuarioDAO;

/**
 *
 * @author UIS
 */
public class ScreenManager {
    public static void abrirLogin() {
        VistaLogin vistaLogin = new VistaLogin();
        
        new ControladorVistaLogin(vistaLogin);
        
        vistaLogin.setVisible(true);
    }
    
    public static void cerrarLogin(VistaLogin vistaLogin) {
        vistaLogin.setVisible(false);
    }
    
    public static void abrirCrearCuenta(VistaLogin vistaLogin) {
        cerrarLogin(vistaLogin);
        VistaCrearCuenta vistaCrearCuenta = new VistaCrearCuenta();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        new ControladorVistaCrearCuenta(usuarioDAO, vistaCrearCuenta);
        
        vistaCrearCuenta.setVisible(true);
    }
    
    public static void cerrarCrearCuenta(VistaCrearCuenta vistaCrearCuenta) {
        vistaCrearCuenta.setVisible(false);
        abrirLogin();
    }
    
    public static void abrirIniciarSesion(VistaLogin vistaLogin) {
        cerrarLogin(vistaLogin);
        VistaIniciarSesion vistaIniciarSesion = new VistaIniciarSesion();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        new ControladorVistaIniciarSesion(vistaIniciarSesion, usuarioDAO);
        
        vistaIniciarSesion.setVisible(true);
    }
    
    public static void cerrarIniciarSesion(VistaIniciarSesion vistaIniciarSesion) {
        vistaIniciarSesion.setVisible(false);
        abrirLogin();
    }
    
    public static void abrirJuego(VistaIniciarSesion vistaIniciarSesion) {
        vistaIniciarSesion.setVisible(false);
        VistaJuego vistaJuego = new VistaJuego();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ControladorVistaIniciarSesion controladorIniciarSesion = new ControladorVistaIniciarSesion(vistaIniciarSesion, usuarioDAO);
        
        new ControladorJuego(controladorIniciarSesion, vistaJuego, usuarioDAO);
        
        vistaJuego.setVisible(true);
    }
    
    public static void cerrarJuego(VistaJuego vistaJuego) {
        vistaJuego.setVisible(false);
        abrirLogin();
    }
    
    public static void abrirRuleta(VistaJuego vistaJuego) {
        vistaJuego.setVisible(false);
        VistaIniciarSesion vistaIniciarSesion = new VistaIniciarSesion();
        VistaRuleta vistaRuleta = new VistaRuleta();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ControladorVistaIniciarSesion controladorIniciarSesion = new ControladorVistaIniciarSesion(vistaIniciarSesion, usuarioDAO);
        
        new ControladorRuleta(controladorIniciarSesion, vistaRuleta, usuarioDAO);
        
        vistaRuleta.setVisible(true);
    }
    
    public static void cerrarRuleta(VistaRuleta vistaRuleta,VistaIniciarSesion vistaIniciarSesion) {
        vistaRuleta.setVisible(false);
        abrirJuego(vistaIniciarSesion);
    }
}
