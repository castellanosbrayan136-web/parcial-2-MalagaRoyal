/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UIS
 */
public class UsuarioDAO {
    private Gson gson;
    private String ruta = "Usuarios.json";
    public List<Usuario> usuarios;

    public UsuarioDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.usuarios = cargarDatos();
    }
    
        public List<Usuario> cargarDatos() {
        File archivo = new File(ruta);
        
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        
        try (Reader reader = new FileReader(archivo)) {
            Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> lista = gson.fromJson(reader , tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }
    
    public void guardarDatos() {
        try (Writer writer = new FileWriter(ruta)) {
            gson.toJson(usuarios , writer);
        } catch (IOException ex) {
            System.err.println("Error al guardar datos:" + ex.getMessage());
        }
    }
    
    public boolean crearUsuario(Usuario usuarioACrear) {
        if (usuarioACrear.getNombre().trim().isEmpty() || usuarioACrear.getContraseña().trim().isEmpty() || usuarioACrear.getCedula().trim().isEmpty() || usuarioACrear.getTelefono().trim().isEmpty() || usuarioACrear.getDireccion().trim().isEmpty()) {
            return false;
        }
        
        usuarios.add(usuarioACrear);
        guardarDatos();
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
            if (usuario.getNombre().equals(usuarioARecargar.getNombre())) {
                usuario.setSaldo(saldo);
                guardarDatos();
                return true;
            }
        }
        return false;
    }
}
