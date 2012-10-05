/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ruben
 */
public class Generaciones {

    public static final int CANTIDAD_POBLACION = 1000;
    public static final int CANTIDAD_ITERACIONES = 1000;
    public int[] cantidadesMaximas;
    public float aptitudMaxima;
    private int cSeleccion;
    private int cCruza;
    private int cMutacion;
    private ArrayList<Poblacion> generaciones = new ArrayList();
    private ArrayList<Integer> materiales;
    private Random suerte = new Random();

    public Generaciones(float pSeleccion, float pCruza, int cantPoblacion, int cantIteraciones,
            ArrayList<Integer> materiales) {
        this.cSeleccion = convertPorcentACant(pSeleccion);
        this.cCruza = convertPorcentACant(pCruza);
        this.cMutacion = cantPoblacion - cCruza - cSeleccion;
        this.materiales = materiales;
        
    }
    
    private void calcularMaximoIndividuo(){
        Individuo indi = new Individuo();
       
        
    }
    
    private int convertPorcentACant(float porciento) {
        int cantidad = (int) (porciento * CANTIDAD_POBLACION);
        return cantidad;
    }

    public void ejecutar() {

//        generaciones.add(new Poblacion(null, cMutacion, cCruza));
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
    
    public ArrayList<Individuo> generarPoblacionInicial(int cantidad){
        ArrayList<Individuo> nueva = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            suerte.nextInt();
        }
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
}
