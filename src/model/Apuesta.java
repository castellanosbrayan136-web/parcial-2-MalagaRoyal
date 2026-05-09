/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USUARIO
 */
public class Apuesta {
    private String nombreUsuario;
    private int numeroApostado;
    private int numeroGanador;
    private int montoApodtado;
    private int gananciaPerdida;

    public Apuesta() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(int numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public void setNumeroGanador(int numeroGanador) {
        this.numeroGanador = numeroGanador;
    }

    public int getMontoApodtado() {
        return montoApodtado;
    }

    public void setMontoApodtado(int montoApodtado) {
        this.montoApodtado = montoApodtado;
    }

    public int getGananciaPerdida() {
        return gananciaPerdida;
    }

    public void setGananciaPerdida(int gananciaPerdida) {
        this.gananciaPerdida = gananciaPerdida;
    }

    @Override
    public String toString() {
        return "Apuesta{" + "nombreUsuario=" + nombreUsuario + ", numeroApostado=" + numeroApostado + ", numeroGanador=" + numeroGanador + ", montoApodtado=" + montoApodtado + ", gananciaPerdida=" + gananciaPerdida + '}';
    }

    void setFecha(String format) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
