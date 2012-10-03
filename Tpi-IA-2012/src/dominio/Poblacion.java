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

    public ArrayList<Individuo> seleccionElitista(int cantidad, ArrayList<Individuo> entrada) {
        ArrayList<Individuo> ordenados = entrada;
        Collections.sort(ordenados);
        ArrayList<Individuo> seleccionados = new ArrayList<>(cantidad);
        for (int i = 0; i < cantidad; i++) {
            seleccionados.add(ordenados.get(i));
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionElitista(int cantidad) {
        return seleccionElitista(cantidad, this.getPoblado());
    }

    public ArrayList<Individuo> seleccionRuleta(int cantidad, ArrayList<Individuo> entrada) {

        ArrayList<Integer> rangos = new ArrayList<>();
        int acum = 0;
        float aptitudEntrada = evaluarAptitud(entrada);
        for (Individuo ind : entrada) {
            acum += Math.round((ind.getAptitud() / aptitudEntrada) * entrada.size() * 10);
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
            seleccionados.add(entrada.get(valor));
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionRuleta(int cantidad) {
        return seleccionRuleta(cantidad, this.poblado);
    }

    /**
     * Acepta hasta un 50% de la poblacion para ser seleccionada
     *
     * @param cantidad
     * @param rMin
     * @return
     */
    public ArrayList<Individuo> seleccionRanking(int cantidad, float rMin, ArrayList<Individuo> entrada) {
        //try catch para que rmin sea un valor entre 0 y 1
        ArrayList<Individuo> ordenados = entrada;
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

    public ArrayList<Individuo> seleccionRanking(int cantidad, float rMin) {
        return seleccionRanking(cantidad, rMin, this.poblado);
    }

    public ArrayList<Individuo> seleccionContCopiasEsp(int cantidad, ArrayList<Individuo> entrada) {
        float aptitudPromedio = evaluarAptitud(entrada) / entrada.size();
        ArrayList<Individuo> seleccionados = new ArrayList();
        int i = 0, cont = 0;
        while (cont < cantidad) {
            //agregar try-catch por si "i" supera el tamaño de la lista.
            float valor = entrada.get(i).getAptitud() / aptitudPromedio;
            int entero = (int) valor;
            for (int j = 0; j < entero; j++) {
                seleccionados.add(entrada.get(i));
                cont++;
            }
            i++;
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionContCopiasEsp(int cantidad) {
        return seleccionContCopiasEsp(cantidad, this.poblado);
    }

    public ArrayList<Individuo> seleccionXTorneo(int cantidad, int cantGrupos, ArrayList<Individuo> entrada) {
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        ArrayList<ArrayList<Individuo>> grupos = getSubGrupos(cantidad, cantGrupos, entrada);
        for (int i = 0; i < cantGrupos; i++) {
            ArrayList<Individuo> individuos = grupos.get(i);
            for (Individuo individuo : individuos) {
                seleccionados.add(individuo);
            }
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionXTorneo(int cantidad, int cantGrupos) {
        return seleccionXTorneo(cantidad, cantGrupos, poblado);
    }

    public ArrayList<ArrayList<Individuo>> getSubGrupos(int cantidadTotalInd, int cantGrupos, ArrayList<Individuo> grupo) {
        ArrayList<ArrayList<Individuo>> seleccionados = new ArrayList<>();
        ArrayList<Individuo> copia = grupo;
        int indivXGrupo = cantidadTotalInd / cantGrupos;

        for (int i = 0; i < cantGrupos; i++) {
            int cont = 0;
            ArrayList<Individuo> lista = new ArrayList<>();
            while (cont < indivXGrupo) {
                int aleatorio1 = suerte.nextInt(copia.size());
                lista.add(copia.get(aleatorio1));
                copia.remove(aleatorio1);
                cont++;
            }
            seleccionados.add(lista);
        }

        if (cantidadTotalInd % cantGrupos != 0 && copia.size() >= cantidadTotalInd % cantGrupos) {
            int restantes = cantidadTotalInd % cantGrupos;
            for (int i = 0; i < restantes; i++) {
                seleccionados.get(i).add(copia.get(0));
                copia.remove(0);
            }
        }
        return seleccionados;
    }

    public ArrayList<Individuo> getSubGrupos(int cantidad, ArrayList<Individuo> grupo) {
        return getSubGrupos(cantidad, 1, grupo).get(0);
    }

    public ArrayList<Individuo> cruzarPoblacion(int cantidad) {
        ArrayList<Individuo> cruzados = new ArrayList<>();
        while (cruzados.size() < cantidad) {
            ArrayList<Individuo> aCruzarse = getSubGrupos(2, poblado);
            cruzados.addAll(aCruzarse.get(0).cruza(suerte.nextInt(3), aCruzarse.get(1)));
        }
        if (cruzados.size() > cantidad) {
            cruzados.remove(cruzados.size() - 1);
        }
        return cruzados;
    }

    public Individuo[] mutarPoblacion(int cantidad) {
        return null;
    }

    public float evaluarAptitud() {
        aptitud = evaluarAptitud(poblado);
        return aptitud;
    }

    public float evaluarAptitud(ArrayList<Individuo> entrada) {
        float sumatoria = 0f;
        for (Individuo individuo : entrada) {
            sumatoria += individuo.getAptitud();
        }
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

    public Poblacion() {
    }

    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>(60);

        lista.add(10);
        lista.add(14);
        lista.add(4);
        lista.add(50);
        lista.add(136);
        lista.add(23);
        lista.remove(3);
        System.out.println(lista.get(3));
        lista.remove(3);
        System.out.println(lista.get(3));
        System.out.println(lista);
    }
}
