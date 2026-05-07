/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author UIS
 */
public class Usuario {
    private String nombre;
    private String contraseña;
    private String cedula;
    private String telefono;
    private String direccion;
    private double saldo;

    public Usuario(String nombre, String contraseña, String cedula, String telefono, String direccion) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.saldo = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        if (contraseña != null) {
            this.contraseña = contraseña;
        }
    }

    public void setCedula(String cedula) {
        if (cedula != null) {
            this.cedula = cedula;
        }
    }

    public void setTelefono(String telefono) {
        if (telefono != null) {
            this.telefono = telefono;
        }
    }

    public void setDireccion(String direccion) {
        if (direccion != null) {
            this.direccion = direccion;
        }
    }

    public void setSaldo(double saldo) {
        if (saldo > 0) {
            this.saldo = saldo;
        }
    }
    
    
}
