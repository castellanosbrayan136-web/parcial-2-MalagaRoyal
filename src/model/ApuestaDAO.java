/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public final class ApuestaDAO {

    private List<Apuesta> apuestas;
    private final Gson gson;
    private final String ruta = "Apuestas.json";

    public ApuestaDAO() throws IOException {
        gson = new GsonBuilder().setPrettyPrinting().create();
        apuestas = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos() throws IOException {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            Files.write(Paths.get(ruta), "[]".getBytes());
            apuestas = new ArrayList<>();
            return;
        }
        String json = new String(Files.readAllBytes(Paths.get(ruta)));
        Type listType = new TypeToken<ArrayList<Apuesta>>(){}.getType();
        apuestas = gson.fromJson(json, listType);
        if (apuestas == null) {
            apuestas = new ArrayList<>();
        }
    }

    public void guardarDatos() throws IOException {
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(apuestas, writer);
        }    }

    public void registrarApuesta(Apuesta apuesta) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        apuesta.setFecha(dtf.format(LocalDateTime.now()));
        apuestas.add(apuesta);
        guardarDatos();
    }
    
    
    public List<Apuesta> obtenerApuestas() {
        return apuestas;
    }
    
   
    public List<Apuesta> obtenerApuestasPorUsuario(String nombreUsuario) {
        List<Apuesta> resultado = new ArrayList<>();
        for (Apuesta a : apuestas) {
            if (a.getNombreUsuario().equals(nombreUsuario)) {
                resultado.add(a);
            }
        }
        return resultado;
    }
    
    
    public int getGananciaNetaCasino() {
        int total = 0;
        for (Apuesta a : apuestas) {
            total += a.getGananciaPerdida() * -1; 
        }
        return total;
    }
}
    

