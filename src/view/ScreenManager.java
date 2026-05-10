/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControladorApuesta;
import controller.ControladorJuego;
import controller.ControladorVistaCrearCuenta;
import controller.ControladorVistaIniciarSesion;
import controller.ControladorVistaLogin;
import model.ApuestaDAO;
import model.Usuario;
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
    
    public static void abrirJuego(VistaIniciarSesion vistaIniciarSesion, Usuario usuario) {
        vistaIniciarSesion.setVisible(false);
        VistaJuego vistaJuego = new VistaJuego();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ApuestaDAO apuestaDAO = new ApuestaDAO();
        
        new ControladorJuego(usuario, vistaJuego, usuarioDAO, apuestaDAO);
        
        vistaJuego.setVisible(true);
    }
    
    public static void cerrarJuego(VistaJuego vistaJuego) {
        vistaJuego.dispose();
        abrirLogin();
    }
    
    public static void abrirHistorialApuestas(VistaIniciarSesion vistaIniciarSesion) {
        vistaIniciarSesion.setVisible(false);
        VistaHistorialCasino vistaHistorial = new VistaHistorialCasino();
        ApuestaDAO apuestaDAO = new ApuestaDAO();
        ControladorApuesta controladorApuesta = new ControladorApuesta(vistaHistorial, apuestaDAO);
        
        vistaHistorial.setVisible(true);
    }
    
    public static void cerrarHistorialApuestas(VistaHistorialCasino vistaHistorial) {
        vistaHistorial.dispose();
        abrirLogin();
    }
}
