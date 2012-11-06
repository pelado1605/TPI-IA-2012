/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa a un individuo dentro de la población de una generación.
 * Tiene sus atributos y métodos para su manejo.
 *
 * @author Ruben
 */
public class Individuo implements Comparable<Individuo>, Cloneable {

    /**
     * Factor multiplicativo de la utilidad para calcular la aptitud. Es un
     * float. Útil para probar distintas combinaciones para calcular la aptitud.
     */
    private static final float PORC_APTITUD_X_UTILIDAD = 2f;
    /**
     * Factor multiplicativo de la factibilidad para calcular la aptitud. Es un
     * float. Útil para probar distintas combinaciones para calcular la aptitud.
     */
    private static final float PORC_APTITUD_X_FACTIBILIDAD = 1f;
    /**
     * Factor multiplicativo de la eficiencia para calcular la aptitud. Es un
     * float. Útil para probar distintas combinaciones para calcular la aptitud.
     */
    private static final float PORC_APTITUD_X_EFICIENCIA = 1f;
    /**
     * Cantidad de veces que se realizó una cruza binomial. Sirve para saber
     * cuantas veces se realizó este tipo de cruza durante la ejecución de la
     * aplicación.
     */
    private static int cont_cruzaBinomial = 0;
    /**
     * Cantidad de veces que se realizó una cruza multipunto. Sirve para saber
     * cuantas veces se realizó este tipo de cruza durante la ejecución de la
     * aplicación.
     */
    private static int cont_cruzaMultipunto = 0;
    /**
     * Cantidad de veces que re realizó una cruza simple. Sirve para saber
     * cuantas veces se realizó este tipo de cruza durante la ejecución de la
     * aplicación.
     */
    private static int cont_cruzaSimple = 0;
    /**
     * Materiales mínimos necesarios para realizar un producto de p1, p2, p3 y
     * p4. Está formado por un array de dos dimensiones, cuyo primer índice
     * apunta al producto, y el segundo índice apunta al material de ese
     * producto.
     */
    public static final byte[][] MMinimos = {
        {80, 0, 44, 72, 0, 55, 22, 0},
        {15, 49, 12, 0, 50, 21, 0, 70},
        {0, 84, 0, 34, 62, 62, 43, 0},
        {41, 0, 0, 74, 24, 82, 55, 52}
    };
    /**
     * Materiales máximos necesarios para realizar un producto de p1, p2, p3 y
     * p4. Está formado por un array de dos dimensiones, cuyo primer índice
     * apunta al producto, y el segundo índice apunta al material de ese
     * producto.
     */
    public static final byte[][] MMaximos = {
        {95, 0, 65, 88, 0, 77, 50, 0},
        {35, 55, 28, 0, 60, 44, 0, 91},
        {0, 99, 0, 55, 78, 70, 65, 0},
        {55, 0, 0, 95, 35, 88, 74, 78}
    };
    /**
     * Rangos de cantidad de productos (entre mínimo y máximo) necesarios de p1,
     * p2, p3 y p4. Está formado por un array de dos dimensiones, cuyo primer
     * índice apunta al producto, y el segundo apunta al material de ese
     * producto.
     */
    public static final byte[][] Rangos = {
        {15, 0, 21, 16, 0, 22, 28, 0},
        {20, 6, 16, 0, 10, 23, 0, 21},
        {0, 15, 0, 21, 16, 8, 22, 0},
        {14, 0, 0, 21, 11, 6, 19, 26}
    };
    /**
     * Base para el cálculo de la aptitud.
     */
    private static int BASE;
    /**
     * Constante de clase para la cruza simple.
     */
    public static final int CRUZA_SIMPLE = 0;
    /**
     * Constante de clase para la cruza multipunto.
     */
    public static final int CRUZA_MULTIPUNTO = 1;
    /**
     * Constante de clase para la cruza binomial.
     */
    public static final int CRUZA_BINOMIAL = 2;
    /**
     * Array constante de los valores de la utilidad para p1, p2, p3 y p4. Son
     * los valores definidos por la consigna del problema.
     */
    public static final byte[] Utilidad = {90, 115, 120, 100};
    /**
     * Aptitud del individuo. Float mayor a cero, calculado a partir del método
     * * <i>evaluarAptitud()</i>
     */
    private float aptitud;
    /**
     * Cantidad de productos p1. Entero positivo indicando la cantidad del
     * producto 1 para el individuo.
     */
    private int p1;
    /**
     * Cantidad de productos p2. Entero positivo indicando la cantidad del
     * producto 2 para el individuo.
     */
    private int p2;
    /**
     * Cantidad de productos p3. Entero positivo indicando la cantidad del
     * producto 3 para el individuo.
     */
    private int p3;
    /**
     * Cantidad de productos p4. Entero positivo indicando la cantidad del
     * producto 4 para el individuo.
     */
    private int p4;
    /**
     * Generador de números aleatorios de uso general. Se utiliza para distintos
     * métodos que necesiten de numeros aleatorios.
     */
    private Random suerte = new Random();

