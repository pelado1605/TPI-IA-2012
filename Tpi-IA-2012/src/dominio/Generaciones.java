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
     * usuario. Se utiliza para comparar la aptitud con la de los demás
     * individuos.
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
     * Iteración en la que se encuentra 
     */
    private int iteracionActual = 0;

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
        while (CANTIDAD_ITERACIONES > iteracionActual) {
            if (pausado) {
                synchronized (this) {
                    wait();
                }
            }
            Poblacion generacAnterior = generaciones.get(iteracionActual);
            ArrayList<Individuo> listaAnterior = (ArrayList<Individuo>) generacAnterior.getPoblado().clone();
            Poblacion copia = new Poblacion(listaAnterior, PROB_FIJA, RMIN, iteracionActual);
//            probMutacion = calcularProbMutacion(iteracionActual, probMutacion);
            Poblacion actual = new Poblacion(copia.seleccionElitista(cSeleccion - convertPorcentACant(.8f, cSeleccion)), PROB_FIJA, RMIN, iteracionActual);
            actual.getPoblado().addAll(copia.seleccionRuleta(convertPorcentACant(.8f, cSeleccion)));
            actual.getPoblado().addAll(copia.cruzarPoblacion(cCruza));
            actual.getPoblado().addAll(copia.mutarPoblacion(cMutacion));
            actual.evaluarAptitud(materialesIng);
            generaciones.add(actual);
            Thread.sleep(10);
//            ArrayList<Individuo> prueba = (ArrayList<Individuo>) actual.getPoblado().clone();
//            Collections.sort(prueba);

//            System.out.println(new DecimalFormat("#.##").format(generacAnterior.getAptitudPromedio()));
//            System.out.println(prueba.get(0).getUtilidad() +" , " +prueba.get(800).getUtilidad());
//            for (Individuo indiv : actual.getPoblado()) {
//                archivador.agregarRegistros(indiv);
//            }
//            archivador.agregar("-----------------------------");
            iteracionActual++;
            System.out.println(iteracionActual);
            publish(actual);
            int progreso = iteracionActual / (CANTIDAD_ITERACIONES / 100);
            setProgress(progreso);
            getPropertyChangeSupport().firePropertyChange("genParaGrafica", generaciones.get(iteracionActual - 1),
                    generaciones.get(iteracionActual));
        }
        Collections.sort(generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado());
//        for (Individuo indiv : generaciones.get(CANTIDAD_ITERACIONES-1).getPoblado()) {
//            archivador.agregarRegistros(indiv);
//        }
//        archivador.agregar("-----------------------------");
        System.out.println("Aptitud Goku : " + gokuFase4.getAptitud());
        System.out.println("Indiv Goku : " + gokuFase4.mostrarProductos());
        System.out.println("Utilidad Goku : " + gokuFase4.getUtilidad());
        System.out.println();
        for (int i = 0; i < 1; i++) {
            Collections.sort(generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado());
            System.out.println("Aptitud : " + generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado().get(i).getAptitud());
            System.out.println("Individuo : " + generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado().get(i).mostrarProductos());
            System.out.println("Utilidad : " + generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado().get(i).getUtilidad());


//            archivador.cerrarArchivo();
        }
        System.out.println();
//        NumberFormat formatter = new DecimalFormat("####0.00");
//            float[] aptitudes = aptitudMejorInd(iteracionActual,0);
//            for (float f : aptitudes) {
//                System.out.println(formatter.format(f));
//            }
//            
//            aptitudes = aptitudpromedio(iteracionActual);
//            System.out.println();
//           for (float f : aptitudes) {
//            System.out.println(formatter.format(f));
//        }
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
    public ArrayList<Individuo> generarPoblacionInicial() {
        ArrayList<Individuo> nueva = new ArrayList<>();
        for (int i = 0; i < CANTIDAD_POBLACION; i++) {
            Individuo nuevo = new Individuo(suerte.nextInt((int) (gokuFase4.getP1() * 1.2) + 1),
                    suerte.nextInt((int) (gokuFase4.getP2() * 1.2) + 1),
                    suerte.nextInt((int) (gokuFase4.getP3() * 1.2) + 1),
                    suerte.nextInt((int) (gokuFase4.getP4() * 1.2) + 1));
            nueva.add(nuevo);
        }
        gokuFase4.calcularBase();
        Poblacion nuevaPob = new Poblacion(nueva, 0f, 0, iteracionActual);
        generaciones.add(nuevaPob);
        return nueva;
    }

    public Generaciones(float pSeleccion, float pCruza,
            int[] materiales) {
        this.cSeleccion = convertPorcentACant(pSeleccion, CANTIDAD_POBLACION);
        this.cCruza = convertPorcentACant(pCruza, CANTIDAD_POBLACION);
        this.cMutacion = CANTIDAD_POBLACION - cCruza - cSeleccion;
        this.materialesIng = materiales;
        crearAGoku();
    }

    /**
     * Calcula la cantidad máxima posible de cada producto. Esto se utiliza para
     * realizar la generación inicial, y que los números aleatorios no superen
     * los máximos. Debido a que esta combinación no es factible, no se estará
     * sesgando alguna posible solución. <br/> El cálculo se realiza
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

    public float calcularProbMutacion(int nroIteracion, float probAnterior) {
        float probActual = probAnterior;
        if (probActual < PROB_MUT_MAX) {
            probActual = probActual + (LAMBDA * (nroIteracion + 1));
        } else {
            probActual = PROB_MUT_MAX;
        }
        return probActual;
    }

    private int convertPorcentACant(float porciento, int cantidadEntrada) {
        int cantidad = (int) (porciento * cantidadEntrada);
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

    public ArrayList<Poblacion> getGeneraciones() {
        return generaciones;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public void addPCl(PropertyChangeListener pcl) {
        getPropertyChangeSupport().addPropertyChangeListener(pcl);
    }

    public void removePCl(PropertyChangeListener pcl) {
        getPropertyChangeSupport().removePropertyChangeListener(pcl);
    }
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pausar")
                    | e.getActionCommand().equals("Parar")
                    | e.getActionCommand().equals("Ste. Iteracion")) {
                getPropertyChangeSupport().firePropertyChange("genParaTabla", generaciones.get(iteracionActual - 1),
                        generaciones.get(iteracionActual));
            }
            System.out.println(e.getActionCommand());
        }
    };

    @Override
    protected void done() {
        getPropertyChangeSupport().firePropertyChange("genParaTabla", generaciones.get(iteracionActual - 1),
                generaciones.get(iteracionActual));
        getPropertyChangeSupport().firePropertyChange("resultado", generaciones.get(iteracionActual - 1),
                generaciones.get(iteracionActual));
    }

    public ActionListener getAl() {
        return al;
    }
}
