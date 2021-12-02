/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import modelo.Estudiante;
import modelo.Materia;

/**
 *
 * @author jeffr
 * @version 20211201
 * @since 1.0
 * @version 1.0.0
 */
public class Control {

    private ArrayList<Estudiante> estudiantes;

    public Control() {
        estudiantes = new  ArrayList<>();
    }
    
    

    public void cargarArchivo(String rutaArchivo) {

    }

    public ArrayList totalMateriasPorEstudiante() {
        ArrayList<Integer> totalMateriasPorEstudiante = new ArrayList<>();
        estudiantes.forEach((estudiante) ->
                totalMateriasPorEstudiante.add(
                        estudiante.cantidadMateriasInscritas()));
        return totalMateriasPorEstudiante;

    }

    private boolean validarCedEstudiante(String cedula) {
        return false;
    }

    private boolean validarCodMateria(String codigo) {
        return false;
    }

    private ArrayList validarDatosArchivo(String info) {
        return null;
    }

    private void agregarMateria(String cedula, Materia materia) {

    }

}
