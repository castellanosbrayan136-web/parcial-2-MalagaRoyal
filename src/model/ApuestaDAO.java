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

    public ApuestaDAO() {
        
        gson = new GsonBuilder().setPrettyPrinting().create();
        apuestas = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos() {
        try {
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
        } catch (IOException ex) {
            apuestas = new ArrayList<>();
        }
        
    }

    public void guardarDatos() {
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(apuestas, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public void registrarApuesta(Apuesta apuesta) {
        apuestas.add(apuesta);
        guardarDatos();
    }
    
    
    public List<Apuesta> retornarApuestas() {
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
    

