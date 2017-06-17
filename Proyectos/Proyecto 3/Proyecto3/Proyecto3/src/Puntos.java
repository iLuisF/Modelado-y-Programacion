
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Representación de una lista de puntos (X, Y).
 *
 * @author Flores González Luis Brandon.
 * @version 1.0 - Noviembre/2015
 */
public class Puntos {

    //Solo valores de X, de las parejas ordenadas (X, P(X))
    private BigInteger[] x;
    //Solo valores de Y, de las parejas ordenadas (X, P(X))
    private BigInteger[] y;
    //Número de puntos (X, P(X)) de la lista.
    private Integer numPuntos;

    /**
     * Construye un listado de puntos (X, Y)
     *
     * @param x Arreglo BigInteger[] de puntos x.
     * @param y Arreglo BigInteger[] de puntos y.
     */
    public Puntos(BigInteger[] x, BigInteger[] y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor por omisión.
     */
    public Puntos() {
    }

    /**
     * Muestra una lista de puntos de la forma (x, y).
     *
     * @return Cadena de una lista de puntos (X, Y) de la forma (X, Y)...(X, Y)
     */
    public String toSting() {
        String puntos = "";
        for (int i = 0; i < getPuntosX().length; i++) {
            puntos = "(" + getPuntosX()[i] + ", " + getPuntosY()[i]
                    + ")\n" + puntos;
        }
        return puntos;
    }

    /**
     * Convierte una cadena a una lista de puntos. La cadena debe ser de la
     * forma (x, y).
     *
     * @param cadena Cadena con puntos de la forma (x, y).
     * @return Regresa una lista de Puntos.
     */
    public Puntos convertirAPunto(String cadena) {
        StringTokenizer st = new StringTokenizer(cadena, "(),\t\n\r\f");
        BigInteger[] puntosX = new BigInteger[st.countTokens() / 2];
        BigInteger[] puntosY = new BigInteger[st.countTokens() / 2];
        setNumPuntos(st.countTokens() / 2);
        int i = 0;
        while (st.hasMoreTokens()) {
            String valorX = st.nextToken();
            String valorY = st.nextToken();
            puntosX[i] = new BigInteger(valorX.trim());
            puntosY[i] = new BigInteger(valorY.trim());
            i++;
        }
        setPuntosX(puntosX);
        setPuntosY(puntosY);
        return new Puntos(puntosX, puntosY);
    }

    /**
     * Asigna el numero de puntos que hay en un listado de puntos.
     *
     * @param numPuntos El numero de puntos que hay.
     */
    public void setNumPuntos(Integer numPuntos) {
        this.numPuntos = numPuntos;
    }

    /**
     * Regresa el numero de puntos que hay en un listado de puntos.
     *
     * @return El numero de puntos.
     */
    public Integer getNumPuntos() {
        return this.numPuntos;
    }

    /**
     * Asigna los puntos x.
     *
     * @param x Arreglo BigInteger[] con puntos x.
     */
    public void setPuntosX(BigInteger[] x) {
        this.x = x;
    }

    /**
     * Asigna los puntos y.
     *
     * @param y Arreglo BigInteger[] con puntos y.
     */
    public void setPuntosY(BigInteger[] y) {
        this.y = y;
    }

    /**
     * Regresa los puntos en x.
     *
     * @return Arreglo BigInteger[] de puntos en x.
     */
    public BigInteger[] getPuntosX() {
        return this.x;
    }

    /**
     * Regresa los puntos en y.
     *
     * @return Arreglo BigInteger[] de puntos en y.
     */
    public BigInteger[] getPuntosY() {
        return this.y;
    }
}