    /**
     * Constructor por defecto. Aplica todos los valores en cero.
     */
    public Individuo() {
        this.aptitud = 0f;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.p4 = 0;
    }

    /**
     * Constructor con valores basados en otro individuo. Clona las cantidades
     * de productos de otro individuo.
     *
     * @param individuo Individuo a ser "clonado".
     */
    public Individuo(Individuo individuo) {
        this(individuo.getP1(), individuo.getP2(), individuo.getP3(),
                individuo.getP4());
    }

    /**
     * Constructor con valores pasados por parámetro. Permite ingresar la
     * combinación de productos para el individuo.
     *
     * @param p1 Cantidad de productos p1.
     * @param p2 Cantidad de productos p2.
     * @param p3 Cantidad de productos p3.
     * @param p4 Cantidad de productos p4.
     */
    public Individuo(int p1, int p2, int p3, int p4) {
        this.aptitud = 0f;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

//    ESTE ESTA POR PRUEBA NOMAS
    /**
     * Constructor de prueba. <b>¡¡¡ATENCIÓN!!!</b> Solo para pruebas de los
     * métodos. NO USAR. Permite definir manualmente la aptitud del individuo,
     * sin realizar el cálculo correspondiente.
     *
     * @param aptitud Valor de la aptitud del individuo.
     */
    public Individuo(float aptitud) {
        this.aptitud = aptitud;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.p4 = 0;
    }

    /**
     * Evalúa la aptitud con respecto a los materiales ingresados. Este método
     * está sobrecargado. Este método no admite al individuo "óptimo"
     * inalcanzable. Ver el otro método para mayor información.
     *
     * @param matIngs Array de materiales ingresados por el usuario en el inicio
     * de la ejecución de la aplicación.
     * @return n valor en float (mayor a cero) que identifica la aptitud de
     * dicho individuo, permitiendo usarlo como comparación frente a otros.
     */
    public float evaluarAptitud(int[] matIngs) {
        return evaluarAptitud(matIngs, false);
    }

    /**
     * Evalúa la aptitud del individuo con respecto a los materiales ingresados.
     * Dicha función está dividida en 3 partes: <br/> - Factibilidad: Verifica
     * si es posible construir dicha combinación de productos con respecto a los
     * materiales ingresados. <br/> - Utilidad: Calcula la utilidad obtenida al
     * realizar dicha combinación de productos. <br/> - Eficiencia de
     * utilización de materiales: Castigan a los individuos cuya combinación de
     * productos desperdicie materiales. <br/>
     *
     * @param matIngs Array de materiales ingresados por el usuario en el inicio
     * de la ejecución de la aplicación.
     * @param esGoku Pregunta si el individuo a calcular la aptitud es el
     * individuo Goku. (Individuo con mayor aptitud, inalcanzable).
     * @return Un valor en float (mayor a cero) que identifica la aptitud de
     * dicho individuo, permitiendo usarlo como comparación frente a otros.
     */
    public float evaluarAptitud(int[] matIngs, boolean esGoku) {

        float nuevaAptitud = BASE;
        int[] diferencia = calcDiferencia(matIngs);
        /*
         * Aca se va a preguntar por la factibilidad del individuo, es decir, si
         * los materiales ingresados por el usuario son suficientes para la demanda
         * que presenta cada individuo.
         */

        if (factibilidad(matIngs) | esGoku) {
            /*
             * En este caso el individuo es factible, hay que ver la utilidad
             * y los materiales remanentes que deja.
             * 
             * Por ser factibles se le suma una proporción de la utildidad
             */

            nuevaAptitud += Math.pow(getUtilidad(), 1.2) * PORC_APTITUD_X_UTILIDAD;

            /*
             * Aca se trata la puntuacion por la utilizacion de los recursos
             */
            if (!eficienteConRecursos(matIngs) & !esGoku) {
                /*
                 * Si hay remanente de materiales, se los descontara una proporción
                 * en puntos a la aptitud.
                 */
                int diferenciaTotal = 0;
                for (int i = 0; i < 4; i++) {
                    if (diferencia[i] < 0) {
                        diferenciaTotal += Math.abs(diferencia[i]);
                    }
                }
                nuevaAptitud -= (diferenciaTotal * PORC_APTITUD_X_EFICIENCIA);
            } else {

                /*
                 * El individuo que utilice los materiales de manera más
                 * eficiente, obtendra un premio en puntos de aptitud
                 */
                nuevaAptitud += ((nuevaAptitud * 3) * PORC_APTITUD_X_EFICIENCIA);
            }

        } else {
            /*
             * Aca, se castiga severamente al individuo con una aptitud muy
             * baja, debido a que no es factible porque pide mas materiales que
             * los ingresados/existentes.
             */
            int diferenciaTotal = 0;
            for (int i = 0; i < 8; i++) {
                if (diferencia[i] < 0) {
                    diferenciaTotal += Math.abs(diferencia[i]);
                }
            }
            nuevaAptitud = nuevaAptitud - (diferenciaTotal * PORC_APTITUD_X_FACTIBILIDAD);
        }
        if (nuevaAptitud < 0) {
            nuevaAptitud = 0.009f;
        }
        this.aptitud = nuevaAptitud;
        return nuevaAptitud;
    }

    /**
     * Cruza simple entre dos individuos. La posicion de los genes empieza en el
     * 0 y puede valer hasta 3. Este metodo devuelve un arreglo con los dos
     * hijos de la cruza
     *
     * @deprecated No se utiliza, debido a la ineficiencia.
     *
     * @param unIndividuo Individuo con el cual cruzarse.
     * @param posicion posicion a la cual cruzar [0..3].
     * @return un arreglo con los dos hijos de la cruza.
     */
    public ArrayList<Individuo> cruzaSimple(Individuo unIndividuo, byte posicion) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        hijos.add(new Individuo(getP1(), getP2(), getP3(), getP4()));
        hijos.add(new Individuo(unIndividuo.getP1(), unIndividuo.getP2(),
                unIndividuo.getP3(), unIndividuo.getP4()));

        hijos.get(0).setProducto(posicion, unIndividuo.getProducto(posicion));
        hijos.get(1).setProducto(posicion, getProducto(posicion));

        cont_cruzaSimple++;
        return hijos;
    }

