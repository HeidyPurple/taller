/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import modelo.Materia;
import persistencia.CargarArchivo;
import persistencia.CargarArchivoException;

/**
 *
 * @author jeffr
 * @version 20211201
 * @since 1.0
 * @version 1.0.0
 */
public class Control {
     
    CargarArchivo cargaArchivo = new CargarArchivo();
     
    public void cargarArchivo(String rutaArchivo) throws CargarArchivoException{
        
        String listaArchivo;
        
        try {
            listaArchivo = this.cargaArchivo.cargarArchivo(rutaArchivo);
            
            String[] listaPalabra = listaArchivo.split("\\;");
            
        for (int i = 0; i < listaPalabra.length; i++) {
            
        }
        
        } catch (Exception e) {
            throw new CargarArchivoException("No se encontro la ruta especidicada");
        }
        
    }
    
    public ArrayList totalMateriasPorEstudiantes(){
      return null;  
    }
    
    private boolean validarCedEstudiante(String cedula){
        return false;
    }
    
    private  boolean validarCodMateria(String codigo){
        return false;
    }
    
    private ArrayList validarDatosArchivo(String info){
        return null;
    }
    
    private void agregarMateria(String cedula, Materia materia){
        
    }
    
    
}
