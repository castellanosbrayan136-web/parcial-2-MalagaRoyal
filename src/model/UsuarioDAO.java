/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UIS
 */
public class UsuarioDAO {
    public static List<Usuario> usuarios = new ArrayList<>();
    
    public boolean crearUsuario(Usuario usuarioACrear) {
        
        
        if (usuarioACrear.getNombre().trim().isEmpty() || usuarioACrear.getContraseña().trim().isEmpty() || usuarioACrear.getCedula().trim().isEmpty() || usuarioACrear.getTelefono().trim().isEmpty() || usuarioACrear.getDireccion().trim().isEmpty()) {
            return false;
        }
        
        usuarios.add(usuarioACrear);
        return true;
    }
    
    public boolean verficarNombre(Usuario usuarioACrear) {
        for (Usuario usuario: usuarios) {
            if (usuario.getNombre().equals(usuarioACrear.getNombre())) {
                return false;
            }
        }
        return true;
    }
    
    public Usuario verificarInicioDeSesion(String nombreUsuario,String contraseña) {
        for (Usuario usuario: usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                return usuario;
            }
        }
        return null;
    }
    
    public boolean actualizarSaldo(double saldo, Usuario usuarioARecargar) {
        for (Usuario usuario: usuarios) {
            if (usuario.equals(usuarioARecargar)) {
                usuario.setSaldo(saldo);
                return true;
            }
        }
        return false;
    }
}
