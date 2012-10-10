/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;
import java.util.Random;

/**
 *
 * @author Ruben
 */
public class TpiIA2012 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int k = 1; k < 11; k++) {
            
        System.out.println("iteracion con base: " + 1000*k + " variacion: 1000" );
        for (int i = 0; i < 100; i++) {
//          int[] mIngresados = {5000,5000,5000,5000,5000,5000,5000,5000}; // leo 87 142 36 0 -  $28480
        Random r = new Random();
        int[] mIngresados = new int[8];
        for (int j = 0; j < 8; j++) {
            mIngresados[j] = (1000*k) + r.nextInt(1000);
        }
        Generaciones generacion = new Generaciones(0.20f, 0.6f, mIngresados);
        generacion.ejecutar();
        };
//        System.out.println("Sele Elitista "+Poblacion.getCont_selecElitista());
//        System.out.println("Sele Ruleta "+Poblacion.getCont_selecRuleta());
//        System.out.println("Sele Ranking "+Poblacion.getCont_selecRanking());
//        System.out.println("Sele ContCopias "+Poblacion.getCont_selecContCopias());
//        System.out.println();
//        System.out.println("Cruza Simple "+Individuo.getCont_cruzaSimple());
//        System.out.println("Cruza Multipunto "+Individuo.getCont_cruzaMultipunto());
//        System.out.println("Cruza Binomial "+Individuo.getCont_cruzaBinomial());

    }
    }
}
