/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import persistencia.CargarArchivo;

/**
 *
 * @author jeffr
 */
public class prueba {
    
    public static void main(String[] args) {
        CargarArchivo archivo = new CargarArchivo();
        try {
            String x=archivo.cargarArchivo("C:\\Users\\jeffr\\Documents\\NetBeansProjects\\tallerF\\Estudiantes.txt");
            System.out.println(x);
            String[] listaPalabra = x.split("\\;");
            for (int i = 0; i < listaPalabra.length; i++) {
                System.out.println(listaPalabra[i]);
            }
            
        } catch (Exception e) {
            System.out.println("no");
        }
        
    }
    
}
