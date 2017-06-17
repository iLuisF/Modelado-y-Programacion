
import java.math.BigInteger;

/**
 * Representación del esquema de Shamir para encriptar o desencriptar un valor.
 *
 * @author Flores Gonzalez Luis Brandon.
 * @version 1.0 - Noviembre/2015
 */
public class Shamir {

    private final Campo dominio;

    /**
     * Construye un esquema de Shamir para poder cifrar o decifrar un número.
     */
    public Shamir() {
        this.dominio = new Campo();
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
    public Puntos construirSecreto(BigInteger valorSecreto, int k, int n) {
        if (n <= 2) {
            throw new ShamirException("El número total de evaluaciones"
                    + "requerias debe ser mayor a 2.");
        }
        if (k <= 1 || k > n) {
            throw new ShamirException("El minimo de llaves 'k' debe de ser 2"
                    + "y el máximo de llaves debe de ser el número total 'n' de"
                    + "fragmentos repartidos. Es decir 1 < k <= n");
        }
        BigInteger[] secreto = new BigInteger[n]; //Valores en X.
        //Cocientes de la función de k-1 grado generada.
        BigInteger[] cocientes = new BigInteger[k - 1];
        BigInteger[] valoresCompartidos = new BigInteger[n];
        //Genera el polinomio de coeficientes.
        for (int i = 0; i < k - 1; i++) {
            cocientes[i] = this.dominio.getNumero();
        }
        //Genera los valores compartidos a n personas.           
        for (int i = 0; i < n; i++) {
            secreto[i] = this.dominio.getNumero();
            valoresCompartidos[i] = valorSecreto.add(this.dominio.valorEnX(
                    cocientes, secreto[i]));
        }
        return new Puntos(secreto, valoresCompartidos);
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
    public BigInteger reconstruirSecreto(BigInteger[] valoresX, 
            BigInteger[] valoresY, int k) {
        if(valoresX.length != valoresY.length){
            throw new ShamirException("Faltan valores para poder definir los"
                    + "puntos.");
        }
        if (valoresX.length <= 2) {
            throw new ShamirException("El número total de evaluaciones"
                    + "requerias debe ser mayor a 2.");
        }
        if (k <= 1 || k > valoresX.length) {
            throw new ShamirException("El minimo de llaves 'k' debe de ser 2"
                    + "y el máximo de llaves debe de ser el número total 'n' de"
                    + "fragmentos repartidos. Es decir 1 < k <= n");
        }
        BigInteger secreto = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger prod = BigInteger.ONE;
            for (int j = 0; j < k; j++) {
                if (j != i) {
                    prod = prod.multiply(valoresX[j].multiply((valoresX[j].
                            subtract(valoresX[i])).modInverse(
                                    this.dominio.getNumPrimo())));
                }
            }
            secreto = secreto.add(valoresY[i].multiply(prod));
        }
        return secreto.mod(this.dominio.getNumPrimo());
    }
}
