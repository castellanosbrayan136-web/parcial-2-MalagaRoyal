/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControladorApuesta;
import controller.ControladorJuego;
import controller.ControladorCrearCuenta;
import controller.ControladorIniciarSesion;
import controller.ControladorLogin;
import javax.swing.JFrame;
import model.ApuestaDAO;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author UIS
 */
public class ScreenManager {
    public static ApuestaDAO apuestaDAO = new ApuestaDAO();
    public static UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public static void abrirLogin() {
        VistaLogin vistaLogin = new VistaLogin();
        configurarVentana(vistaLogin);
        
        new ControladorLogin(vistaLogin);
        
        vistaLogin.setVisible(true);
    }
    
    public static void cerrarLogin(VistaLogin vistaLogin) {
        vistaLogin.dispose();
    }
    
    public static void abrirCrearCuenta(VistaLogin vistaLogin) {
        cerrarLogin(vistaLogin);
        
        VistaCrearCuenta vistaCrearCuenta = new VistaCrearCuenta();
        configurarVentana(vistaLogin);
        
        new ControladorCrearCuenta(usuarioDAO, vistaCrearCuenta);
        
        vistaCrearCuenta.setVisible(true);
    }
    
    public static void cerrarCrearCuenta(VistaCrearCuenta vistaCrearCuenta) {
        vistaCrearCuenta.dispose();
        abrirLogin();
    }
    
    public static void abrirIniciarSesion(VistaLogin vistaLogin) {
        cerrarLogin(vistaLogin);
        
        VistaIniciarSesion vistaIniciarSesion = new VistaIniciarSesion();
        configurarVentana(vistaLogin);
        
        new ControladorIniciarSesion(vistaIniciarSesion, usuarioDAO);
        
        vistaIniciarSesion.setVisible(true);
    }
    
    public static void cerrarIniciarSesion(VistaIniciarSesion vistaIniciarSesion) {
        vistaIniciarSesion.dispose();
    }
    
    public static void abrirJuego(VistaIniciarSesion vistaIniciarSesion, Usuario usuario) {
        vistaIniciarSesion.setVisible(false);
        
        VistaJuego vistaJuego = new VistaJuego();
        configurarVentana(vistaJuego);
        
        new ControladorJuego(usuario, vistaJuego, usuarioDAO, apuestaDAO);
        
        vistaJuego.setVisible(true);
    }
    
    public static void cerrarJuego(VistaJuego vistaJuego) {
        vistaJuego.dispose();
        abrirLogin();
    }
    
    public static void abrirHistorialApuestas(VistaIniciarSesion vistaIniciarSesion) {
        cerrarIniciarSesion(vistaIniciarSesion);
        
        VistaHistorialCasino vistaHistorial = new VistaHistorialCasino();
        configurarVentana(vistaHistorial);
        
        new ControladorApuesta(vistaHistorial, apuestaDAO);
        
        vistaHistorial.setVisible(true);
    }
    
    public static void cerrarHistorialApuestas(VistaHistorialCasino vistaHistorial) {
        vistaHistorial.dispose();
        abrirLogin();
    }
    
    private static void configurarVentana(JFrame vista) {
        vista.setSize(800,600);
        vista.setLocationRelativeTo(null);
    }
}
