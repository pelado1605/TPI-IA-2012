/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visual.Principal;

/**
 *
 * @author Ruben
 */
public class TpiIA2012 {

    int[] mIngresados = {5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000};
    private Generaciones generaciones;
    private Principal vPrincipal = new Principal();

    public TpiIA2012() {

        vPrincipal.getEjecutarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPausarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPararButton().addActionListener(actionListenerEjecutar);
        vPrincipal.setVisible(true);
    }
    ActionListener actionListenerEjecutar = new ActionListener() {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Ejecutar")) {
                if (vPrincipal.isParado()) {
                    System.out.println("entró");
//                    vPrincipal.getPararButton().removeActionListener(generaciones.getAl());
//                    vPrincipal.getPausarButton().removeActionListener(generaciones.getAl());
                    if (generaciones != null) {
                        generaciones.removePCl(vPrincipal.getPclModelo());
                    }
                    vPrincipal.getPararButton().removeAll();
                    vPrincipal.getPausarButton().removeAll();
                    generaciones = new Generaciones(.2f, .6f, vPrincipal.getMatIngs());
                    generaciones.addPCl(vPrincipal.getPclModelo());
                    vPrincipal.getPausarButton().addActionListener(generaciones.getAl());//para que imprima 2generac. en el comando
                    vPrincipal.getPararButton().addActionListener(generaciones.getAl());//lo mismo que arriba
                    generaciones.execute();
                } else {
                    synchronized (generaciones) {
                        generaciones.notify();
                        generaciones.setPausado(false);
                    }
                }
            }
            if (e.getActionCommand().equals("Pausar")) {
                if (vPrincipal.isEjecutandose()) {
                    generaciones.setPausado(true);
                }
            }
            if (e.getActionCommand().equals("Parar")) {
                System.out.println("aaasdasdasdasdasdasdasdasdadasdasdsd");
                generaciones.cancel(true);
            }
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TpiIA2012 tpiIA2012 = new TpiIA2012();
//        tpiIA2012.generaciones.execute();
    }
}
