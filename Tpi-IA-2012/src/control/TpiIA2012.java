/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dominio.Generaciones;
import dominio.Poblacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jfree.ui.RefineryUtilities;
import visual.Configuracion;
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
    private Principal vPrincipal;
    /**
     * Interfaz grafica, pantalla de gráficas del avance de las generaciones.
     */
    private Grafica grafica = new Grafica("Gráfica del avance de las generaciones");
    private Configuracion configuracion;

    /**
     * Constructor de la clase. Incluye escuchadores de acciones y muestra la
     * pantalla principal.
     */
    public TpiIA2012() {
        vPrincipal = new Principal();
        vPrincipal.getEjecutarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPausarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPararButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getPasoApasoButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getGraficasButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getLimpiarButton().addActionListener(actionListenerEjecutar);
        vPrincipal.getGraficaMenuItem().addActionListener(actionListenerEjecutar);
        vPrincipal.getConfigButton().addActionListener(actionListenerConfig);
        vPrincipal.getConfigMenuItem().addActionListener(actionListenerConfig);
        vPrincipal.setVisible(true);
        configuracion = new Configuracion(vPrincipal, true);
    }
    /**
     * Escuchadores de acciones necesarios para la ejecución de la aplicación.
     * Tiene las acciones a realizar según el evento ocurrido. Los eventos a los
     * que responde son: Ejecución, Pausa, Parada y Paso a paso del algoritmo.
     * Además tambien responde a los eventos de mostrar la gáfica y limpiar la
     * pantalla.
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
            if (e.getActionCommand().equals("Siguiente")) {
                if (vPrincipal.isParado()) {
                    inicializarEjecucion();
                    generaciones.setPausado(true);
                    generaciones.execute();
                }
                synchronized (generaciones) {
                    generaciones.notify();
                }
            }
            if (e.getActionCommand().equals("Graficas")
                    || e.getActionCommand().equals(
                    "Mejor, Peor individuo y aptitud promedio")) {
                grafica.pack();
                RefineryUtilities.centerFrameOnScreen(grafica);
                grafica.setVisible(true);
            }
            if (e.getActionCommand().equals("Limpiar")) {
                grafica.dispose();
                grafica = new Grafica("Gráfica del avance de las generaciones");
            }
        }
    };
    /**
     * Escuchadores de acciones necesarios para la configuración de la
     * aplicación. Responde al evento de mostrar la configuración para revisarla
     * o editarla.
     */
    private ActionListener actionListenerConfig = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            configuracion.setVisible(true);
        }
    };

    /**
     * Inicializa la ejecución del algoritmo. Se utiliza ya que se puede
     * ejecutar varias veces el algoritmo, en una corrida del programa.
     */
    private void inicializarEjecucion() {
        grafica.dispose();
        grafica = new Grafica("Gráfica del avance de las generaciones");
        if (generaciones != null) {
            generaciones.removePCl(vPrincipal.getPclModelo());
            generaciones.removePCl(grafica.getPclModel());
        }
        vPrincipal.getPararButton().removeAll();
        vPrincipal.getPausarButton().removeAll();
        int tSeleccion = configuracion.getTipoSelec();
        int cantGrupos = 0;
        if (tSeleccion == Poblacion.ELITISTA_TORNEO || tSeleccion == Poblacion.TORNEO) {
            cantGrupos = Integer.valueOf(configuracion.getPorTorneoTextField().getText());
        }
        int tCruza = configuracion.getTipoCruza();
        generaciones = new Generaciones(tSeleccion, cantGrupos, tCruza, vPrincipal.getMatIngs());
        generaciones.addPCl(vPrincipal.getPclModelo());
        generaciones.addPCl(grafica.getPclModel());
        vPrincipal.getPausarButton().addActionListener(generaciones.getAl());
        vPrincipal.getPararButton().addActionListener(generaciones.getAl());
        vPrincipal.getPasoApasoButton().addActionListener(generaciones.getAl());
    }

    /**
     * El main del programa.
     *
     * @param args Los parametros de ejecución del sistema
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
    }
}
