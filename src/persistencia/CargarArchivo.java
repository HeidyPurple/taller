/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Clase encargada de buscar los archivos .txt de la carpeta, cuya direccion es
 * especificada por el usuario, asi como la encargada de leer el contenido de
 * los archivos
 *
 * @author JEFFREY LOAIZA
 * @version 20211201
 * @since 1.0
 * @version 1.0.0
 */
public class CargarArchivo {

    public CargarArchivo() {
    }


    /**
     * Permite cargar la informacion desde un archivo de texto plano, del
     * listado de numero a los cuales se les desea calcular su promedio y
     * desviacion estandar.
     *
     * @param rutaArchivo Ruta de acceso a la ubicacion del archivo de texto,
     * debe ser diferente de null.
     * @return Texto Cadena de caracteres que contiene el archivo .txt
     * @throws CargarArchivoException Excepcion que es dada cuando no se
     * encuentra el archivo en la ruta especificada
     */
    public String cargarArchivo(String rutaArchivo) throws CargarArchivoException {
        File archivo = null;
        FileReader lectorArchivo = null;
        BufferedReader lector = null;
        String texto="";

        try {
            archivo = new File(rutaArchivo);
            lectorArchivo = new FileReader(archivo);
            lector = new BufferedReader(lectorArchivo);

            String linea;
            
            while ((linea = lector.readLine()) != null) {
                texto= texto+linea;
            }
            return texto;

        } catch (Exception eRuta) {
            throw new CargarArchivoException("No se encontro el archivo de texto en la ruta especidicada");
        } finally {
            try {
                if (null != lectorArchivo) {
                    lectorArchivo.close();
                }
            } catch (Exception eRuta2) {
                throw new CargarArchivoException("No se encontro el archivo de texto en la ruta especidicada");
            }
        }
    }
}