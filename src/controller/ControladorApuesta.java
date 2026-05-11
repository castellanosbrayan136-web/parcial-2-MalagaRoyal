package controller;

//@autor: Brayan C

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import model.Apuesta;
import model.ApuestaDAO;
import view.ScreenManager;
import view.VistaHistorialCasino;


public class ControladorApuesta {
    private final VistaHistorialCasino vistaHistorialCasino;
    private final ApuestaDAO apuestaDAO;

    public ControladorApuesta(VistaHistorialCasino vistaHistorialCasino, ApuestaDAO apuestaDAO) {
        this.vistaHistorialCasino = vistaHistorialCasino;
        this.apuestaDAO = apuestaDAO;
        cargarDatos();
        funcionBotonXHistorial();
    }
    
    public void cargarDatos() {
        calcularGananciaNeta();
        llenarTabla();
    }
    
    public void funcionBotonXHistorial() {
        vistaHistorialCasino.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScreenManager.cerrarHistorialApuestas(vistaHistorialCasino);
            }
        });
    }

    public void llenarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) vistaHistorialCasino.getTablaHistorial().getModel();
        
        modeloTabla.setRowCount(0);
        
        Object[] fila = new Object[6];
        
        int contador = 0;
        
        for (Apuesta apuesta: apuestaDAO.retornarApuestas()) {
            if (contador >= 100) {
                break;
            }
            
            fila[0] = apuesta.getNombreUsuario();
            fila[1] = apuesta.getNumeroApostado();
            fila[2] = apuesta.getNumeroGanador();
            fila[3] = apuesta.getMontoApostado();
            fila[4] = apuesta.getGananciaPerdida();
            fila[5] = apuesta.getHoraApuesta();
            
            modeloTabla.addRow(fila);
            contador++;
        } 
    }

    public void calcularGananciaNeta() {
        double gananciaNeta = 0;
        
        for (Apuesta apuesta : apuestaDAO.retornarApuestas()) {
            gananciaNeta += apuesta.getGananciaPerdida();
        }
        
        vistaHistorialCasino.setJblGananciaCasino(String.valueOf(gananciaNeta));
    }
    

    
}
