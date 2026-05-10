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
import view.VistaCrearCuenta;

/**
 *
 * @author UIS
 */
public class ControladorVistaCrearCuenta implements ActionListener{
    private UsuarioDAO usuarioDAO;
    private VistaCrearCuenta vistaCrearCuenta;

    public ControladorVistaCrearCuenta(UsuarioDAO usuarioDAO, VistaCrearCuenta vistaCrearCuenta) {
        this.usuarioDAO = usuarioDAO;
        this.vistaCrearCuenta = vistaCrearCuenta;
        activarBotones();
    }
    
    public void activarBotones() {
        vistaCrearCuenta.getBtnCrearCuenta().addActionListener(this);
        funcionBotonX();
    }
    
    public void funcionBotonX() {
        vistaCrearCuenta.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScreenManager.cerrarCrearCuenta(vistaCrearCuenta);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCrearCuenta.getBtnCrearCuenta()) {
            crearCuenta();
        }
    }
    
    public void crearCuenta() {
        if (!usuarioDAO.verficarNombre(leerDatos())) {
            vistaCrearCuenta.setJblMensajeNombreUsuario("Nombre de usuario ya en uso intenta con otro.");
            return;
        }
        
        if (usuarioDAO.crearUsuario(leerDatos())) {
            JOptionPane.showMessageDialog(vistaCrearCuenta, "Usuario creado con exito.");
            ScreenManager.cerrarCrearCuenta(vistaCrearCuenta);
        } else {
            JOptionPane.showMessageDialog(vistaCrearCuenta, "Completa los datos.");
        }
    }
    
    public Usuario leerDatos() {
        return new Usuario(vistaCrearCuenta.getTxtNombreUsuario(),
                vistaCrearCuenta.getTxtContraseña(),
                vistaCrearCuenta.getTxtCedula(),
                vistaCrearCuenta.getTxtTelefono(),
                vistaCrearCuenta.getTxtDireccion());
    }
    
}
