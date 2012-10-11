/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.SwingWorker;

/**
 *
 * @author Ruben
 */
public class Control {

    private Asd asd;
    private Ventana ventana;
    private boolean estado = false; //false=parado , true= corriendo
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private PropertyChangeListener pcl = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("progress".equals(evt.getPropertyName())) {
                ventana.getjProgressBar1().setValue((int) evt.getNewValue());
            }
            if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
                ventana.getjProgressBar1().setValue(0);
            }
            
        }
    };

    public void addPCL(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public Control() {
        ventana = new Ventana();
        asd = new Asd();
        asd.addPropertyChangeListener(pcl);
        ventana.getjButton1().addActionListener(actionListener);
        ventana.getjButton1().addActionListener(asd.getAl());
        pcs.addPropertyChangeListener(asd.getPcl());
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        boolean viejo = this.estado;
        this.estado = estado;
        pcs.firePropertyChange("parar", viejo, estado);
    }

    public Asd getAsd() {
        return asd;
    }

    public Ventana getVentana() {
        return ventana;
    }

    public PropertyChangeListener getPcl() {
        return pcl;
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        @SuppressWarnings("empty-statement")
        public synchronized void actionPerformed(ActionEvent e) {
            setEstado(!estado);
            System.out.println("---"+Thread.currentThread().getName());
            if (asd.getState() == SwingWorker.StateValue.PENDING) {
                asd.execute();
            } else {
                if (estado){
                    System.out.println("notify");
                    synchronized (asd) {
                        asd.notify();
                    }
                    ventana.repaint();
                }
            }
        }
    };

    public ActionListener getActionListener() {
        return actionListener;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Control control = new Control();
                control.getVentana().run();
            }
        });

    }
}
