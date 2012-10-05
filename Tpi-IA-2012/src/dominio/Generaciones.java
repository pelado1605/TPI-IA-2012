/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class Generaciones {

    public static final int CANTIDAD_POBLACION = 1000;
    public static final int CANTIDAD_ITERACIONES = 1000;
    private int cSeleccion;
    private int cCruza;
    private int cMutacion;
    private ArrayList<Poblacion> generaciones = new ArrayList();
    private ArrayList<Integer> materiales;

    public Generaciones(float pSeleccion, float pCruza, int cantPoblacion, int cantIteraciones,
            ArrayList<Integer> materiales) {
        this.cSeleccion = convertPorcentACant(pSeleccion);
        this.cCruza = convertPorcentACant(pCruza);
        this.cMutacion = cantPoblacion - cCruza - cSeleccion;
        this.materiales = materiales;
    };
    
    private int convertPorcentACant(float porciento) {
        int cantidad = (int) (porciento * CANTIDAD_POBLACION);
        return cantidad;
    }

    public int getcSeleccion() {
        return cSeleccion;
    }

    public int getcCruza() {
        return cCruza;
    }

    public int getcMutacion() {
        return cMutacion;
    }
}
