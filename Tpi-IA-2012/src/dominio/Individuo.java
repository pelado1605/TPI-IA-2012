/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Random;

/**
 *
 * @author Ruben
 */
public class Individuo implements Comparable<Individuo> {

    private static final byte[][] MMinimos = {
        {80, 0, 44, 72, 0, 55, 22, 0},
        {15, 49, 12, 0, 50, 21, 0, 70},
        {0, 84, 0, 34, 62, 62, 43, 0},
        {41, 0, 0, 74, 24, 82, 55, 52}
    };
    private static final byte[][] MMaximos = {
        {95, 0, 65, 88, 0, 77, 50, 0},
        {35, 55, 28, 0, 60, 44, 0, 91},
        {0, 99, 0, 55, 78, 70, 65, 0},
        {55, 0, 0, 95, 35, 88, 74, 78}
    };
    private static final byte[][] Rangos = {
        {15, 0, 21, 16, 0, 22, 28, 0},
        {20, 6, 16, 0, 10, 23, 0, 21},
        {0, 15, 0, 21, 16, 8, 22, 0},
        {14, 0, 0, 21, 11, 6, 19, 26}
    };
    private static final byte[] Utilidad = {90, 115, 120, 100};
    private float aptitud;
    private int p1;
    private int p2;
    private int p3;
    private int p4;
    private Random suerte = new Random();

    public Individuo() {
        this.aptitud = 0f;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.p4 = 0;
    }

