/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;
import dominio.Individuo;
import dominio.Poblacion;
import java.util.Collections;

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
        int[] mIngresados = {70000, 10405, 31080, 51009, 71002,
            31010, 80102, 40100};
        Generaciones generacion = new Generaciones(0f, 0f, mIngresados);
        generacion.generarPoblacionInicial(Generaciones.CANTIDAD_POBLACION);
        Poblacion poblacion = generacion.getGeneraciones().get(0);
        float aptitudPoblacion = poblacion.evaluarAptitud(mIngresados);
        Collections.sort(poblacion.getPoblado());
        System.out.print(Generaciones.gokuFase4.getAptitud());
        System.out.println();
        int cont = 0;
        for (Individuo ind : generacion.getGeneraciones().get(0).getPoblado()) {
            System.out.println(ind.mostrarProductos());
            System.out.println(ind.getAptitud());
            System.out.println(ind.getUtilidad());
            System.out.println(ind.factibilidad(mIngresados));
            System.out.println(cont);
            System.out.println();
            cont++;
        }


    }
}
