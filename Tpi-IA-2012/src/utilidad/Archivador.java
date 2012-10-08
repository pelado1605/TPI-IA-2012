/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidad;

import dominio.Individuo;
import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * @author Ruben
 */
public class Archivador implements Runnable {

    private Formatter salida;
    private String nombreArchivo;

    public Archivador(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void abrirArchivo() {
        try {
            salida = new Formatter(nombreArchivo);
        } catch (SecurityException securityException) {
            System.err.println("NO TIENE ACCESO DE ESCRITURA AL ARCHIVO");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("ERROR AL CREAR EL ARCHIVO");
            System.exit(1);
        }
    }

    public void agregarRegistros(Individuo individuo) {
        
        salida.format("(%d %d %d %d) , %.2f\n",
                individuo.getP1(),
                individuo.getP2(),
                individuo.getP3(),
                individuo.getP4(),
                individuo.getAptitud());
        salida.format("%n");
    }
    
    public void agregar(String string){
        salida.format("%s %n", string);
    }
    

    public void cerrarArchivo() {
        salida.close();
    }

    @Override
    public void run() {
    }
}