    /**
     * Cruza multi punto entre dos individuos. Este metodo recibe como parametro
     * un valor entero del cual se lo analiza de manera binaria para determinar
     * las posiciones de corte para la cruza. <br/> <i>Ejemplo: para realizar un
     * corte en posiciones 2 y 3, se deberá pasar el entero 6 (0110 en binario).
     * </i> <br/> Como se ve, los 1 determinan el punto de corte y el cero donde
     * mantiene el valor del padre. Los valores 0 y 15 no se incluyen, ya que no
     * producirían corte alguno, solo copia de los padres.
     *
     * @param unIndividuo Individuo con el que se va a cruzar.
     * @param cortes entero entre 1 y 14.
     * @return un arreglo con los dos hijos de la cruza.
     */
    public ArrayList<Individuo> cruzaMultiPunto(Individuo unIndividuo, int cortes) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        hijos.add(new Individuo());
        hijos.add(new Individuo());
        for (byte i = 0; i < 4; i++) {
            int posicion = (int) Math.pow(2, i);
            int valor = (int) (cortes & posicion);
            if ((valor ^ posicion) == 0) {
                hijos.get(0).setProducto(3 - i, this.getProducto(3 - i));
                hijos.get(1).setProducto(3 - i, unIndividuo.getProducto(3 - i));
            } else {
                hijos.get(1).setProducto(3 - i, this.getProducto(3 - i));
                hijos.get(0).setProducto(3 - i, unIndividuo.getProducto(3 - i));
            }
        }
        cont_cruzaMultipunto++;
        return hijos;
    }

    /**
     * Cruza binomial entre dos individuos. Permite la realización de la cruza
     * mediante una probabilidad ingresada por parámetro (probabilidad de tomar
     * del padre o de la madre). Por cada producto se genera un número aleatorio
     * para determinar si va a tomar el gen del padre o de la madre. (el segundo
     * hijo tomara del otro progenitor)
     *
     * @param unIndividuo Individuo con el cual se realizará la cruza binomial.
     * @param probUnIndividuo Probabilidad de tomar los genes del padre o de la
     * madre.
     * @return Array de dos individuos hijos, generados por la cruza binomial.
     */
    public ArrayList<Individuo> cruzaBinomial(Individuo unIndividuo, float probUnIndividuo) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        hijos.add(new Individuo());
        hijos.add(new Individuo());
        for (int i = 0; i < 4; i++) {
            float nroAleatorio = suerte.nextFloat();
            if (nroAleatorio <= probUnIndividuo) {
                hijos.get(0).setProducto(i, this.getProducto(i));
                hijos.get(1).setProducto(i, unIndividuo.getProducto(i));
            } else {
                hijos.get(1).setProducto(i, this.getProducto(i));
                hijos.get(0).setProducto(i, unIndividuo.getProducto(i));
            }
        }
        cont_cruzaBinomial++;
        return hijos;
    }

    /**
     * Despachador de tipo de cruza. Permite realizar la cruza deseada según el
     * tipo ingresado.
     *
     * @param typoCruza Permite elegir entre los 3 tipos de cruza:</br> - Cruza
     * simple</br> - Cruza multipunto</br> - Cruza binomial</br>
     * @param unIndividuo Devuelve los hijos generados por la cruza.
     * @return
     */
    public ArrayList<Individuo> cruza(int typoCruza, Individuo unIndividuo) {
        ArrayList<Individuo> hijos = new ArrayList<>();
        switch (typoCruza) {
            case CRUZA_SIMPLE:
                hijos = this.cruzaSimple(unIndividuo, (byte) suerte.nextInt(4));
                break;
            case CRUZA_MULTIPUNTO:
                hijos = this.cruzaMultiPunto(unIndividuo, suerte.nextInt(14) + 1);
                break;
            case CRUZA_BINOMIAL:
                hijos = this.cruzaBinomial(unIndividuo, 0.6f);
                break;
        }
        return hijos;
    }

    /**
     * Realiza la mutación del individuo. Método automático para la realización
     * de la mutación. Puede mutar varios productos del individuo.
     */
    public void mutar() {
        int aleatorio = suerte.nextInt(15) + 1;
        for (int i = 0; i < 4; i++) {
            int posicion = aleatorio & (int) Math.pow(2, i);
            if (posicion != 0) {
                int nuevoValor = suerte.nextInt((int) (Generaciones.gokuFase4.getProducto(3 - i) * 1.2 + 1));//el mas 1 es por el tema de su el valor es 0 y 1
                this.setProducto(3 - i, nuevoValor);
            }
        }
    }

    /**
     * Determina la eficiencia del individuo con los recursos ingresados. Es
     * utilizado para determinar la aptitud del individuo, tomando como dato la
     * cantidad de materiales.
     *
     * @param matIngs Cantidad de materiales ingresados por el usuario al inicio
     * de la ejecución del programa.
     * @return Un booleano indicando si es eficiente o no.
     */
    public boolean eficienteConRecursos(int[] matIngs) {
        /*
         * Devuelve verdadero si (diferencia - rango*cantDeProductos)<=0 Si es
         * es menor pone 0 en la diferencia. Los valores de diferencia que
         * queden positivos, seran el remanente del material.
         */
        int[] diferencia = calcDiferencia(matIngs);
        boolean valor = true;
        int[] rangos = calcularMaterialesRango();

        for (int i = 0; i < rangos.length; i++) {
            diferencia[i] -= rangos[i];
            if ((diferencia[i]) > 0) {
                valor = false;
            }
        }
        return valor;
    }

    /**
     * Determina la cantidad mínima de materiales necesarios para el individuo.
     * Calcula la cantidad de materiales mínimos según la combinación de
     * productos del individuo.
     *
     * @return Array indicando la cantidad de materiales m1..m8.
     */
    public int[] calcularMaterialesMinimos() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * MMinimos[0][i] + p2 * MMinimos[1][i]
                    + p3 * MMinimos[2][i] + p4 * MMinimos[3][i];
        }
        return materiales;
    }

    /**
     * Determina el rango de materiales para el individuo, sobre el mínimo, para
     * llegar al valor máximo. Calcula la cantidad de materiales para llegar al
     * valor máximo de la "receta".
     *
     * @return Array indicando el rango por encima del mínimo de los materiales
     * m1..m8.
     */
    public int[] calcularMaterialesRango() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * Rangos[0][i] + p2 * Rangos[1][i]
                    + p3 * Rangos[2][i] + p4 * Rangos[3][i];
        }
        return materiales;
    }

    /**
     * Determina si la producción de la combinación de productos es factible.
     * Permite determinar si es factible producir la combinación según la
     * cantidad de materiales m1..m8 ingresados por el usuario al inicio de la
     * ejecución del programa.
     *
     * @param matIngs Cantidad de materiales m1..m8 ingresados por el usuario.
     * @return Booleano determinando si es (o no) factible la producción de
     * dicha combinación.
     */
    public boolean factibilidad(int[] matIngs) {

        int[] diferencia = calcDiferencia(matIngs);
        boolean valor = true;
        for (int i = 0; i < diferencia.length; i++) {
            if (diferencia[i] < 0) {
                valor = false;
            }
        }
        return valor;
    }

    /**
     * Determina la diferencia materiales con respecto al mínimo. Compara la
     * cantidad mínima necesaria para realizar la combinación de productos con
     * respecto a la cantidad ingresada por el usuario.
     *
     * @param matIngs Cantidad de materiales m1..m8 ingresados por el usuario.
     * @return Array de enteros con las diferencias entre ingresado y mínimo.
     */
    private int[] calcDiferencia(int[] matIngs) {
        int[] matMinimos = calcularMaterialesMinimos();
        int[] diferencia = new int[8];
        for (int i = 0; i < diferencia.length; i++) {
            diferencia[i] = matIngs[i] - matMinimos[i];
        }
        return diferencia;
    }

    /**
     * Devuelve String para mostrar en consola la cantidad de productos del
     * individuo. Concatena de manera agradable a la vista la cantidad de
     * p1..p4.
     *
     * @return String listo para ser mostrado en consola.
     */
    public String mostrarProductos() {

        return "(" + this.getP1() + "," + this.getP2() + ","
                + this.getP3() + "," + this.getP4() + ")";

    }

    /**
     * Devuelve la utilidad del individuo. Utilizado para obtener la ganancia,
     * útil para calcular la aptitud.
     *
     * @return Entero indicando la utilidad del individuo.
     */
    public int getUtilidad() {

        int utilidad = this.getP1() * Utilidad[0] + this.getP2() * Utilidad[1]
                + this.getP3() * Utilidad[2] + this.getP4() * Utilidad[3];
        return utilidad;
    }

    /**
     * Devuelve la aptitud del individuo. Solo devuelve el atributo aptitud, no
     * lo calcula.
     *
     * @return Float positivo indicando la aptitud del individuo.
     */
    public float getAptitud() {
        return aptitud;
    }

    /**
     * Devuelve el valor del gen (producto) deseado. Para obtener la cantidad de
     * p1, elegir parámetro 0; para p2, 1; para p3, 2 y para p4 poner cualquier
     * otra cosa (preferiblemente 3).
     *
     * @param nroProducto Entero para determinar el producto deseado a obtener.
     * @return La cantidad del producto que se desea obtener.
     */
    public int getProducto(int nroProducto) {
        int var;
        switch (nroProducto) {
            case 0:
                var = getP1();
                break;
            case 1:
                var = getP2();
                break;
            case 2:
                var = getP3();
                break;
            default:
                var = getP4();
                break;

        }
        return var;
    }

    /**
     * El metodo setProducto recibe el nro del prducto al que se le va a asignar
     * el valor. Los valores de nro de prducto son: 0 para P1, 1 para P2, 2 para
     * P3 y 3 para P4. En el caso de que se agregue cualquier otro nro el valor
     * no sera seteado.
     *
     * @param nroProducto Especifica el gen que se va a setear.
     * @param valor El nuevo valor que se va a setear.
     */
    public void setProducto(int nroProducto, int valor) {
        int var;
        switch (nroProducto) {
            case 0:
                setP1(valor);
                break;
            case 1:
                setP2(valor);
                break;
            case 2:
                setP3(valor);
                break;
            case 3:
                setP4(valor);
                break;
        }
    }

    /**
     * Devuelve la cantidad de p1 del individuo. (Simple getter)
     *
     * @return Entero con la cantidad de p1.
     */
    public int getP1() {
        return p1;
    }

    /**
     * Devuelve la cantidad de p2 del individuo. (Simple getter)
     *
     * @return Entero con la cantidad de p2.
     */
    public int getP2() {
        return p2;
    }

    /**
     * Devuelve la cantidad de p3 del individuo. (Simple getter)
     *
     * @return Entero con la cantidad de p3.
     */
    public int getP3() {
        return p3;
    }

    /**
     * Devuelve la cantidad de p4 del individuo. (Simple getter)
     *
     * @return Entero con la cantidad de p4.
     */
    public int getP4() {
        return p4;
    }

    /**
     * Asigna el valor de p1 con lo deseado. (Simple setter)
     *
     * @param p1 Entero deseado a ingresar para p1.
     */
    public void setP1(int p1) {
        this.p1 = p1;
    }

    /**
     * Asigna el valor de p2 con lo deseado. (Simple setter)
     *
     * @param p2 Entero deseado a ingresar para p1.
     */
    public void setP2(int p2) {
        this.p2 = p2;
    }

    /**
     * Asigna el valor de p3 con lo deseado. (Simple setter)
     *
     * @param p3 Entero deseado a ingresar para p3.
     */
    public void setP3(int p3) {
        this.p3 = p3;
    }

    /**
     * Asigna el valor de p4 con lo deseado. (Simple setter)
     *
     * @param p4 Entero deseado a ingresar para p4.
     */
    public void setP4(int p4) {
        this.p4 = p4;
    }

    /**
     * Devuelve el contador de cruzas binomiales.
     *
     * @return Entero con la cantidad de cruzas binomiales realizadas.
     */
    public static int getCont_cruzaBinomial() {
        return cont_cruzaBinomial;
    }

    /**
     * Asigna un valor al contador de cruzas binomiales.
     *
     * @param cont_cruzaBinomial Entero con la cantidad a asignar.
     */
    public static void setCont_cruzaBinomial(int cont_cruzaBinomial) {
        Individuo.cont_cruzaBinomial = cont_cruzaBinomial;
    }

    /**
     * Devuelve el contador de cruzas multipunto.
     *
     * @return Entero con la cantidad de cruzas multipunto realizadas.
     */
    public static int getCont_cruzaMultipunto() {
        return cont_cruzaMultipunto;
    }

    /**
     * Asigna un valor al contador de cruzas multipunto.
     *
     * @param cont_cruzaMultipunto Entero con la cantidad a asignar.
     */
    public static void setCont_cruzaMultipunto(int cont_cruzaMultipunto) {
        Individuo.cont_cruzaMultipunto = cont_cruzaMultipunto;
    }

    /**
     * Devuelve el contador de cruzas simple.
     *
     * @return Entero con la cantidad de cruzas simple realizadas.
     */
    public static int getCont_cruzaSimple() {
        return cont_cruzaSimple;
    }

    /**
     * Asigna un valor al contador de cruzas simple.
     *
     * @param cont_cruzaSimple Entero con la cantidad a asignar.
     */
    public static void setCont_cruzaSimple(int cont_cruzaSimple) {
        Individuo.cont_cruzaSimple = cont_cruzaSimple;
    }

    public static void reiniciarContadoresCruza() {
        cont_cruzaBinomial = 0;
        cont_cruzaMultipunto = 0;
        cont_cruzaSimple = 0;

    }

    /**
     * Calula la receta a un indiviuo. Busca minimizar el desperdicio asignando
     * eficientemente los recursos existentes.
     *
     * @param mIngs Materiales ingresados por el usuario al inicio de la
     * ejecución del algoritmo genético.
     * @return Matriz de 4x8 indicando la receta para cada uno de los productos.
     */
    public int[][] calcularReceta(int[] mIngs) {
        int[][] receta = new int[4][8];
        int[] totalconsumido = new int[8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                receta[i][j] = MMaximos[i][j];// + rangos[i][j];
                totalconsumido[j] = totalconsumido[j] + (getProducto(i) * receta[i][j]);
            }
        }
        for (int i = 0; i < 8; i++) {
            totalconsumido[i] = mIngs[i] - totalconsumido[i];
        }
        int aux;
        int salto;

        for (int i = 0; i < 8; i++) {

            while (totalconsumido[i] < 0) {
                //buscar el menor material que no este en el minimo...
                //en verdad hay que preguntar.. ¿¿Que salto se aproxima mas a cero??
                aux = 0;
                salto = Integer.MAX_VALUE;
                for (int j = 0; j < 4; j++) {
                    if ((salto >= Math.abs(totalconsumido[i] + getProducto(j))) && (receta[j][i] > MMinimos[j][i])) {
                        aux = j;
                        salto = Math.abs(totalconsumido[i] + getProducto(j));
                    }
                }
                if (receta[aux][i] > MMinimos[aux][i]) {
                    receta[aux][i] = receta[aux][i] - 1;
                    totalconsumido[i] = totalconsumido[i] + getProducto(aux);
                }
            }
        }

        return receta;
    }

    /**
     * Determina la cantidad de materiales que se desperdiciarán.
     *
     * @param receta La receta calculada que utiliza eficientemente los
     * recursos.
     * @param mIng Materiales ingresados por el usuario al inicio de la
     * ejecución del algoritmo genético.
     * @return Cantidad de cada material que será despericiado.
     */
    public int[] calcularDesperdicios(int[][] receta, int[] mIng) {
        int[] uso = calcularUso(receta);
        int[] desperdicios = new int[8];

        for (int i = 0; i < 8; i++) {
            desperdicios[i] = mIng[i] - uso[i];
        }
        return desperdicios;
    }

    /**
     * Calcula la cantidad de cada material que será utilizado por el individuo.
     *
     * @param receta La receta calculada que utiliza eficientemente los
     * recursos.
     * @return Cantidad de materiales utilizados de cada tipo.
     */
    public int[] calcularUso(int[][] receta) {
        int[] uso = new int[8];
        for (int i = 0; i < 8; i++) {
            uso[i] = 0;
            for (int j = 0; j < 4; j++) {
                uso[i] = uso[i] + receta[j][i] * getProducto(j);
            }
        }
        return uso;
    }

    /**
     * Calcula la base para el cálculo de la aptitud. Se basa en el individuo
     * óptimo inalcanzable para determinar la base de la aptitud. Modifica la
     * variable "BASE".
     *
     * @return El valor de la base.
     */
    public int calcularBase() {
        int base = 0;
        int minimos[] = Generaciones.gokuFase4.calcularMaterialesMinimos();
        int rangos[] = Generaciones.gokuFase4.calcularMaterialesRango();
        for (int i = 0; i < minimos.length; i++) {
            base += minimos[i] + rangos[i];
        }
        BASE = base;
        return base;
    }

    /**
     * Permite comparar con otros individuos en un array con respecto a la
     * aptitud. Es un metodo sobrescrito necesario por la interfaz Comparable.
     *
     * @param otroIndividuo El otro individuo con el que se quiere comparar.
     * @return La comparación de los individuos.
     */
    @Override
    public int compareTo(Individuo otroIndividuo) {
        return Float.compare(otroIndividuo.getAptitud(), this.aptitud);
    }

    /**
     * Obtiene la relación con respecto al máximo teórico. Este método permite
     * obtener la relacion entre la aptitud del individuo y la aptitud del
     * máximo teórico.
     *
     * @return La relación entre las dos aptitudes (apt/aptMax).
     */
    public float getPorcEficiencia() {
        float porcentaje = 0f;
        porcentaje = aptitud / Generaciones.gokuFase4.getAptitud();
        return porcentaje;
    }
}
