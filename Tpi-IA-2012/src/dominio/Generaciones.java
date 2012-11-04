/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Random;
import javax.swing.SwingWorker;

/**
 * Clase que representa al conjunto de las generaciones con sus poblaciones e
 * individuos. Contiene la lógica de la ejecución del algoritmo, atributos y
 * métodos para la ejecución completa del algoritmo.
 *
 * @author Ruben
 */
public class Generaciones extends SwingWorker<Boolean, Poblacion> {

    /**
     * Tamaño de la población que se utilizará en el algoritmo. El número indica
     * cuantos individuos existirán dentro de una población.
     */
    public static final int CANTIDAD_POBLACION = 250;
    /**
     * Cantidad de iteraciones que realizará el algoritmo. El número indica
     * cuantas veces se hará la selección, cruza y mutación de los individuos.
     * Definirá la cantidad de generaciones que existirán.
     */
    public static final int CANTIDAD_ITERACIONES = 1000;
    public static final float PORC_SELECCION = 0.2f;
    public static final float PORC_CRUZA = 0.65f;
    /**
     * Número de copias esperadas que se utilizará el algoritmo. Definido para
     * utilizar en la selección por ranking.
     */
    public static final float RMIN = 0.5f;
    /**
     * Probabilidad de mutación mínima. Utilizado para la mutación por
     * temperatura ascendente/descendente.
     */
    public static final float PROB_MUT_MIN = 0.02f;
    /**
     * Probabilidad de mutación máxima. Utilizado para la mutación por
     * temperatura ascendente/descendente.
     */
    public static final float PROB_MUT_MAX = 0.3f;
    /**
     * Probabilidad fija de mutación. Utilizado para determinar la probabilidad
     * de mutación de un individuo.
     *
     * @deprecated NO SE UTILIZA, ya que se define una cantidad de población a
     * ser mutada.
     */
    public static final float PROB_FIJA = 1f;
    /**
     * Factor Lambda para la mutación por temperatura. Incremento (o decremento)
     * de la probabilidad de mutación por temperatura ascendente (o descendente)
     * por cada iteración ejecutada.
     */
    public static final float LAMBDA = 0.005f;
    /**
     * Individuo óptimo inalcanzable. Cada producto que contiene es calculado
     * como si no existiesen los otros. En la práctica, no se podrá alcanzar, ya
     * que será un individuo infactible, con los materiales ingresados por el
     * usuario. Se utiliza principalmente para la generación de la población
     * inicial, y para comparar la aptitud con la de los demás individuos.
     */
    public static Individuo gokuFase4;
    /**
     * Aptitud máxima. Máxima aptitud alcanzable.
     *
     * @deprecated NO SE UTILIZA, para determinar dicho valor, se utiliza el
     * individuo óptimo "gokuFase4".
     */
    public float aptitudMaxima;
    /**
     * Cantidad de individuos que serán obtenidos por selección. El número de
     * individuos que serán pasados intactos a la próxima generación.
     */
    private int cSeleccion;
    /**
     * Cantidad de individuos que serán obtenidos por cruza. El número de
     * individuos resultantes de la cruza que serán parte de la próxima
     * generación.
     */
    private int cCruza;
    /**
     * Cantidad de individuos que serán obtenidos por mutación. El número de
     * individuos sometidos a la mutación que serán parte de la próxima
     * generación.
     */
    private int cMutacion;
    /**
     * Arreglo con cada una de las poblaciones de cada generación. En cada
     * iteración se genera una nueva población y será almacenada en este
     * arreglo.
     */
    private ArrayList<Poblacion> generaciones = new ArrayList();
    /**
     * Materiales ingresados por el usuario. Es un vector de enteros que
     * contiene las cantidades de m1..m8 ingresados por el usuario.
     */
    private int[] materialesIng;
    /**
     * Generador de aleatorios de uso múltiple. Será utilizado por los metodos
     * que necesiten de números aleatorios.
     */
    private Random suerte = new Random();
//    private Archivador archivador;
    /**
     * Formateador de strings.
     *
     * @deprecated NO SE UTILIZA.
     */
    private Formatter formato = new Formatter();
    /**
     * Variable booleana que determina el estado de ejecución del algoritmo. Si
     * la variable es verdadera, el algoritmo se encuetra pausado, en caso
     * contrario, esta en ejecución.
     */
    private boolean pausado = false;
    /**
     * Iteración en la que se encuentra la ejecución del algoritmo. Es un valor
     * entero que se inicia en 0.
     */
    private int iteracionActual = 0;
    private int cantIteraciones;
    private int tamañoPoblacion;
    private int tipoSeleccion;
    private int cantGrupos; //para seleccion por torneo
    private int tipoCruza;

