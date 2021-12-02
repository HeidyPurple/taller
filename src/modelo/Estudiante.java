/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author arroy
 * @version 1
 */
public class Estudiante {
    private String cedula;
    private String nombre;
    private ArrayList listaEstudiantes;
    
    public Estudiante(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }
    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public ArrayList validarCodMateria(String codigo){
        return null;
    }
    
    public ArrayList materiasInscritas(){
    return null;
}
 
    public void inscribirMateria(Materia materia){
        
    }
    
}
