/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;

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
        int[] mIngresados = {1000,1000,1000,1000,1000,1000,1000,1000};
        Generaciones generacion = new Generaciones(0.2f, 0.6f, mIngresados);
        generacion.ejecutar();
    }
}