    /**
     * Constructor de la clase Generaciones. Setea la cantidad de individuos que
     * serán seleccionados, cruzados y mutados, para crear las siguientes
     * generaciones. Toma los materiales ingresados por el usuario. Y por último
     * crea al individuo óptimo inalcanzable, que se tomará como base para la
     * creación de la población inicial.
     *
     * @param pSeleccion Float que indica el porcentaje de la población que será
     * seleccionada para la generación de las próximas iteraciones.
     * @param pCruza Float que indica el porcentaje de la población que será
     * cruzada para la generación de las próximas iteraciones.
     * @param materiales Arreglo de materiales m1..m8 ingresados por el usuario.
     */
    public Generaciones(int cantIter, int tamPob, float pSeleccion, int tipoSeleccion,
            int cantGrupos, float pCruza, int tipoCruza, int[] materiales) {
        this.tamañoPoblacion = tamPob;
        this.cantIteraciones = cantIter;
        this.cSeleccion = convertPorcentACant(pSeleccion, tamañoPoblacion);
        this.tipoSeleccion = tipoSeleccion;
        this.cantGrupos = cantGrupos;
        this.cCruza = convertPorcentACant(pCruza, tamañoPoblacion);
        this.tipoCruza = tipoCruza;
        this.cMutacion = tamañoPoblacion - cCruza - cSeleccion;
        this.materialesIng = materiales;
        crearAGoku();
    }

