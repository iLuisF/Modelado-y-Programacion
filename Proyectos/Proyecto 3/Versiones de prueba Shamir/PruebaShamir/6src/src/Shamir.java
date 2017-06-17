
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Shamir {

    //Tamaño del byte del numero generado que servira como elemento del campo.
    private final static int TAMANIO_BIT = 256;    
    //Numero minimo de personas para reconstruir el secreto.
    private final int k;
    //Numero de puntos para repartir el mensaje.
    private final int n;
    //Cocientes de la función de k-1 grado generada.
    private BigInteger[] cocientes;
    //Parametro de seguridad de un primo grande.
    private final BigInteger primoGrande;
    //Limites del campo, es decir, [0,..., primoGrande]
    private final BigInteger[] limite;

    /**
     * Construye un secreto apartir de un valor.
     *     
     * @param k Número minimo de personas para reconstruir el secreto. De estas
     * k personas se generaran k-1 coeficientes.
     * @param n Número de personas(puntos) para repartir el mensaje. De estos n
     * puntos solo se necesitaran k de cualquier punto para reconstruir el
     * secreto.
     */
    public Shamir(int k, int n) {        
        this.k = k;
        this.n = n;
        this.primoGrande = new BigInteger("20835161731609124123432674631212444"
                + "8251235562226470491514186331217050270460481");
        this.limite = new BigInteger[2];
        System.out.println("Campo: " + this.primoGrande);
    }

    /**
     * Construye n puntos que seran distribuidos en n personas, de los cuales
     * solo se necesitaran k<n de esas personas para reconstruir el mensaje.
     *
     * @param valorSecreto Valor secreto que se desea guardar.
     * @return Los n puntos que se distribuiran en n personas.
     */
    public BigInteger[] construirSecreto(BigInteger valorSecreto) {
        this.asignarDominio();
        BigInteger[] secreto = new BigInteger[n];
        this.cocientes = new BigInteger[this.k - 1];
        BigInteger[] valoresCompartidos = new BigInteger[n];
        System.out.println("Generando valores entre " + this.limite[0] + " y "
                + this.limite[1]);
        //Genera el polinomio de coeficientes.
        for (int i = 0; i < this.k - 1; i++) {
            cocientes[i] = this.generarElementoCampo(this.limite[0], this.limite[1]);
            System.out.println("Coeficientes: " + this.cocientes[i]);
        }
        //Genera los valores compartidos a n personas.           
        for (int i = 0; i < this.n; i++) {
            secreto[i] = this.generarElementoCampo(this.limite[0], this.limite[1]);
            valoresCompartidos[i] = valorSecreto.add(this.valorEnX(cocientes, secreto[i]));
        }
        System.out.print("Valores en X: ");
        System.out.println(Arrays.toString(secreto));
        return valoresCompartidos;
    }

    /**
     * Método para obtener el valor secreto usando k puntos. Esto se hace con la
     * interpolación Lagrange.
     *
     * @param secreto1 Arreglo(secreto) que se genera en construirSecreto.
     * @param valoresCompartidos Puntos necesarios para aplicar la
     * interpolación.
     * @param k Numero minimo de personas para reconstruir el mensaje.
     * @return El secreto que se obtendra por medio de la interpolacion
     * Lagrange.
     */
    public BigInteger reconstruirSecreto(BigInteger[] secreto1, BigInteger[] valoresCompartidos, int k) {
        BigInteger secreto2 = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger prod = BigInteger.ONE;
            for (int j = 0; j < k; j++) {
                if (j != i) {
                    prod = prod.multiply(secreto1[j].multiply((secreto1[j].subtract(secreto1[i])).modInverse(this.primoGrande)));
                }
            }
            secreto2 = secreto2.add(valoresCompartidos[i].multiply(prod));
        }
        return secreto2.mod(this.primoGrande);
    }

    /**
     * Asigna un dominio apartir de un numero primo, es decir, un campo.
     */
    public void asignarDominio() {
        this.limite[0] = BigInteger.ZERO;
        this.limite[1] = this.primoGrande;
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
                                    this.primoGrande)));
        }
        return resultado;
    }

    /*
     * Genera un número aleatorio del campo especificado.
     *     
     * @return Número aleatorio.
     */
    private BigInteger generarElementoCampo(BigInteger limiteInferior, BigInteger limiteSuperior) {
        BigInteger numero;
        do {
            numero = new BigInteger(Shamir.TAMANIO_BIT, new Random());
        } while (numero.compareTo(limiteInferior) < 0 || numero.compareTo(limiteSuperior) >= 0);
        return numero;
    }
}
