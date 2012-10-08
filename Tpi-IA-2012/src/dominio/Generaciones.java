/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import utilidad.Archivador;

/**
 *
 * @author Ruben
 */
public class Generaciones {

    public static final int CANTIDAD_POBLACION = 1000;
    public static final int CANTIDAD_ITERACIONES = 200;
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
    private Archivador archivador;

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

    public void ejecutar() {
        archivador = new Archivador(Calendar.getInstance().getTime().getHours()+
                " "+Calendar.getInstance().getTime().getMinutes()+" "+
                Calendar.getInstance().getTime().getSeconds());
        int iteracionActual = 0;
        float probMutacion = 0;
        generarPoblacionInicial();
        generaciones.get(0).evaluarAptitud(materialesIng);
        archivador.abrirArchivo();
        while (CANTIDAD_ITERACIONES > iteracionActual) {
            Poblacion anterior = generaciones.get(iteracionActual);
//            probMutacion = calcularProbMutacion(iteracionActual, probMutacion);
            Poblacion actual = new Poblacion(anterior.seleccionRuleta(cSeleccion - convertPorcentACant(0.9f, cSeleccion)), PROB_FIJA,RMIN);
            actual.getPoblado().addAll(anterior.seleccionElitista(convertPorcentACant(.10f, cSeleccion)));
            actual.getPoblado().addAll(anterior.cruzarPoblacion(cCruza));
            actual.getPoblado().addAll(anterior.mutarPoblacion(cMutacion));
            actual.evaluarAptitud(materialesIng);
            generaciones.add(actual);
//            for (Individuo indiv : actual.getPoblado()) {
//                archivador.agregarRegistros(indiv);
//            }
//            archivador.agregar("-----------------------------");
            iteracionActual++;
        }

        System.out.println("Aptitud Goku : " + gokuFase4.getAptitud());
        System.out.println("Indiv Goku : " + gokuFase4.mostrarProductos());
        System.out.println("Utilidad Goku : " + gokuFase4.getUtilidad());
        System.out.println();
        for (int i = 0; i < 3; i++) {
            Collections.sort(generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado());
            System.out.println("Aptitud : " + generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado().get(i).getAptitud());
            System.out.println("Individuo : " + generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado().get(i).mostrarProductos());
            System.out.println("Utilidad : " + generaciones.get(CANTIDAD_ITERACIONES - 1).getPoblado().get(i).getUtilidad());
//            int[] mat = Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).calcularMaterialesMinimos();
//            for (int j : mat) {
//                System.out.print(j + ",");
//            }
            System.out.println();
            archivador.cerrarArchivo();
        }
    }

    public ArrayList<Individuo> generarPoblacionInicial() {
        ArrayList<Individuo> nueva = new ArrayList<>();
        for (int i = 0; i < CANTIDAD_POBLACION; i++) {
            Individuo nuevo = new Individuo(suerte.nextInt(gokuFase4.getP1()),
                    suerte.nextInt(gokuFase4.getP2()),
                    suerte.nextInt(gokuFase4.getP3()),
                    suerte.nextInt(gokuFase4.getP4()));
            nueva.add(nuevo);
        }
        Poblacion nuevaPob = new Poblacion(nueva, 0f, 0);
        generaciones.add(nuevaPob);
        return nueva;
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
}
