/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jfree.ui.RefineryUtilities;
import visual.Grafica1;
import visual.Principal;

/**
 *
 * @author Ruben
 */
public class TpiIA2012 {

    int[] mIngresados = {5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000};
    private Generaciones generaciones;
    private Principal vPrincipal = new Principal();
    private Grafica1 grafica;

    public TpiIA2012() {

        vPrincipal.getEjecutarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPausarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPararButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPasoApasoButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getGraficasButton().addActionListener(actionListenerEjecutar);
        vPrincipal.setVisible(true);
    }
    ActionListener actionListenerEjecutar = new ActionListener() {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Ejecutar")) {
                if (vPrincipal.isParado()) {
                    inicializarEjecucion();
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
                generaciones.cancel(true);
            }
            if (e.getActionCommand().equals("Ste. Iteracion")) {
                if (vPrincipal.isParado()) {
                    inicializarEjecucion();
                    generaciones.setPausado(true);
                    generaciones.execute();
                }
                synchronized (generaciones) {
                    generaciones.notify();
                }
            }
            if (e.getActionCommand().equals("Graficas")) {
                grafica.pack();
                RefineryUtilities.centerFrameOnScreen(grafica);
                grafica.setVisible(true);
            }

        }
    };

    private void inicializarEjecucion() {
        grafica = new Grafica1("Super Grafica");
        if (generaciones != null) {
            generaciones.removePCl(vPrincipal.getPclModelo());
            generaciones.removePCl(grafica.getPclModel());
        }
        vPrincipal.getPararButton().removeAll();
        vPrincipal.getPausarButton().removeAll();
        generaciones = new Generaciones(.2f, .6f, vPrincipal.getMatIngs());
        generaciones.addPCl(vPrincipal.getPclModelo());
        generaciones.addPCl(grafica.getPclModel());
        vPrincipal.getPausarButton().addActionListener(generaciones.getAl());//para que imprima 2generac. en el comando
        vPrincipal.getPararButton().addActionListener(generaciones.getAl());//lo mismo que arriba
        vPrincipal.getPasoApasoButton().addActionListener(generaciones.getAl());//lo mismo que arriba
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        TpiIA2012 tpiIA2012 = new TpiIA2012();
//        tpiIA2012.generaciones.execute();
    }
}
