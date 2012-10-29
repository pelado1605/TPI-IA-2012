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

    /**
     * Constante de selección elitista. Determina uno de los tipos de
     * selecciones. Selección elitista es 0.
     */
    public static final int SELECCION_ELITISTA = 0;
    /**
     * Constante de selección por ruleta. Determina uno de los tipos de
     * selecciones. Selección por ruleta es 1.
     */
    public static final int SELECCION_POR_RULETA = 1;
    /**
     * Constante de selección por ranking. Determina uno de los tipos de
     * selecciones. Selección por ranking es 2.
     */
    public static final int SELECCION_RANKING = 2;
    /**
     * Constante de selección por copias esperadas. Determina uno de los tipos
     * de selecciones. Selección por copias esperadas es 3.
     */
    public static final int SELECCION_POR_COPIAS_ESPERADAS = 3;
    /**
     * Constante de selección por torneo. Determina uno de los tipos de 
     * selecciones. Selección por torneo es 4.
     */
    public static final int SELECCION_POR_TORNEO = 4;
    /**
     * Contador de selecciones elitistas. Sirve para las pruebas, para saber
     * cuantas veces se realizó este tipo de selección.
     */
    private static int cont_selecElitista = 0;
    /**
     * Contador de selecciones por ruleta. Sirve para las pruebas, para saber
     * cuantas veces se realizó este tipo de selección.
     */
    private static int cont_selecRuleta = 0;
    /**
     * Contador de selecciones por ranking. Sirve para las pruebas, para saber
     * cuantas veces se realizó este tipo de selección.
     */
    private static int cont_selecRanking = 0;
    /**
     * Contador de selecciones por cantidad de copias esperadas. Sirve para las
     * pruebas, para saber cuantas veces se realizó este tipo de selección.
     */
    private static int cont_selecContCopias = 0;
    /**
     * Arreglo de los individuos de la población. Esta formado por un ArrayList
     * de longitud de la población. Cada elemento corresponde a un individuo de
     * la población.
     */
    private ArrayList<Individuo> poblado;
    /**
     * Aleatorio para uso dentro de la clase. Random para usar en métodos que
     * necesiten valores aleatorios para su funcionamiento.
     */
    private Random suerte = new Random();
    /**
     * Sumatoria de todas las aptitudes de los individuos que conforman la
     * población. Se inicializa en 0.
     */
    private float aptitud = 0f;
    /**
     * Probabilidad de mutación. Para determinar la posibilidad de que se
     * realice la mutación por temperatura.
     * @deprecated No se utiliza debido que se ha adoptado la mutación por
     * temperatura.
     */
    private float probMutacion;
    /**
     * Copias mínimas esperadas. Es un valor entre 0 y 1.
     */
    private float rMin;
    /**
     * Número de la generación. Indica en que iteración fue generada.
     */
    private int nroGeneracion;

    /**
     * Constructor de la población. Genera la población para que la misma sea
     * apta para su utilización.
     * @param poblado ArrayList de los individuos que serán parte de la 
     * población.
     * @param probMutacion Probabilidad de mutación. No se utiliza actualmente,
     * ya que no hay mutación por temperatura ascendente/descendente.
     * @param rMin Copias mínimas esperadas, utilizados para la selección por 
     * control de copias esperadas. Es un valor decimal entre 0 y 1.
     * @param generacion Número de la generación. Permite distinguir entre las
     * generaciones. Es asignado por la iteración en la que es generado.
     */
    public Poblacion(ArrayList<Individuo> poblado, float probMutacion, float rMin, int generacion) {
        this.poblado = poblado;
        this.probMutacion = probMutacion;
        this.rMin = rMin;
        this.nroGeneracion = generacion;
        getAptitudPoblacion();
    }

    /**
     * Devuelve un individuo en la posición dada de la población. Ordena los 
     * individuos del poblado según la aptitud y devuelve el de la posición
     * deseada.
     * @param index Posición dentro de la población, deseado a obtener.
     * @return Individuo del poblado en la posición "index"
     */
    public Individuo devolverIndividuo(int index) {
        Individuo buscado = new Individuo();
        ArrayList<Individuo> ordenados = (ArrayList<Individuo>) poblado.clone();
        Collections.sort(ordenados);

        buscado = ordenados.get(index);

        return buscado;
    }

    /**
     * Realiza la selección elitista de los individuos de una población.
     * Devuelve un arreglo de individuos ordenados según la aptitud. El tamaño
     * del arreglo es determinado por el parámetro "cantidad".
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param entrada El poblado de donde se realizará la selección.
     * @return El conjunto de individuos seleccionados de manera elitista.
     */
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

    /**
     * Realiza la selección elitista de los individuos de esta población.
     * Utiliza el método selecciónElitista con el poblado de esta población.
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @return El conjunto de individuos seleccionados de manera elitista.
     */
    public ArrayList<Individuo> seleccionElitista(int cantidad) {
        return seleccionElitista(cantidad, this.getPoblado());
    }

    /**
     * Realiza la selección por ruleta de los individuos de una población.
     * Devuelve un arreglo de individuos de manera aleatoria según una ruleta. 
     * El tamaño del arreglo es determinado por el parámetro "cantidad".
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param entrada El poblado de donde se realizará la selección.
     * @return El conjunto de individuos seleccionados por la ruleta.
     */
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

    /**
     * Realiza la selección por ruleta de los individuos de esta población.
     * Utiliza el método selecciónRuleta con el poblado de esta población.
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @return El conjunto de individuos seleccionados por la ruleta.
     */
    public ArrayList<Individuo> seleccionRuleta(int cantidad) {
        return seleccionRuleta(cantidad, this.poblado);
    }

    /**
     * Realiza la selección por ranking de los individuos de una población.
     * Devuelve un arreglo mediante el uso de ranking y numero de copias
     * esperadas. El tamaño del arreglo es determinado por el parámetro 
     * "cantidad".
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param rMin El numero de copias esperadas.
     * @param entrada El poblado de donde se realizará la selección.
     * @return El conjunto de individuos seleccionados por ranking.
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

    /**
     * Realiza la selección por ranking de los individuos de esta población.
     * Utiliza el método seleccionRanking con el poblado de esta población.
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param rMin El numero de copias esperadas.
     * @return El conjunto de individuos seleccionados por ranking.
     */
    public ArrayList<Individuo> seleccionRanking(int cantidad, float rMin) {
        return seleccionRanking(cantidad, rMin, this.poblado);
    }

    /**
     * Realiza la selección por control de copias esperadas de una población.
     * Devuelve un arreglo mediante el uso control de copias esperadas. 
     * El tamaño del arreglo es determinado por el parámetro "cantidad".
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param entrada El poblado de donde se realizará la selección.
     * @return El conjunto de individuos seleccionados por control de copias
     * esperadas.
     */
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

    /**
     * Realiza la selección por control de copias esperadas de esta población.
     * Utiliza el método seleccionContCopiasEsp con el poblado de esta
     * población.
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @return El conjunto de individuos seleccionados por control de copias
     * esperadas.
     */
    public ArrayList<Individuo> seleccionContCopiasEsp(int cantidad) {
        return seleccionContCopiasEsp(cantidad, this.poblado);
    }

    /**
     * Realiza la selección por torneo de una población.
     * Devuelve un arreglo mediante el uso de torneos. El tamaño del arreglo es
     * determinado por el parámetro "cantidad". La cantidad de grupos se
     * determina con el parámetro cantGrupos.
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param entrada El poblado de donde se realizará la selección.
     * @param cantGrupos El número de grupos que se van a realizar.
     * @return El conjunto de individuos seleccionados por torneo.
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

    /**
     * Determina el tipo de selección a realizar dentro de un torneo.
     * @param cantidad El número de individuos elegidos mediante el tipo de 
     * selección.
     * @param grupo Individuos que se encuentra dentro del grupo.
     * @param tipoDeSeleccion Elige el tipo de selección a realizar.
     * @return El conjunto de individuos del grupo del torneo.
     */
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

    /**
     * Realiza la selección por torneo de esta población.
     * Utiliza el método seleccionXTorneo con el poblado de esta población.
     * @param cantidad El número de individuos elegidos mediante este tipo de 
     * selección.
     * @param cantGrupos El número de grupos que se van a realizar.
     * @return El conjunto de individuos seleccionados por torneo.
     */
    public ArrayList<Individuo> seleccionXTorneo(int cantidad, int cantGrupos) {
        return seleccionXTorneo(cantidad, cantGrupos, poblado);
    }

    /**
     * Genera subgrupos dentro de una población dada.
     * @param cantidadTotalInd Indica la cantidad de individuos dentro de la 
     * población dada.
     * @param cantGrupos Indica el número de subgrupos a realizar.
     * @param grupo La población del cual generar los subgrupos.
     * @return Arreglo de subgrupos de individuos.
     */
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

    /**
     * Genera un subgrupo único de una población dada de tamaño dado. Útil para 
     * obtener una cantidad definida de individuos de una población dada.
     * @param cantidad Cantidad de individuos del subgrupo generado.
     * @param grupo La población de individuos de donde se generará el subgrupo.
     * @return El subgrupo generado con la cantidad de individuos definido.
     */
    public ArrayList<Individuo> getSubGrupos(int cantidad, ArrayList<Individuo> grupo) {
        return getSubGrupos(cantidad, 1, grupo).get(0);
    }

    /**
     * Realiza la cruza de los individuos para generar la próxima generación. El
     * tipo de cruza puede ser simple, multipunto o binomial, según el parámetro
     * "tipo".
     * @param cantidad Cantidad de individuos que se cruzarán.
     * @param tipo Tipo de cruza a utilizar.
     * @return Arreglo de individuos cruzados.
     */
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
     * Realiza la cruza de los individuos para generar la próxima generación, 
     * tomando aleatoriamente el tipo de cruza.
     * @param cantidad Cantidad de individuos que se cruzarán.
     * @return Arreglo de individuos cruzados.
     */
    public ArrayList<Individuo> cruzarPoblacion(int cantidad) {
        return cruzarPoblacion(cantidad, 1 + suerte.nextInt(2));
    }

    /**
     * Realiza la mutación de los individuos para la próxima generación.
     * @param cantidad La cantidad de individuos que serán mutados.
     * @return Arreglo de individuos mutados.
     */
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

    /**
     * Calcula la sumatoria de la aptitud de la población, y lo setea a la 
     * variable "aptitud".
     * @return El valor float de la sumatoria de las aptitudes.
     */
    public float getAptitudPoblacion() {
        aptitud = getAptitudPoblacion(poblado);
        return aptitud;
    }

    /**
     * Calcula la sumatoria de la aptitud de un arreglo de individuos dado.
     * @param entrada El arreglo de individuos a calcular la sumatoria de
     * aptitud.
     * @return El valor float de la sumatoria de las aptitudes.
     */
    public float getAptitudPoblacion(ArrayList<Individuo> entrada) {
        float sumatoria = 0f;
        for (Individuo individuo : entrada) {
            sumatoria += individuo.getAptitud();
        }
        return sumatoria;
    }

    /**
     * Calcula el promedio de la aptitud de la población.
     * @return El valor promedio de la aptitud.
     */
    public float getAptitudPromedio() {
        float aptitudPromedio = 0;
        aptitudPromedio = getAptitudPoblacion() / getPoblado().size();
        return aptitudPromedio;
    }

    /**
     * Fuerza a realizar la evaluación de aptitud de cada uno de los individuos
     * de la población. A diferencia de "getAptitudPoblacion()", este último 
     * toma el valor del atributo, puede que no haya sido calculado aún, y dar 
     * valores erróneos.
     * @param materialesIng Materiales ingresados por el usuario.
     * @return El valor float de la sumatoria de las aptitudes.
     */
    public float evaluarAptitud(int[] materialesIng) {
        float sumatoria = 0f;
        for (Individuo individuo : poblado) {
            sumatoria += individuo.evaluarAptitud(materialesIng);
        }
        return sumatoria;
    }

    /**
     * Devuelve el poblado de la población. Es un simple getter del atributo
     * "poblado".
     * @return Arreglo del poblado con los individuos que lo componen.
     */
    public ArrayList<Individuo> getPoblado() {
        return poblado;
    }

    /**
     * Devuelve la sumatoria de la aptitud de la población. Es un simple getter
     * del atributo "aptitud".
     * @return Float con la sumatoria de la aptitud.
     */
    public float getAptitud() {
        return aptitud;
    }

    /**
     * Devuelve la probabilidad de mutación de la población. Es un simple getter
     * del atributo "probMutación".
     * @deprecated No se utiliza el atributo, por lo tanto, tampoco se debería
     * usar este método.
     * @return Float con la probabilidad de mutación.
     */
    public float getProbMutacion() {
        return probMutacion;
    }

    /**
     * Devuelve el número de copias esperadas para la selección por ranking de 
     * esta población. Es un simple getter del atributo "rMin".
     * @return Float con el número de copias esperadas.
     */
    public float getrMin() {
        return rMin;
    }

    /**
     * Devuelve el número de la generación al cual pertenece esta población. Es
     * un simple getter del atributo "nroGeneracion".
     * @return Entero con el número de la generación de la población.
     */
    public int getNroGeneracion() {
        return nroGeneracion;
    }

    /**
     * Devuelve la cantidad de veces que se realizó selección elitista. Es un
     * simple getter del atributo estático "cont_selecElitista".
     * @return Entero con la cantidad de veces que se realizó dicho tipo de
     * selección.
     */
    public static int getCont_selecElitista() {
        return cont_selecElitista;
    }

    /**
     * Devuelve la cantidad de veces que se realizó selección por ruleta. Es un
     * simple getter del atributo estático "cont_selecRuleta".
     * @return Entero con la cantidad de veces que se realizó dicho tipo de
     * selección.
     */
    public static int getCont_selecRuleta() {
        return cont_selecRuleta;
    }

    /**
     * Devuelve la cantidad de veces que se realizó selección por ranking. Es un
     * simple getter del atributo estático "cont_selecRanking".
     * @return Entero con la cantidad de veces que se realizó dicho tipo de
     * selección.
     */
    public static int getCont_selecRanking() {
        return cont_selecRanking;
    }

    /**
     * Devuelve la cantidad de veces que se realizó selección por control de
     * copias. Es un simple getter del atributo estático "cont_selecContCopias".
     * @return Entero con la cantidad de veces que se realizó dicho tipo de
     * selección.
     */
    public static int getCont_selecContCopias() {
        return cont_selecContCopias;
    }

}