    public Individuo(int p1, int p2, int p3, int p4) {
        this.aptitud = 0f;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

//    ESTE ESTA POR PRUEBA NOMAS
    public Individuo(float aptitud) {
        this.aptitud = aptitud;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.p4 = 0;
    }

    public float evaluarAptitud(int[] matIngs) {

        float nuevaAptitud = 0;
        int[] diferencia = calcDiferencia(matIngs);
        /*
         * Aca se va a preguntar por la factibilidad del individuo, es decir, si
         * los materiales que solicita el individuo estan dentro de los ingre-
         * -sados por el usario.
         */

        if (factibilidad(matIngs)) {
            /*
             * En es este caso el individuo es factible, hay que ver la utilidad
             * y los materiales remanentes que deja.
             */

            /*
             * Por ser factibles se le suma la utildidad. Ésta no sera lineal,
             * sino que se elevará al cubo el valor
             */

            nuevaAptitud += Math.pow(getUtilidad(), 3);

            /*
             * Aca se trata la puntuacion po utilizacion de los recursos
             */
            if (!eficienteConRecursos(matIngs)) {
                /*
                 * Si no hay remanente de materiales, se los descontara en
                 * puntos a la aptitud.
                 */
                int diferenciaTotal = 0;
                for (int i = 0; i < 4; i++) {
                    if (diferencia[i] < 0) {
                        diferenciaTotal += Math.pow(diferencia[i], 2);
                    }
                }
                nuevaAptitud -= diferenciaTotal;
            } else {
                /*
                 * El individuo que utilice los materiales de manera más
                 * eficiente (que el remanente de materiales sea 0), obtendra un
                 * premio en puntos de aptitud
                 */
                nuevaAptitud += (nuevaAptitud * 0.50);
            }

        } else {
            /*
             * Aca, se castiga severamente al individuo con una aptitud muuuuy
             * baja, debido a que no es factible porque pide mas materiales que
             * los ingresados/existentes.
             */
            int diferenciaTotal = 0;
            for (int i = 0; i < 8; i++) {
                if (diferencia[i] < 0) {
                    diferenciaTotal += Math.pow(diferencia[i], 2);
                }
            }
            nuevaAptitud = nuevaAptitud - diferenciaTotal;
        }

        this.aptitud = nuevaAptitud;
        return nuevaAptitud;
    }

    /**
     * Cruza simple entre dos individuos. La posicion de los genes empieza en el
     * 0 y puede valer hasta 3. Este metodo devuelve un arreglo con los dos
     * hijos de la cruza
     *
     * @param unIndividuo Individuo con el cual cruzarse.
     * @param posicion posicion a la cual cruzar [0..3].
     * @return un arreglo con los dos hijos de la cruza.
     */
    public Individuo[] cruzaSimple(Individuo unIndividuo, byte posicion) {
        Individuo[] hijos = new Individuo[2];

        hijos[0] = new Individuo(getP1(), getP2(), getP3(), getP4());
        hijos[0].setProducto(posicion, unIndividuo.getProducto(posicion));

        hijos[1] = new Individuo(unIndividuo.getP1(), unIndividuo.getP2(),
                unIndividuo.getP3(), unIndividuo.getP4());
        hijos[1].setProducto(posicion, getProducto(posicion));

        return hijos;
    }

    /**
     * Cruza multi punto entre dos individuos. Este metodo recibe como
     * parametro un valor entero del cual se lo analiza de manera binaria para
     * determinar las posiciones de corte para la cruza.
     * <br/>
     * <i>Ejemplo: para realizar un corte en posiciones 2 y 3, se deberá pasar 
     * el entero 6 (0110 en binario).
     * </i>
     * <br/>
     * Como se ve, los 1 determinan el punto de corte y el cero donde mantiene 
     * el valor del padre. Los valores 0 y 15 no se incluyen, ya que no 
     * producirían corte alguno, solo copia de los padres.
     * @param unIndividuo Individuo con el que se va a cruzar.
     * @param cortes entero entre 1 y 14.
     * @return un arreglo con los dos hijos de la cruza.
     */
    public Individuo[] cruzaMultiPunto(Individuo unIndividuo, int cortes) {
        Individuo[] hijos = {new Individuo(), new Individuo()};
      
        for (byte i = 0; i<4; i++) {
            int posicion = (int) Math.pow(2, i);
            int valor = (int) (cortes & posicion);
            if ((valor ^ posicion) == 0) {
                hijos[0].setProducto(3-i, this.getProducto(3-i));
                hijos[1].setProducto(3-i, unIndividuo.getProducto(3-i));
            } else {
                hijos[1].setProducto(3-i, this.getProducto(3-i));
                hijos[0].setProducto(3-i, unIndividuo.getProducto(3-i));
            }
        }
        return hijos;
        /*
        Collections.sort(cortesList);
        boolean bandera = true;
        for (int i = 0; i < cortesList.size(); i++) {
            
            for (int j = cortesList.get(i); j < 4; j++) {
                if (bandera) {
                    hijos[0].setProducto(j, getProducto(j));
                    hijos[1].setProducto(j, unIndividuo.getProducto(j));
                } else {
                    hijos[1].setProducto(j, getProducto(j));
                    hijos[0].setProducto(j, unIndividuo.getProducto(j));
                }
            }
            bandera = bandera ? false : true;
        }

        Individuo mascara = new Individuo(1, 1, 1, 1);
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            boolean flag = false;
            for (Integer valor : cortesList) {
                if (valor == i) {
                    flag = true;
                }
            }
            if (flag) {
                cont++;
            }
            if (cont % 2 == 0) {
                mascara.setProducto(i, 0);
            }
        }
        Individuo mascara2 = new Individuo();
        for (int i = 0; i < 4; i++) {
            mascara2.setProducto(i, mascara.getProducto(i) ^ 1);
        }
        for (int i = 0; i < 4; i++) {
            int valor = (this.getProducto(i) * mascara.getProducto(i))
                    + (unIndividuo.getProducto(i) * mascara2.getProducto(i));
            hijos[0].setProducto(i, valor);
        
        };
        for (int i = 0; i < 4; i++) {
            int valor = (this.getProducto(i) * mascara2.getProducto(i))
                    + (unIndividuo.getProducto(i) * mascara.getProducto(i));
            hijos[1].setProducto(i, valor);
        };

        for (int i = 0; i < 4; i++) {
            if (cortesList.get(i)) {
                hijos[0].setProducto(i, this.getProducto(i));
                hijos[1].setProducto(i, unIndividuo.getProducto(i));
            } else {
                hijos[1].setProducto(i, this.getProducto(i));
                hijos[0].setProducto(i, unIndividuo.getProducto(i));
            }
        }
        */

    }

    /**
     * 
     * @param unIndividuo
     * @param probUnIndividuo
     * @return 
     */
    public Individuo[] cruzaBinomial(Individuo unIndividuo, float probUnIndividuo) {
        return null;
    }

    public void mutacionXTempAsc() {
    }

    public void mutacionXTempDesc() {
    }

    /**
     *
     * @param matIngs
     * @return
     */
    public boolean eficienteConRecursos(int[] matIngs) {
        /*
         * Devuelve verdadero si (diferencia - rango*cantDeProductos)<=0 Si es
         * es menor pone 0 en la diferencia. Los valores de diferencia que
         * queden positivos, seran el remanente del material.
         */
        int[] diferencia = calcDiferencia(matIngs);
        boolean valor = false;
        int[] rangos = calcularMaterialesRango();

        for (int i = 0; i < rangos.length; i++) {
            diferencia[i] -= rangos[i];
            if ((diferencia[i]) <= 0) {
                valor = true;
                diferencia[i] = 0;
            }
        }

        return valor;
    }

    public int[] calcularMaterialesMinimos() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * MMinimos[0][i] + p2 * MMinimos[1][i]
                    + p3 * MMinimos[2][i] + p4 * MMinimos[3][i];
        }
        return materiales;
    }

    public int[] calcularMaterialesRango() {
        int[] materiales = new int[8];
        for (int i = 0; i < materiales.length; i++) {
            materiales[i] = p1 * Rangos[0][i] + p2 * Rangos[1][i]
                    + p3 * Rangos[2][i] + p4 * Rangos[3][i];
        }
        return materiales;
    }

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

    private int[] calcDiferencia(int[] matIngs) {
        int[] matMinimos = calcularMaterialesMinimos();
        int[] diferencia = new int[8];
        for (int i = 0; i < diferencia.length; i++) {
            diferencia[i] = matIngs[i] - matMinimos[i];
        }
        return diferencia;
    }

    public int getUtilidad() {

        return 0;
    }

    public float getAptitud() {
        return aptitud;
    }

    /**
     * Devuelve el valor del gen (producto) que se indica mediante el valor del
     * parametro recibido.
     *
     * @param nroProducto
     * @return Especifica el gen que se va a setear.
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

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public int getP3() {
        return p3;
    }

    public int getP4() {
        return p4;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public void setP4(int p4) {
        this.p4 = p4;
    }

    @Override
    public int compareTo(Individuo otroIndividuo) {
        return Float.compare(otroIndividuo.getAptitud(), this.aptitud);
    }
}
