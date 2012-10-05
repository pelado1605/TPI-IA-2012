/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Ruben
 */
public class Generaciones {
    
    
    private int cSeleccion;
    private int cCruza;
    private int cMutacion;
    private int cantPoblacion;
    

    public Generaciones(float pSeleccion, float pCruza, int cantPoblacion) {
        this.cantPoblacion = cantPoblacion;
        this.cSeleccion = convertPorcentACant(pSeleccion);
        this.cCruza = convertPorcentACant(pCruza);
        this.cMutacion = cantPoblacion - cCruza - cSeleccion;
    };
    
    private int convertPorcentACant(float porciento){
        int cantidad = (int) (porciento * cantPoblacion);
        return cantidad;
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

    public int getCantPoblacion() {
        return cantPoblacion;
    }
    
}
