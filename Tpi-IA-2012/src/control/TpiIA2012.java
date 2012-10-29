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
import visual.Grafica;
import visual.Principal;

/**
 * Clase principal del programa. Es la que inicializa la ejecución de la
 * aplicación.
 * 
 * @author Ruben
 */
public class TpiIA2012 {

    /**
     * Materiales ingresados por el usuario. Arreglo de enteros correspondientes
     * a los materiales m1..m8.
     */
    int[] mIngresados;
    /**
     * Generaciones que maneja el algoritmo genético. Conjunto de las
     * generaciones, con las poblaciones y sus individuos.
     */
    private Generaciones generaciones;
    /**
     * Interfaz gráfica, pantalla principal de la aplicación.
     */
    private Principal vPrincipal = new Principal();
    /**
     * Interfaz grafica, pantalla de gráficas del avance de las generaciones.
     */
    private Grafica grafica = new Grafica("Gráfica del avance de las generaciones");

    /**
     * Constructor de la clase. Incluye escuchadores de acciones y muestra la
     * pantalla principal.
     */
    public TpiIA2012() {

        vPrincipal.getEjecutarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPausarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPararButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPasoApasoButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getGraficasButton().addActionListener(actionListenerEjecutar);
        vPrincipal.setVisible(true);
    }
    /**
     * Escuchadores de acciones necesarios para la ejecución de la aplicación.
     * Tiene las acciones a realizar según el evento ocurrido.
     */
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

    /**
     * Inicializa la ejecución del algoritmo. Se utiliza ya que se puede
     * ejecutar varias veces el algoritmo, en una corrida del programa.
     */
    private void inicializarEjecucion() {
        grafica = new Grafica("Gráfica del avance de las generaciones");
        if (generaciones != null) {
            generaciones.removePCl(vPrincipal.getPclModelo());
            generaciones.removePCl(grafica.getPclModel());
        }
        vPrincipal.getPararButton().removeAll();
        vPrincipal.getPausarButton().removeAll();
        generaciones = new Generaciones(.2f, .5f, vPrincipal.getMatIngs());
        generaciones.addPCl(vPrincipal.getPclModelo());
        generaciones.addPCl(grafica.getPclModel());
        vPrincipal.getPausarButton().addActionListener(generaciones.getAl());//para que imprima 2generac. en el comando
        vPrincipal.getPararButton().addActionListener(generaciones.getAl());//lo mismo que arriba
        vPrincipal.getPasoApasoButton().addActionListener(generaciones.getAl());//lo mismo que arriba
    }

    /**
     * El main del programa.
     * 
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
