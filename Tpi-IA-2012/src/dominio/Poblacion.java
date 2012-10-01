/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ruben
 */
public class Poblacion {

    private ArrayList<Individuo> poblado;
    private Random suerte = new Random();
    private float aptitud = 0f;
    private float probMutacion;

    public Poblacion(float probMutacion) {

        this.probMutacion = probMutacion;
    }

    /**
     * Constructor para la creación de la población inicial.
     * @param poblado 
     */
    public Poblacion(ArrayList<Individuo> poblado) {
        this.poblado = poblado;
    }
    
    
    
    
}
