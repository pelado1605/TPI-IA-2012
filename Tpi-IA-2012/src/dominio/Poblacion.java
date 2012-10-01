/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.crypto.Mac;

/**
 *
 * @author Ruben
 */
public class Poblacion {

    private ArrayList<Individuo> poblado;
    private Random suerte = new Random();
    private float aptitud = 0f;
    private float probMutacion;

    /**
     *
     * @param poblado
     */
    public Poblacion(ArrayList<Individuo> poblado, float probMutacion) {
        this.poblado = poblado;
        this.probMutacion = probMutacion;
        evaluarAptitud();
    }

    public ArrayList<Individuo> seleccionElitista(int cantidad) {
        ArrayList<Individuo> ordenados = this.getPoblado();
        Collections.sort(ordenados);
        ArrayList<Individuo> seleccionados = new ArrayList<>(cantidad);
        for (int i = 0; i < cantidad; i++) {
            seleccionados.add(ordenados.get(i));
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionRuleta(int cantidad) {

        ArrayList<Integer> rangos = new ArrayList<>();
        int acum = 0;
        for (Individuo ind : poblado) {
            acum += Math.round((ind.getAptitud() / aptitud) * poblado.size() * 10);
            rangos.add(acum);
        }
        ArrayList<Individuo> seleccionados = new ArrayList<>(cantidad);
        
        for (int i = 0; i < cantidad; i++) {
            int nroAleatorio = suerte.nextInt(poblado.size() * 10) + 1;
            int valor = Collections.binarySearch(rangos, nroAleatorio);
            if (valor < 0) {
                valor = Math.abs(valor + 1);
            }
            if (valor >= rangos.size()) {
                valor = rangos.size()-1; //parche puesto por German, ver: 1.0.
            }
            seleccionados.add(poblado.get(valor));
        }    
        return seleccionados;
    }

    public Individuo[] seleccionRanking(int cantidad, float rMin) {
        return null;
    }

    public Individuo[] seleccionContCopiasEsp(int cantidad) {
        return null;
    }

    public Individuo[] seleccionXTorneo(int cantidad, int grupos) {
        return null;
    }

    public Individuo[] cruzarPoblacion(int cantidad) {
        return null;
    }

    public Individuo[] mutarPoblacion(int cantidad) {
        return null;
    }

    public void evaluarAptitud() {
        float sumatoria = 0f;
        for (Individuo individuo : poblado) {
            sumatoria += individuo.getAptitud();
        }
        this.aptitud = sumatoria;
    }

    public ArrayList<Individuo> getPoblado() {
        return poblado;
    }

    public float getAptitud() {
        return aptitud;
    }

    public float getProbMutacion() {
        return probMutacion;
    }
}