    /**
     * Ejecución del algoritmo genético. Implementación por Swingworker. Se
     * ejecuta en segundo plano, para que permita el funcionamiento de la
     * interfaz gráfica, además de la posibilidad de pausarlo a conveniencia.
     *
     * @return Booleano, ver api de Swingworker.
     * @throws InterruptedException
     * @throws Exception
     */
    @Override
    protected Boolean doInBackground() throws InterruptedException, Exception {
//        archivador = new Archivador(Calendar.getInstance().getTime().getHours()
//                + " " + Calendar.getInstance().getTime().getMinutes() + " "
//                + Calendar.getInstance().getTime().getSeconds()+".txt");
        iteracionActual = 0;
        float probMutacion = 0;
        generarPoblacionInicial();
        generaciones.get(0).evaluarAptitud(materialesIng);
//        archivador.abrirArchivo();
        while (cantIteraciones > iteracionActual) {
            if (pausado) {
                synchronized (this) {
                    wait();
                }
            }
            Poblacion generacAnterior = generaciones.get(iteracionActual);
            ArrayList<Individuo> listaAnterior = (ArrayList<Individuo>) generacAnterior.getPoblado().clone();
            Poblacion copia = new Poblacion(listaAnterior, PROB_FIJA, RMIN, iteracionActual);
//            probMutacion = calcularProbMutacion(iteracionActual, probMutacion);
            iteracionActual++;
            Poblacion actual = new Poblacion(copia.seleccionXTipo(cSeleccion, copia.getPoblado(), tipoSeleccion, cantGrupos), PROB_FIJA, RMIN, iteracionActual);
//            Poblacion actual = new Poblacion(copia.seleccionElitista(cSeleccion - convertPorcentACant(.8f, cSeleccion)), PROB_FIJA, RMIN, iteracionActual);
//            actual.getPoblado().addAll(copia.seleccionRuleta(convertPorcentACant(.8f, cSeleccion)));
            actual.getPoblado().addAll(copia.cruzarPoblacion(cCruza, tipoCruza));
            actual.getPoblado().addAll(copia.mutarPoblacion(cMutacion));
            actual.evaluarAptitud(materialesIng);
            generaciones.add(actual);
//            ArrayList<Individuo> prueba = (ArrayList<Individuo>) actual.getPoblado().clone();
//            Collections.sort(prueba);

//            System.out.println(new DecimalFormat("#.##").format(generacAnterior.getAptitudPromedio()));
//            System.out.println(prueba.get(0).getUtilidad() +" , " +prueba.get(800).getUtilidad());
//            for (Individuo indiv : actual.getPoblado()) {
//                archivador.agregarRegistros(indiv);
//            }
//            archivador.agregar("-----------------------------");
            Thread.sleep(8);
            System.out.println(iteracionActual);
            publish(actual);
            int progreso = 0;
            progreso = (int) (((float) iteracionActual) / ((float) cantIteraciones) * 100);
            System.out.println(progreso);
            setProgress(progreso);
            getPropertyChangeSupport().firePropertyChange("genParaGrafica", generaciones.get(iteracionActual - 1),
                    generaciones.get(iteracionActual));
        }
        Collections.sort(generaciones.get(cantIteraciones - 1).getPoblado());
//        for (Individuo indiv : generaciones.get(cantIteraciones-1).getPoblado()) {
//            archivador.agregarRegistros(indiv);
//        }
//        archivador.agregar("-----------------------------");
        System.out.println("Aptitud Goku : " + gokuFase4.getAptitud());
        System.out.println("Indiv Goku : " + gokuFase4.mostrarProductos());
        System.out.println("Utilidad Goku : " + gokuFase4.getUtilidad());
        System.out.println();
        for (int i = 0; i < 1; i++) {
            Collections.sort(generaciones.get(cantIteraciones - 1).getPoblado());
            System.out.println("Aptitud : " + generaciones.get(cantIteraciones - 1).getPoblado().get(i).getAptitud());
            System.out.println("Individuo : " + generaciones.get(cantIteraciones - 1).getPoblado().get(i).mostrarProductos());
            System.out.println("Utilidad : " + generaciones.get(cantIteraciones - 1).getPoblado().get(i).getUtilidad());


//            archivador.cerrarArchivo();
        }
        System.out.print("Cant Elitista: ");
        System.out.println(Poblacion.getCont_selecElitista());
        System.out.print("Cant Ruleta: ");
        System.out.println(Poblacion.getCont_selecRuleta());
        System.out.print("Cant Ranking: ");
        System.out.println(Poblacion.getCont_selecRanking());
        System.out.print("Cant Control de copias: ");
        System.out.println(Poblacion.getCont_selecContCopias());
        System.out.print("Cant Torneo: ");
        System.out.println(Poblacion.getCont_selecTorneo());
        System.out.print("Cant CRUZA SIMPLE: ");
        System.out.println(Individuo.getCont_cruzaSimple());
        System.out.print("Cant CRUZA MULTIPUNTO: ");
        System.out.println(Individuo.getCont_cruzaMultipunto());
        System.out.print("Cant BINOMIAL: ");
        System.out.println(Individuo.getCont_cruzaBinomial());

        return true;
    }

//    public float[] aptitudMejorInd (int iteracion, int posicion){
//        float[] mejoraptitud = new float[iteracion];
//        for (int i = 0; i < iteracion; i++) {
//            mejoraptitud[i] = generaciones.get(i).devolverIndividuo(posicion).getAptitud();                 
//        }
//        return mejoraptitud;
//    }
//    
//    public float[] aptitudpromedio  (int iteracion) {
//        float[] aptpormedio = new float[iteracion];
//        
//        for (int i = 0; i < iteracion; i++) {
//            aptpormedio[i]=generaciones.get(i).getAptitudPromedio();
//        }
//             
//        return aptpormedio;
//         }
    /**
     * Genera la primera población, basandose en el individuo óptimo. Se toma a
     * al individuo óptimo inalcanzable como parámetro para la generación de los
     * individuos. El mayor individuo posible de generar tiene sus productos
     * p1..p4 hasta 1.2 veces la del individuo óptimo (que será inalcanzable,
     * dado al individuo óptimo). Esto elimina un espacio que se saben que serán
     * infactibles (aquellos mayores a este individuo mayor).
     *
     * @return Un arreglo con los individuos de la población inicial.
     */
    public ArrayList<Individuo> generarPoblacionInicial() {
        ArrayList<Individuo> nueva = new ArrayList<>();
        for (int i = 0; i < tamañoPoblacion; i++) {
            Individuo nuevo = new Individuo(suerte.nextInt((int) (gokuFase4.getP1() * 1.2) + 1),
                    suerte.nextInt((int) (gokuFase4.getP2() * 1.2) + 1),
                    suerte.nextInt((int) (gokuFase4.getP3() * 1.2) + 1),
                    suerte.nextInt((int) (gokuFase4.getP4() * 1.2) + 1));
            nueva.add(nuevo);
        }
        gokuFase4.calcularBase();
        Poblacion nuevaPob = new Poblacion(nueva, 0f, 0, 0);
        generaciones.add(nuevaPob);
        return nueva;
    }

