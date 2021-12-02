/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import modelo.Estudiante;
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

    private ArrayList<Estudiante> estudiantes;
    private CargarArchivo cargaArchivo;

    public Control() {
        this.estudiantes = new ArrayList<>();
        this.cargaArchivo = new CargarArchivo();
    }

    public void cargarArchivo(String rutaArchivo) throws CargarArchivoException {

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

    private ArrayList validarDatosArchivo(String info) {
        return null;
    }

    public ArrayList totalMateriasPorEstudiante() {
        ArrayList<Integer> totalMateriasPorEstudiante = new ArrayList<>();
        estudiantes.forEach((estudiante)
                -> totalMateriasPorEstudiante.add(
                        estudiante.cantidadMateriasInscritas()));
        return totalMateriasPorEstudiante;

    }

    private boolean validarCedEstudiante(String cedula) {
        boolean  existEstudiante = false;
        for (Estudiante estudiante : estudiantes) {
            if(estudiante.getCedula().equals(cedula)){
                existEstudiante = true;
            }
        }
        
       return existEstudiante; 
    }

    private boolean validarMateriaPorEstudiante(String codigoMateria,String cedula) {
        Estudiante estudiante = obtenerEstudiantPorCedula(cedula);
        return estudiante.validarCodMateria(codigoMateria);
        
    }

    private void agregarMateria(String cedula, Materia materia) {

    }
    
    private Estudiante obtenerEstudiantPorCedula(String cedula)
    {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().endsWith(cedula)) {
                return  estudiante;
            }           
        }
        return  null;
    }

}
