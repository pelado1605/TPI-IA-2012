/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Ruben
 */
public abstract class Configuracion {

    /**
     * Tamaño de la población que se utilizará en el algoritmo. El número indica
     * cuantos individuos existirán dentro de una población.
     */
    public static final int CANTIDAD_POBLACION_DEFAULT = 250;
    /**
     * Cantidad de iteraciones que realizará el algoritmo. El número indica
     * cuantas veces se hará la selección, cruza y mutación de los individuos.
     * Definirá la cantidad de generaciones que existirán.
     */
    public static final int CANTIDAD_ITERACIONES_DEFAULT = 1000;
    public static final float PORC_SELECCION_DEFAULT = 0.2f;
    public static final float PORC_CRUZA_DEFAULT = 0.65f;
    /**
     * Número de copias esperadas que se utilizará el algoritmo. Definido para
     * utilizar en la selección por ranking.
     */
    public static final float RMIN_DEFAULT = 0.5f;
    /**
     * Probabilidad de mutación mínima. Utilizado para la mutación por
     * temperatura ascendente/descendente.
     */
    public static final float PROB_MUT_MIN_DEFAULT = 0.02f;
    /**
     * Probabilidad de mutación máxima. Utilizado para la mutación por
     * temperatura ascendente/descendente.
     */
    public static final float PROB_MUT_MAX_DEFAULT = 0.3f;
    /**
     * Probabilidad fija de mutación. Utilizado para determinar la probabilidad
     * de mutación de un individuo.
     *
     * @deprecated NO SE UTILIZA, ya que se define una cantidad de población a
     * ser mutada.
     */
    public static final float PROB_FIJA_DEFAULT = 1f;
    /**
     * Factor Lambda para la mutación por temperatura. Incremento (o decremento)
     * de la probabilidad de mutación por temperatura ascendente (o descendente)
     * por cada iteración ejecutada.
     */
    public static final float LAMBDA_DEFAULT = 0.005f;
    
        /**
     * Tamaño de la población que se utilizará en el algoritmo. El número indica
     * cuantos individuos existirán dentro de una población.
     */
    private static int CANTIDAD_POBLACION = CANTIDAD_POBLACION_DEFAULT;
    /**
     * Cantidad de iteraciones que realizará el algoritmo. El número indica
     * cuantas veces se hará la selección, cruza y mutación de los individuos.
     * Definirá la cantidad de generaciones que existirán.
     */
    private static int CANTIDAD_ITERACIONES = CANTIDAD_ITERACIONES_DEFAULT;
    private static float PORC_SELECCION = PORC_SELECCION_DEFAULT;
    private static float PORC_CRUZA = PORC_CRUZA_DEFAULT;
    /**
     * Número de copias esperadas que se utilizará el algoritmo. Definido para
     * utilizar en la selección por ranking.
     */
    private static float RMIN = RMIN_DEFAULT;
    /**
     * Probabilidad de mutación mínima. Utilizado para la mutación por
     * temperatura ascendente/descendente.
     */
    private static float PROB_MUT_MIN = PROB_MUT_MIN_DEFAULT;
    /**
     * Probabilidad de mutación máxima. Utilizado para la mutación por
     * temperatura ascendente/descendente.
     */
    private static float PROB_MUT_MAX = PROB_MUT_MAX_DEFAULT;
    /**
     * Probabilidad fija de mutación. Utilizado para determinar la probabilidad
     * de mutación de un individuo.
     *
     * @deprecated NO SE UTILIZA, ya que se define una cantidad de población a
     * ser mutada.
     */
    private static float PROB_FIJA = PROB_FIJA_DEFAULT;
    /**
     * Factor Lambda para la mutación por temperatura. Incremento (o decremento)
     * de la probabilidad de mutación por temperatura ascendente (o descendente)
     * por cada iteración ejecutada.
     */
    public static float LAMBDA = LAMBDA_DEFAULT;

    public static int getCANTIDAD_POBLACION() {
        return CANTIDAD_POBLACION;
    }

    public static void setCANTIDAD_POBLACION(int CANTIDAD_POBLACION) {
        Configuracion.CANTIDAD_POBLACION = CANTIDAD_POBLACION;
    }

    public static int getCANTIDAD_ITERACIONES() {
        return CANTIDAD_ITERACIONES;
    }

    public static void setCANTIDAD_ITERACIONES(int CANTIDAD_ITERACIONES) {
        Configuracion.CANTIDAD_ITERACIONES = CANTIDAD_ITERACIONES;
    }

    public static float getPORC_SELECCION() {
        return PORC_SELECCION;
    }

    public static void setPORC_SELECCION(float PORC_SELECCION) {
        Configuracion.PORC_SELECCION = PORC_SELECCION;
    }

    public static float getPORC_CRUZA() {
        return PORC_CRUZA;
    }

    public static void setPORC_CRUZA(float PORC_CRUZA) {
        Configuracion.PORC_CRUZA = PORC_CRUZA;
    }

    public static float getRMIN() {
        return RMIN;
    }

    public static void setRMIN(float RMIN) {
        Configuracion.RMIN = RMIN;
    }

    public static float getPROB_MUT_MIN() {
        return PROB_MUT_MIN;
    }

    public static void setPROB_MUT_MIN(float PROB_MUT_MIN) {
        Configuracion.PROB_MUT_MIN = PROB_MUT_MIN;
    }

    public static float getPROB_MUT_MAX() {
        return PROB_MUT_MAX;
    }

    public static void setPROB_MUT_MAX(float PROB_MUT_MAX) {
        Configuracion.PROB_MUT_MAX = PROB_MUT_MAX;
    }

    public static float getPROB_FIJA() {
        return PROB_FIJA;
    }

    public static void setPROB_FIJA(float PROB_FIJA) {
        Configuracion.PROB_FIJA = PROB_FIJA;
    }

    public static float getLAMBDA() {
        return LAMBDA;
    }

    public static void setLAMBDA(float LAMBDA) {
        Configuracion.LAMBDA = LAMBDA;
    }
    
    
}
