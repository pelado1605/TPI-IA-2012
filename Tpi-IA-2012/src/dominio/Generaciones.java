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
import java.util.List;
import java.util.Random;
import javax.swing.SwingWorker;

/**
 *
 * @author Ruben
 */
public class Generaciones extends SwingWorker<Boolean, Poblacion> {

    public static final int CANTIDAD_POBLACION = 250;
    public static final int CANTIDAD_ITERACIONES = 1000;
    public static final float RMIN = 0.5f;
    public static final float PROB_MUT_MIN = 0.02f;
    public static final float PROB_MUT_MAX = 0.3f;
    public static final float PROB_FIJA = 1f;
    public static final float LAMBDA = 0.005f;
    public static Individuo gokuFase4;
    public float aptitudMaxima;
    private int cSeleccion;
    private int cCruza;
    private int cMutacion;
    private ArrayList<Poblacion> generaciones = new ArrayList();
    private int[] materialesIng;
    private Random suerte = new Random();
//    private Archivador archivador;
    private Formatter formato = new Formatter();
    private boolean pausado = false;

    public void ejecutar() throws InterruptedException {
//        archivador = new Archivador(Calendar.getInstance().getTime().getHours()
//                + " " + Calendar.getInstance().getTime().getMinutes() + " "
//                + Calendar.getInstance().getTime().getSeconds()+".txt");
        int iteracionActual = 0;
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
            Poblacion copia = new Poblacion(listaAnterior, PROB_FIJA, RMIN);
//            probMutacion = calcularProbMutacion(iteracionActual, probMutacion);
            Poblacion actual = new Poblacion(copia.seleccionElitista(cSeleccion - convertPorcentACant(.8f, cSeleccion)), PROB_FIJA, RMIN);
            actual.getPoblado().addAll(copia.seleccionRuleta(convertPorcentACant(.8f, cSeleccion)));
            actual.getPoblado().addAll(copia.cruzarPoblacion(cCruza));
            actual.getPoblado().addAll(copia.mutarPoblacion(cMutacion));
            actual.evaluarAptitud(materialesIng);
            generaciones.add(actual);
//            getPropertyChangeSupport().firePropertyChange("generaciones",generacAnterior,actual);
//            Thread.sleep(10);
//            ArrayList<Individuo> prueba = (ArrayList<Individuo>) actual.getPoblado().clone();
//            Collections.sort(prueba);

//            System.out.println(new DecimalFormat("#.##").format(generacAnterior.getAptitudPromedio()));
//            System.out.println(prueba.get(0).getUtilidad() +" , " +prueba.get(800).getUtilidad());
//            for (Individuo indiv : actual.getPoblado()) {
//                archivador.agregarRegistros(indiv);
//            }
//            archivador.agregar("-----------------------------");
            iteracionActual++;
            publish(actual);
            int progreso = iteracionActual / (CANTIDAD_ITERACIONES / 100);
            System.out.println(progreso);
            setProgress(progreso);
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

            System.out.println();
//            archivador.cerrarArchivo();
        }
    }

    public ArrayList<Individuo> generarPoblacionInicial() {
        ArrayList<Individuo> nueva = new ArrayList<>();
        for (int i = 0; i < CANTIDAD_POBLACION; i++) {
            Individuo nuevo = new Individuo(suerte.nextInt((int) (gokuFase4.getP1() * 1.2)),
                    suerte.nextInt((int) (gokuFase4.getP2() * 1.2)),
                    suerte.nextInt((int) (gokuFase4.getP3() * 1.2)),
                    suerte.nextInt((int) (gokuFase4.getP4() * 1.2)));
            nueva.add(nuevo);
        }
        gokuFase4.calcularBase();
        Poblacion nuevaPob = new Poblacion(nueva, 0f, 0);
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

    @Override
    protected Boolean doInBackground() throws Exception {
        ejecutar();
        return true;
    }

    @Override
    protected void process(List<Poblacion> chunks) {
        //aca deberia imprimr en la consola los res parciales
    }
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pausar")) {
                getPropertyChangeSupport().firePropertyChange("generacion", generaciones.get(generaciones.size()-2),
                        generaciones.get(generaciones.size() - 1));
            }
        }
    };

    public ActionListener getAl() {
        return al;
    }
}
