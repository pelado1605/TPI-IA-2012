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
public class Poblacion implements Cloneable {

    public static final int SELECCION_ELITISTA = 0;
    public static final int SELECCION_POR_RULETA = 1;
    public static final int SELECCION_RANKING = 2;
    public static final int SELECCION_POR_COPIAS_ESPERADAS = 3;
    public static final int SELECCION_POR_TORNEO = 4;
    
    private static int cont_selecElitista = 0;
    private static int cont_selecRuleta = 0;
    private static int cont_selecRanking = 0;
    private static int cont_selecContCopias = 0;
    
    private ArrayList<Individuo> poblado;
    private Random suerte = new Random();
    private float aptitud = 0f;
    private float probMutacion;
    private float rMin;

    /**
     *
     * @param poblado
     */
    public Poblacion(ArrayList<Individuo> poblado, float probMutacion, float rMin) {
        this.poblado = poblado;
        this.probMutacion = probMutacion;
        this.rMin = rMin;
        getAptitudPoblacion();
    }

    public ArrayList<Individuo> seleccionElitista(int cantidad, ArrayList<Individuo> entrada) {
        ArrayList<Individuo> ordenados = (ArrayList<Individuo>) entrada.clone();
        Collections.sort(ordenados);
        ArrayList<Individuo> seleccionados = new ArrayList<>(cantidad);
        for (int i = 0; i < cantidad; i++) {
            seleccionados.add(ordenados.get(i));
            cont_selecElitista++;
        }   
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionElitista(int cantidad) {
        return seleccionElitista(cantidad, this.getPoblado());
    }

    public ArrayList<Individuo> seleccionRuleta(int cantidad, ArrayList<Individuo> entrada) {

        ArrayList<Integer> rangos = new ArrayList<>();
        int acum = 0;
        float aptitudEntrada = getAptitudPoblacion(entrada);
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
            cont_selecRuleta++;
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
        ArrayList<Individuo> ordenados = (ArrayList<Individuo>) entrada.clone();
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
                    cont_selecRanking++;
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
        float aptitudPromedio = getAptitudPoblacion(entrada) / entrada.size();
        ArrayList<Individuo> seleccionados = new ArrayList();
        int i = 0, cont = 0;
        while (cont < cantidad) {
            //agregar try-catch por si "i" supera el tamaño de la lista.
            float valor = entrada.get(i).getAptitud() / aptitudPromedio;
            int entero = (int) valor;
            for (int j = 0; j < entero; j++) {
                seleccionados.add(entrada.get(i));
                cont++;
                cont_selecContCopias++;
            }
            i++;
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionContCopiasEsp(int cantidad) {
        return seleccionContCopiasEsp(cantidad, this.poblado);
    }

    /**
     *
     * @param cantidad la cantidad total de individuos que se van a seleccionar.
     * @param cantGrupos
     * @param entrada
     * @return
     */
    public ArrayList<Individuo> seleccionXTorneo(int cantidad, int cantGrupos, ArrayList<Individuo> entrada) {
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        ArrayList<ArrayList<Individuo>> grupos = getSubGrupos(entrada.size(), cantGrupos, entrada);
        for (int i = 0; i < cantGrupos; i++) {
            ArrayList<Individuo> subgrupo = grupos.get(i);
            //ejecutar seleccion a este grupo. La cantidad va a ser "cantidad/cantGrupos"
            /*
             *Si quiero para la seleccion de 200 individuos el 10% (20),
             * y tengo 4 subgrupos, hago una seleccion de 5 individuos por cada 
             * subgrupo.
             * 
             * hacer case para ver que seleccion se usa en cada subgrupo
             * Tener en cuenta si cantidad no es multiplo de cantGrupos
             */
            int cantidadXGrupo = cantidad / cantGrupos;
            int tipoSeleccion = suerte.nextInt(4); //el 4 no entra porque es seleccion x torneo.
            seleccionados.addAll(seleccionXTipo(cantidadXGrupo, subgrupo, tipoSeleccion));
        }
        if (seleccionados.size() < cantidad) {
            seleccionados.addAll(seleccionElitista(cantidad - seleccionados.size()));
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionXTipo(int cantidad, ArrayList<Individuo> grupo, int tipoDeSeleccion) {
        ArrayList<Individuo> seleccionados = new ArrayList<>();
        switch (tipoDeSeleccion) {
            case SELECCION_ELITISTA:
                seleccionados.addAll(seleccionElitista(cantidad, grupo));
                break;
            case SELECCION_POR_COPIAS_ESPERADAS:
                seleccionados.addAll(seleccionContCopiasEsp(cantidad, grupo));
                break;
            case SELECCION_POR_RULETA:
                seleccionados.addAll(seleccionRuleta(cantidad, grupo));
                break;
            case SELECCION_RANKING:
                seleccionados.addAll(seleccionRanking(cantidad, rMin, grupo));
                break;
        }
        return seleccionados;
    }

    public ArrayList<Individuo> seleccionXTorneo(int cantidad, int cantGrupos) {
        return seleccionXTorneo(cantidad, cantGrupos, poblado);
    }

    public ArrayList<ArrayList<Individuo>> getSubGrupos(int cantidadTotalInd, int cantGrupos, ArrayList<Individuo> grupo) {
        ArrayList<ArrayList<Individuo>> seleccionados = new ArrayList<>();
        ArrayList<Individuo> copia = (ArrayList<Individuo>) grupo.clone();
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

    public ArrayList<Individuo> cruzarPoblacion(int cantidad, int tipo) {
        ArrayList<Individuo> cruzados = new ArrayList<>();
        while (cruzados.size() < cantidad) {
            ArrayList<Individuo> aCruzarse = getSubGrupos(2, poblado);
            cruzados.addAll(aCruzarse.get(0).cruza(tipo, aCruzarse.get(1)));
        }
        if (cruzados.size() > cantidad) {
            cruzados.remove(cruzados.size() - 1);
        }
        return cruzados;
    }

    /**
     *
     * @param cantidad un nuro de 0 a 2. (Ver en Individuo los tipos)
     * @return
     */
    public ArrayList<Individuo> cruzarPoblacion(int cantidad) {
        return cruzarPoblacion(cantidad,1 + suerte.nextInt(2));
    }

    public ArrayList<Individuo> mutarPoblacion(int cantidad) {
        //que sentido tiene poner probabilidad de mutar si tenes una cantidad que cumplir por mutación.
        ArrayList<Individuo> mutados = new ArrayList<>();
        ArrayList<Individuo> porMutar = getSubGrupos(cantidad, this.getPoblado());
//        int cont = 0;
//        int i = 0;
//        while (cont < cantidad && i < poblado.size()) {
//            if (suerte.nextFloat() <= probMutacion) {
//                Individuo aMutar = poblado.get(i);
//                aMutar.mutar();
//                mutados.add(aMutar);
//                cont++;
//            }
//            i++;
//        }
//
//        if (cont < cantidad) {
//            mutados.addAll(seleccionElitista(cantidad - cont));
//        }
        for (Individuo individuo : porMutar) {
            Individuo nuevo = new Individuo(individuo);
            nuevo.mutar();
            mutados.add(nuevo);
        }
        return mutados;
    }

    public float getAptitudPoblacion() {
        aptitud = getAptitudPoblacion(poblado);
        return aptitud;
    }

    public float getAptitudPoblacion(ArrayList<Individuo> entrada) {
        float sumatoria = 0f;
        for (Individuo individuo : entrada) {
            sumatoria += individuo.getAptitud();
        }
        return sumatoria;
    }

    public float getAptitudPromedio() {
        float aptitudPromedio = 0;
        aptitudPromedio = getAptitudPoblacion() / getPoblado().size();
        return aptitudPromedio;
    }

    public Poblacion() {
    }

    public float evaluarAptitud(int[] materialesIng) {
        float sumatoria = 0f;
        for (Individuo individuo : poblado) {
            sumatoria += individuo.evaluarAptitud(materialesIng);
        }
        return sumatoria;
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

    public float getrMin() {
        return rMin;
    }

    public static int getCont_selecElitista() {
        return cont_selecElitista;
    }

    public static int getCont_selecRuleta() {
        return cont_selecRuleta;
    }

    public static int getCont_selecRanking() {
        return cont_selecRanking;
    }

    public static int getCont_selecContCopias() {
        return cont_selecContCopias;
    }

    
    public static void main(String[] args) {
        int[] mIngresados = {5000,5000,5000,5000,5000,5000,5000,5000};
        Generaciones generaciones = new Generaciones(.20f, .60f, mIngresados);
        generaciones.ejecutar();
    }
}
