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

    /**
     * Permite cargar la informacion desde un archivo de texto plano, del
     * listado de estudiantes con sus respectivas materias inscritas.
     *
     * @param rutaArchivo Cadena de caracteres con la direccion de la carpeta
     * que se desea consultar sus archivos
     * @return ArrayList con los mensajes de error generados durante la
     * validacion de los datos de los estudiantes y sus materias
     * @throws CargarArchivoException Excepcion que es dada cuando no se
     * encuentra el archivo en la ruta especificada
     */
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
                cedulaEstudiante = datosEstudiante[0];
                nombreEstudiante = datosEstudiante[1];
                codigoMateria = datosEstudiante[2];
                nombreMateria = datosEstudiante[3];

                listaErrores = this.validarDatosArchivoPorEstudiante(cedulaEstudiante, nombreEstudiante,
                        codigoMateria, nombreMateria);
            }

        } catch (Exception e) {
            throw new CargarArchivoException("No se encontro la ruta especidicada");
        }
        return listaErrores;

    }

    /**
     * Se encarga de validar si el estudiante ya se encuentra registrado en el
     * sistema asi como las materias que este tiene inscritas, evitando de esta
     * forma informacion duplicada
     *
     * @param cedula Codigo de identificacion unico de cada estudiante
     * @param nombreEstudiante nombre que posee el estudiante a inscribir
     * @param codigo Codigo de identificacion unico de cada materia
     * @param nombreMateria nombre que posee la materia inscrita por el
     * estudiante
     * @return ArrayList con los mensajes de error generados durante la
     * validacion de los datos de los estudiantes y sus materias
     */
    private ArrayList validarDatosArchivoPorEstudiante(String cedula, String nombreEstudiante,
            String codigo, String nombreMateria) {
        ArrayList<String> mensajesValidacion = new ArrayList();
        boolean validarCedula;
        boolean validarMateria;
        validarCedula = this.validarCedEstudiante(cedula);

        if (validarCedula == false) {
            mensajesValidacion.add("El estudiante " + nombreEstudiante + " Ya se encuentra "
                    + "registrado en el sistema");
        } else {
            this.agregarEstudiante(cedula, nombreEstudiante);
        }

        validarMateria = this.validarMateriaPorEstudiante(codigo, cedula);

        if (validarMateria == false) {
            mensajesValidacion.add("El estudiante " + nombreEstudiante + " Ya posee "
                    + "la materia" + nombreMateria + " inscrita");
        } else {
            this.agregarMateriaPorEstudiante(cedula, codigo, nombreMateria);
        }

        return mensajesValidacion;
    }

    /**
     * Se encarga de contar por estudiante el total de materias que este posee
     * inscritas
     *
     * @return ArrayList con el listado de cada estudiante y su respectivo total
     * de materias inscritas
     */
    public ArrayList totalMateriasPorEstudiante() {
        ArrayList<Integer> totalMateriasPorEstudiante = new ArrayList<>();
        estudiantes.forEach((estudiante)
                -> totalMateriasPorEstudiante.add(
                        estudiante.cantidadMateriasInscritas()));
        return totalMateriasPorEstudiante;

    }

    /**
     * Se encarga de validar si el estudiante ya se encuentra registrado en el
     * sistema a traves de la comparacion de cedulas de cada uno de ellos
     *
     * @param cedula Codigo de identificacion unico de cada estudiante
     * @return boolean indicando con True si el estudiante no se encuentra
     * registrado y False si se encuentra coincidencia alguna con algun registro
     * previo
     */
    private boolean validarCedEstudiante(String cedula) {
        boolean existEstudiante = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                existEstudiante = true;
            }
        }

        return existEstudiante;
    }

    /**
     * Se encarga de validar si la materia ya se encuentra inscrita por el
     * estudiante a traves de la verificacion del codigo de esta
     *
     * @param codigo Codigo de identificacion unico de cada materia
     * @param cedula Codigo de identificacion unico de cada estudiante
     * @return boolean indicando con True si la materia no se encuentra inscrita
     * por el estudiante y False si se encuentra coincidencia alguna con algun
     * registro previo
     */
    private boolean validarMateriaPorEstudiante(String codigoMateria, String cedula) {
        Estudiante estudiante = obtenerEstudiantPorCedula(cedula);
        return estudiante.validarCodMateria(codigoMateria);

    }

    /**
     * Realiza el llamado a la clase estudiante para que este inscriba la
     * materia respectiva
     *
     * @param cedula Codigo de identificacion unico de cada estudiante
     * @param codigo Codigo de identificacion unico de cada materia
     * @param nombreMateria nombre que posee la materia inscrita por el
     * estudiante
     * @return void
     */
    private void agregarMateriaPorEstudiante(String cedula, String codigoMateria, String nombreMateria) {
        Estudiante estudiante = obtenerEstudiantPorCedula(cedula);
        estudiante.inscribirMateria(codigoMateria, nombreMateria);
    }

    /**
     * Metodo que devuelve un estudiante buscado por cedula
     *
     * @param cedula Codigo de identificacion unico de cada estudiante
     * @return Estudiante clase estudiante la cual presento coincidencia por su
     * cedula
     */
    private Estudiante obtenerEstudiantPorCedula(String cedula) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().endsWith(cedula)) {
                return estudiante;
            }
        }
        return null;
    }

    /**
     * Realiza la creacion de un nuevo objeto de clase Estudiante el
     * cual es agregado a la lista de estudiantes de la clase Control
     *
     * @param cedula Codigo de identificacion unico de cada estudiante
     * @param nombre nombre que posee el estudiante a inscribir
     * @return void
     */
    private void agregarEstudiante(String cedula, String nombre) {
        this.estudiantes.add(new Estudiante(cedula, nombre));
    }

}
