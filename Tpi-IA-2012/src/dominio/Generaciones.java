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
public class Generaciones {

    public static final int CANTIDAD_POBLACION = 1000;
    public static final int CANTIDAD_ITERACIONES = 1000;
    public static Individuo gokuFase4;
    public float aptitudMaxima;
    private int cSeleccion;
    private int cCruza;
    private int cMutacion;
    private ArrayList<Poblacion> generaciones = new ArrayList();
    private int[] materiales;
    private Random suerte = new Random();

    public Generaciones(float pSeleccion, float pCruza,
            int[] materiales) {
        this.cSeleccion = convertPorcentACant(pSeleccion);
        this.cCruza = convertPorcentACant(pCruza);
        this.cMutacion = CANTIDAD_POBLACION - cCruza - cSeleccion;
        this.materiales = materiales;
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
                    minimos.add(materiales[j] / Individuo.MMinimos[i][j]);
                }
            }
            superSayayin.setProducto(i, Collections.min(minimos));

        }
        superSayayin.evaluarAptitud(materiales, true);
        gokuFase4 = superSayayin;
    }

    private int convertPorcentACant(float porciento) {
        int cantidad = (int) (porciento * CANTIDAD_POBLACION);
        return cantidad;
    }

    public void ejecutar() {
//        Generaciones.get(0).evaluarAptitud(mIngresados);
//        while (!condicionParada(cantidadIteraciones)) {
//            Poblacion actual = Generaciones.get(iteracionActual);
//            Poblacion seleccionada = actual.seleccionarPoblacion();
//            /*
//             * BASTANTE CHOTO ESTE METODO DE CRUZA...cREO QUE ESTA MAL, RECIBE
//             * DE PARAMETRO LA POBLACION QUE VA A RECIBIR LA CRUZA. VER.
//             */
//            actual.cruzarPoblacion(seleccionada);
//            seleccionada.mutarPoblacion(iteracionActual);
//            seleccionada.evaluarAptitud(mIngresados);
//            System.out.println(seleccionada.getIndividuo(0).evaluarAptitud(mIngresados));
//            Generaciones.add(seleccionada);
//            iteracionActual++;
//        }
//        for (int i = 0; i < 3; i++) {
//
//            Generaciones.get(CANTIDAD_ITERACIONES - 1).ordenarPobladoPorAptitud();
//            System.out.println("Aptitud " + Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).getAptitud());
//            System.out.print(Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).getP1() + " ");
//            System.out.print(Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).getP2() + " ");
//            System.out.print(Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).getP3() + " ");
//            System.out.println(Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).getP4() + " ");
//            System.out.println("Utilidad " + Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).calcularUtilidad());
//            int[] mat = Generaciones.get(CANTIDAD_ITERACIONES - 1).getIndividuo(i).calcularMaterialesMinimos();
//            for (int j : mat) {
//                System.out.print(j + ",");
//            }
//            System.out.println();
//        }
    }

    public ArrayList<Individuo> generarPoblacionInicial(int cantidad) {
        ArrayList<Individuo> nueva = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
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
