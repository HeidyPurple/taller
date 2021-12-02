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

    public ArrayList cargarArchivo(String rutaArchivo) throws CargarArchivoException {

        String listaArchivo;
        ArrayList<String> listaErrores = new ArrayList();

        try {
            listaArchivo = this.cargaArchivo.cargarArchivo(rutaArchivo);

            String[] listaPalabra = listaArchivo.split("\\;");
            String[] datosEstudiante;
            String cedulaEstudiante;
            String nombreEstudiante;
            String codigoMateria;
            String nombreMateria;
            
            for (int i = 0; i < listaPalabra.length; i++) {
                
                datosEstudiante = listaPalabra[i].split("\\,");
                cedulaEstudiante= datosEstudiante[0];
                nombreEstudiante= datosEstudiante[1];
                codigoMateria= datosEstudiante[2];
                nombreMateria= datosEstudiante[3];
                
                listaErrores=this.validarDatosArchivoPorEstudiante(cedulaEstudiante, nombreEstudiante,
                        codigoMateria, nombreMateria);
            }
            

        } catch (Exception e) {
            throw new CargarArchivoException("No se encontro la ruta especidicada");
        }
        return listaErrores;
        
    }

    private ArrayList validarDatosArchivoPorEstudiante(String cedula,String nombreEstudiante, 
            String codigo, String nombreMateria) {
        ArrayList <String> mensajesValidacion = new ArrayList();
        boolean validarCedula;
        boolean validarMateria;
        validarCedula= this.validarCedEstudiante(cedula);
        
        if (validarCedula == false){
            mensajesValidacion.add("El estudiante "+nombreEstudiante+" Ya se encuentra "
                    + "registrado en el sistema");
        }else{
            this.agregarEstudiante(cedula, nombreEstudiante);
        }
        
        validarMateria = this.validarMateriaPorEstudiante(codigo, cedula);
        
        if (validarMateria == false){
            mensajesValidacion.add("El estudiante "+nombreEstudiante+" Ya posee "
                    + "la materia"+nombreMateria+" inscrita");
        }else{
            this.agregarMateriaPorEstudiante(cedula, codigo, nombreMateria);
        }
                
        return mensajesValidacion;
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

    private void agregarMateriaPorEstudiante(String cedula, String codigoMateria,String nombreMateria) {
        Estudiante estudiante = obtenerEstudiantPorCedula(cedula);
        estudiante.inscribirMateria(codigoMateria,nombreMateria);
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
    
    private void agregarEstudiante(String cedula, String nombre)
    {
        this.estudiantes.add(new Estudiante(cedula,nombre));
    }

}
