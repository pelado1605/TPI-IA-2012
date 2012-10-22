/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author Ruben
 */
public class Asd extends SwingWorker<Boolean, Integer> {

    private static final int CANTIDAD = 100;
    private int cont = 0;
    private boolean llego = false;
    private boolean parar = false;
    private PropertyChangeListener pcl = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            setParar((boolean) evt.getNewValue());
            System.out.println(parar);
        }
    };
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setParar(!parar);
        }
    };

    public ActionListener getAl() {
        return al;
    }

    public PropertyChangeListener getPcl() {
        return pcl;
    }

    public boolean isLlego() {
        return llego;
    }

    public void setLlego(boolean llego) {
        this.llego = llego;
    }

    public boolean isParar() {
        return parar;
    }

    public void setParar(boolean parar) {
        boolean viejo = this.parar;
        this.parar = parar;
        firePropertyChange("parar", viejo, parar);
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    @Override
    protected synchronized Boolean doInBackground() throws Exception {
        while (!llego) {
            if (!parar) {
                wait();
            }
            cont++;
            System.out.println("**" + Thread.currentThread().getName());
            publish(cont);
            System.out.println("-----------" + cont);
            Thread.sleep(300);
            setProgress(cont);
            if (cont == CANTIDAD) {
                llego = true;
            }
        }
        return llego;
    }

    @Override
    protected void done() {
        System.out.println("termino .-");
        System.out.println("-" + getProgress());
        System.out.println("-");

    }

    @Override
    protected void process(List<Integer> chunks) {
        for (Integer integer : chunks) {
            System.out.println(integer);
        }
    }
}
