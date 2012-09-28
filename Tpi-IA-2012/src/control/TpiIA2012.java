/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Individuo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.event.ListSelectionEvent;

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
        
//        byte cortes[] = {3,1,5,2,6,7,2};
//         ArrayList cortesList = new ArrayList();
//        for (int i = 0; i < cortes.length; i++) {
//            cortesList.add(cortes[i]);
//        }
//        Collections.sort(cortesList);
//        for (Object e : cortesList) {
//            System.out.print(e+",");
//        }
        
//        Individuo ind1 = new Individuo(0.4f);
//        Individuo ind2 = new Individuo(-20f);
//        Individuo ind3 = new Individuo(16.9f);
//        Individuo ind4 = new Individuo(0.4f);
//        Individuo ind5 = new Individuo(-200.4f);
//        
//        ArrayList<Individuo> lista = new ArrayList();
//        lista.add(ind1);
//        lista.add(ind2);
//        lista.add(ind3);
//        lista.add(ind4);
//        lista.add(ind5);
//        
//        Collections.sort(lista);
//        
//        for (Individuo individuo : lista) {
//            System.out.println(individuo.getAptitud());
//        }
        
        Individuo ind1 = new Individuo(1,1,1,1);
        Individuo ind2 = new Individuo(3, 3, 3, 3);
        List<Integer> lista = new ArrayList(Arrays.asList());
        Individuo[] hijos = ind1.cruzaMultiPunto(ind2, lista);
        for (int i = 0; i < hijos.length; i++) {
            System.out.print(hijos[i].getP1()+",");
            System.out.print(hijos[i].getP2()+",");
            System.out.print(hijos[i].getP3()+",");
            System.out.print(hijos[i].getP4()+",");
            System.out.println();
        }
    }
}
