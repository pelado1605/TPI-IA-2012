/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;
import utilidad.Archivador;

/**
 *
 * @author Ruben
 */
public class TpiIA2012 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] mIngresados = {10000,10000,10000,10000,10000,10000,10000,10000};
        Generaciones generacion = new Generaciones(0.2f, 0.5f, mIngresados);
        generacion.ejecutar();
       
    }
}