    /**
     * Calcula la cantidad máxima posible de cada producto. Esto se utiliza para
     * realizar la generación inicial, y que los números aleatorios no superen
     * los máximos. Debido a que esta combinación no es factible, <b>no se
     * estará sesgando alguna posible solución</b>. <br/> El cálculo se realiza
     * considerando a cada producto como único para la utilización de los
     * materiales ingresados. De esta manera obtenemos un valor máximo que no
     * podrá ser alcanzado, ya que en realidad, la utilizacion de los materiales
     * se comparte para todos los productos.
     */
    public void crearAGoku() {
        Individuo superSayayin = new Individuo();
        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> minimos = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                if (Individuo.MMinimos[i][j] != 0) {
                    minimos.add(materialesIng[j] / Individuo.MMinimos[i][j]);
                }
            }
            superSayayin.setProducto(i, Collections.min(minimos));

        }
        superSayayin.evaluarAptitud(materialesIng, true);
        gokuFase4 = superSayayin;
    }

    /**
     * Calcúla la probabilidad de mutación por temperatura ascendente según la
     * iteración en la que se encuentra el algoritmo. Toma la iteración actual y
     * la probabilidad anterior para realizar el cálculo. Utiliza los atributos
     * de probabilidad máxima de mutación y de lambda.
     *
     * @param nroIteracion Iteración actual, en la que se encuentra la ejecución
     * del algoritmo.
     * @param probAnterior Probabilidad que tenía anteriormente la mutación por
     * temperatura.
     * @return Float con la probabilidad actual de mutación.
     */
    public float calcularProbMutacion(int nroIteracion, float probAnterior) {
        float probActual = probAnterior;
        if (probActual < PROB_MUT_MAX) {
            probActual = probActual + (LAMBDA * (nroIteracion + 1));
        } else {
            probActual = PROB_MUT_MAX;
        }
        return probActual;
    }

    /**
     * Convierte los porcentajes en cantidades. Se utiliza para pasar el
     * porcentaje de selección/cruza/mutación en cantidades enteras para
     * determinar el número de individuos que formarán la próxima generación con
     * cada uno de estos operadores.
     *
     * @param porciento Float indicando el porcentaje.
     * @param cantidadEntrada Entero indicando la cantidad de individuos.
     * @return Entero indicando la cantidad de individuos que se generarán por
     * el operador deseado.
     */
    private int convertPorcentACant(float porciento, int cantidadEntrada) {
        int cantidad = (int) (porciento * cantidadEntrada);
        return cantidad;
    }

    /**
     * Devuelve la cantidad de individuos que se seleccionarán para la próxima
     * iteración. Es un simple getter de "cSelección".
     *
     * @return Entero indicando la cantidad por selección.
     */
    public int getcSeleccion() {
        return cSeleccion;
    }

    /**
     * Devuelve la cantidad de individuos que se generarán por cruza para la
     * próxima iteración. Es un simple getter de "cCruza".
     *
     * @return Entero indicando la cantidad por cruza.
     */
    public int getcCruza() {
        return cCruza;
    }

    /**
     * Devuelve la cantidad de individuos que se mutarán para la próxima
     * iteración. Es un simple getter de "cMutacion".
     *
     * @return Entero indicando la cantidad por mutación.
     */
    public int getcMutacion() {
        return cMutacion;
    }

    public int getIteracionActual() {
        return iteracionActual;
    }

    public void setIteracionActual(int iteracionActual) {
        this.iteracionActual = iteracionActual;
    }

    public int getCantIteraciones() {
        return cantIteraciones;
    }

    public void setCantIteraciones(int cantIteraciones) {
        this.cantIteraciones = cantIteraciones;
    }

    public int getTamañoPoblacion() {
        return tamañoPoblacion;
    }

    public void setTamañoPoblacion(int tamañoPoblacion) {
        this.tamañoPoblacion = tamañoPoblacion;
    }

    /**
     * Devuelve el arreglo de las poblaciones de la ejecución del algoritmo. Es
     * un simple getter de "generaciones".
     *
     * @return Arreglo con todas las poblaciones de la ejecución del algoritmo.
     */
    public ArrayList<Poblacion> getGeneraciones() {
        return generaciones;
    }

    /**
     * Devuelve el estado de ejecución del algoritmo: Está o no pausado. Es un
     * simple getter del booleano "pausado".
     *
     * @return Booleano indicando si el algoritmo se encuentra pausado.
     */
    public boolean isPausado() {
        return pausado;
    }

    /**
     * Setea el valor de "pausado", cambiando el estado de ejecución del
     * algoritmo. Es un simple setter del booleano "pausado".
     *
     * @param pausado Booleano a settear a pausado.
     */
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public int[] getMaterialesIng() {
        return materialesIng;
    }

    /**
     * Agrega un escuchador de cambio de propiedad.
     *
     * @param pcl
     */
    public void addPCl(PropertyChangeListener pcl) {
        getPropertyChangeSupport().addPropertyChangeListener(pcl);
    }

    /**
     * Elimina un escuchador de cambio de propiedad.
     *
     * @param pcl
     */
    public void removePCl(PropertyChangeListener pcl) {
        getPropertyChangeSupport().removePropertyChangeListener(pcl);
    }
    /**
     * Escuchador de acciones.
     */
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pausar")
                    | e.getActionCommand().equals("Parar")
                    | e.getActionCommand().equals("Siguiente")) {
                getPropertyChangeSupport().firePropertyChange("genParaTabla", generaciones.get(iteracionActual - 1),
                        generaciones.get(iteracionActual));
            }
            System.out.println(e.getActionCommand());
        }
    };

    /**
     * Determina que se realizará al terminar la ejecución de "doInBackground".
     * Eso existe dado a la implementación de SwingWorker.
     */
    @Override
    protected void done() {
        getPropertyChangeSupport().firePropertyChange("genParaTabla", generaciones.get(iteracionActual - 1),
                generaciones.get(iteracionActual));
        getPropertyChangeSupport().firePropertyChange("resultado", generaciones.get(iteracionActual - 1),
                generaciones.get(iteracionActual));
        getPropertyChangeSupport().firePropertyChange("revisar", null, generaciones);
        Poblacion.reiniciarContadoresSeleccion();
        Individuo.reiniciarContadoresCruza();
        System.gc();
    }

    /**
     * Devuelve al escuchador de la acción. Getter de "al".
     *
     * @return El escuchador de la acción.
     */
    public ActionListener getAl() {
        return al;
    }
}
