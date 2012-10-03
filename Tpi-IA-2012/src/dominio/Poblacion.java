/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Collections;
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
            int nroAleatorio = suerte.nextInt(Collections.max(rangos) + 1);
            int valor = Collections.binarySearch(rangos, nroAleatorio);
            if (valor < 0) {
                valor = Math.abs(valor + 1);
            }
            if (valor >= rangos.size()) {
                valor = rangos.size() - 1; //parche puesto por German, ver: 1.0.
            }
            seleccionados.add(poblado.get(valor));
        }
        return seleccionados;
    }

    /**
     * Acepta hasta un 50% de la poblacion para ser seleccionada
     *
     * @param cantidad
     * @param rMin
     * @return
     */
    public ArrayList<Individuo> seleccionRanking(int cantidad, float rMin) {
        //try catch para que rmin sea un valor entre 0 y 1
        ArrayList<Individuo> ordenados = this.getPoblado();
        Collections.sort(ordenados);
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        ArrayList<Float> valores = new ArrayList<>();
        for (int i = 0; i < ordenados.size(); i++) {
            float valor = rMin + 2 * ((ordenados.size() - i) * (1 - rMin))
                    / (ordenados.size() - 1);
            valores.add(valor);
        }
        int cont = 0;
        int i = 0;
        boolean bandera = true;
        ArrayList<Float> rangos = new ArrayList<>();
        while ((cont < cantidad) && bandera) {
            float valor = valores.get(i);
            int parteEntera = (int) valor;
            float parteDecimal = valor - parteEntera;
            if (parteEntera > 0) {
                seleccionados.add(ordenados.get(i));
                cont++;
                if (parteEntera > 1) {
                    seleccionados.add(ordenados.get(i));
                    cont++;
                }
            } else {
                bandera = false;
            }
            i++;
            rangos.add(parteDecimal);
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionContCopiasEsp(int cantidad) {
        float aptitudPromedio = getAptitudPromedio();
        ArrayList<Individuo> seleccionados = new ArrayList();
        int i=0,cont=0;
        while (cont<cantidad){
            //agregar try-catch por si "i" supera el tamaño de la lista.
            float valor = poblado.get(i).getAptitud() / aptitudPromedio;
            int entero = (int) valor;
            for (int j = 0; j < entero; j++) {
                seleccionados.add(poblado.get(i));
                cont++;
            }
            i++;
        }
            return seleccionados;
        }
    
    public ArrayList<Individuo> seleccionXTorneo(int cantidad, int grupos) {
        int cantXGrupo = cantidad/grupos;
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        
        return seleccionados;
    }

    public Individuo[] cruzarPoblacion(int cantidad) {
        return null;
    }

    public Individuo[] mutarPoblacion(int cantidad) {
        return null;
    }

    public float evaluarAptitud() {
        float sumatoria = 0f;
        for (Individuo individuo : poblado) {
            sumatoria += individuo.getAptitud();
        }
        this.aptitud = sumatoria;
        return sumatoria;
    }

    public float getAptitudPromedio() {
        float aptitudPromedio = 0;
        aptitudPromedio = evaluarAptitud() / getPoblado().size();
        return aptitudPromedio;
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
