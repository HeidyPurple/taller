/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author arroy
 * @version 20211201
 * @since 1.0
 * @version 1.0.0
 */
public class Estudiante {
    private String cedula;
    private String nombre;
    private ArrayList<Materia> listaMaterias;
    
    public Estudiante(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        listaMaterias = new ArrayList<>();
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
    
    public boolean validarCodMateria(String codigo){
        boolean  existeMateria = false;
        for (Materia materia : listaMaterias) {
            if(materia.getCodigo().equals(codigo)){
                existeMateria = true;
            }
        }
        
       return existeMateria; 
    }
    
    public int cantidadMateriasInscritas(){
        return this.listaMaterias.size();
    }
 
    public void inscribirMateria(Materia materia){
        if(materia!= null){
            this.listaMaterias.add(materia);
        }
        
    }
    
}
