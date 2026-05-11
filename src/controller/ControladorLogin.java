/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ScreenManager;
import view.VistaLogin;

/**
 *
 * @author UIS
 */

public class ControladorLogin implements ActionListener{
    private final VistaLogin vistaLogin;

    public ControladorLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
        activarBotones();
    }
    
    public void activarBotones() {
        vistaLogin.getBtnCrearCuenta().addActionListener(this);
        vistaLogin.getBtnIniciarSesion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.getBtnIniciarSesion()) {
            ScreenManager.abrirIniciarSesion(vistaLogin);
        } else if (e.getSource() == vistaLogin.getBtnCrearCuenta()) {
            ScreenManager.abrirCrearCuenta(vistaLogin);
        }
    }
    
}
