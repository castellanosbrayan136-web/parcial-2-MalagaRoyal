/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author USUARIO
 */

public class Apuesta {
    private String nombreUsuario;
    private int numeroApostado;
    private int numeroGanador;
    private double montoApostado;
    private double gananciaPerdida;
    private LocalDateTime horaApuesta;

    public Apuesta() {
    }

    public Apuesta(String nombreUsuario, int numeroApostado, int numeroGanador, double montoApostado, double gananciaPerdida, LocalDateTime horaApuesta) {
        this.nombreUsuario = nombreUsuario;
        this.numeroApostado = numeroApostado;
        this.numeroGanador = numeroGanador;
        this.montoApostado = montoApostado;
        this.gananciaPerdida = gananciaPerdida;
        this.horaApuesta = horaApuesta;
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

    public double getMontoApostado() {
        return montoApostado;
    }

    public void setMontoApodtado(double montoApodtado) {
        this.montoApostado = montoApodtado;
    }

    public double getGananciaPerdida() {
        return gananciaPerdida;
    }

    public void setGananciaPerdida(double gananciaPerdida) {
        this.gananciaPerdida = gananciaPerdida;
    }

    public LocalDateTime getHoraApuesta() {
        return horaApuesta;
    }

    public void setHoraApuesta(LocalDateTime horaApuesta) {
        this.horaApuesta = horaApuesta;
    }

    @Override
    public String toString() {
        return "Apuesta{" + "nombreUsuario=" + nombreUsuario + ", numeroApostado=" + numeroApostado + ", numeroGanador=" + numeroGanador + ", montoApodtado=" + montoApostado + ", gananciaPerdida=" + gananciaPerdida + '}';
    }
}
