
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Representación del esquema de Shamir para encriptar o desencriptar un valor.
 *
 * @author Flores Gonzalez Luis Brandon.
 */
public class Shamir {

    private final Campo campo;

    /**
     * Construye un esquema de Shamir para poder cifrar o decifrar un mensaje.
     */
    public Shamir() {
        this.campo = new Campo();
    }

    /**
     * Construye n puntos que seran distribuidos en n personas, de los cuales
     * solo se necesitaran k<n de esas personas para reconstruir el mensaje.
     *
     * @param valorSecreto Valor secreto que se desea guardar.
     * @param k Número minimo de personas para reconstruir el secreto. De estas
     * k personas se generaran k-1 coeficientes.
     * @param n Número de personas(puntos) para repartir el mensaje. De estos n
     * puntos solo se necesitaran k de cualquier punto para reconstruir el
     * secreto.
     * @return Los n puntos que se distribuiran en n personas.
     */
    public BigInteger[] construirSecreto(BigInteger valorSecreto, int k, int n) {
        BigInteger[] secreto = new BigInteger[n];
        //Cocientes de la función de k-1 grado generada.
        BigInteger[] cocientes = new BigInteger[k - 1];
        BigInteger[] valoresCompartidos = new BigInteger[n];
        System.out.println("Generando valores de " + this.campo.getLimite()[0]
                + " a " + this.campo.getLimite()[1]);
        //Genera el polinomio de coeficientes.
        for (int i = 0; i < k - 1; i++) {
            cocientes[i] = this.campo.getNumero(this.campo.getLimite()[0],
                    this.campo.getLimite()[1]);
            System.out.println("Coeficientes: " + cocientes[i]);
        }
        //Genera los valores compartidos a n personas.           
        for (int i = 0; i < n; i++) {
            secreto[i] = this.campo.getNumero(this.campo.getLimite()[0],
                    this.campo.getLimite()[1]);
            valoresCompartidos[i] = valorSecreto.add(this.valorEnX(cocientes,
                    secreto[i]));
        }
        System.out.print("Valores en X: ");
        System.out.println(Arrays.toString(secreto));
        return valoresCompartidos;
    }

    /**
     * Método para obtener el valor secreto usando k puntos. Esto se hace con la
     * interpolación Lagrange.
     *
     * @param valoresX Arreglo(secreto) que se genera en construirSecreto.
     * @param valoresY Puntos necesarios para aplicar la interpolación.
     * @param k Numero minimo de personas para reconstruir el mensaje.
     * @return El secreto que se obtendra por medio de la interpolacion
     * Lagrange.
     */
    public BigInteger reconstruirSecreto(BigInteger[] valoresX, BigInteger[] valoresY, int k) {
        BigInteger secreto = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger prod = BigInteger.ONE;
            for (int j = 0; j < k; j++) {
                if (j != i) {
                    prod = prod.multiply(valoresX[j].multiply((valoresX[j].
                            subtract(valoresX[i])).modInverse(this.campo.getNumPrimo())));
                }
            }
            secreto = secreto.add(valoresY[i].multiply(prod));
        }
        return secreto.mod(this.campo.getNumPrimo());
    }

    /**
     * Valor encontrado de una función especifica en un punto x.
     *
     * @param cocientes Arreglo con los cocientes de un polinomio.
     * @param x Punto x a evaluar.
     * @return Valor encontrado en un punto x de una fución.
     */
    private BigInteger valorEnX(BigInteger[] cocientes, BigInteger x) {
        BigInteger resultado = BigInteger.ZERO;
        for (int j = 0; j < cocientes.length; j++) {
            resultado = resultado.add(
                    cocientes[j].multiply(x.modPow(BigInteger.valueOf(j + 1),
                                    this.campo.getNumPrimo())));
        }
        return resultado;
    }
}
