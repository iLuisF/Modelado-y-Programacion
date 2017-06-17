//Borrar dominio dividido
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

public class SecureShamir {

    /**
     * Byte size of the generated prime number that will serve as a field
     */
    private final static int TAMANIO_BIT = 128;

    /**
     * The secret values that will be shared
     */
    private final BigInteger[] valores;

    /**
     * Minimum number of agents able to reconstruct the secret
     */
    private final int k;//Numero minimo de personas para reconstruir secreto.

    /**
     * Number of servers
     */
    private final int n; //Numero de puntos para repartir el mensaje.

    /**
     * Secret x values
     */
    private final ArrayList<BigInteger[]> x;

    /**
     * Shares that will be distributed among the servers
     */
    private final ArrayList<BigInteger[]> shares;

    /**
     * Quotients of the k-1 degree generated function
     */
    private BigInteger[] cocientes;

    /**
     * Large prime security parameter
     */
    private final BigInteger primoGrande;

    /**
     * Subsets of the [0...largePrime] set that will be associated to each value
     */
    private final Hashtable<BigInteger, BigInteger[]> dominios;

    /**
     *
     * @param valores
     * @param k
     * @param n
     */
    public SecureShamir(BigInteger[] valores, int k, int n) {
        Arrays.sort(valores);
        this.valores = valores;
        this.k = k;
        this.n = n;
        this.primoGrande = this.generarPrimo(SecureShamir.TAMANIO_BIT);
        this.x = new ArrayList<>(valores.length);
        this.shares = new ArrayList<>(valores.length);
        this.dominios = new Hashtable<>(valores.length);
        System.out.println("Campo: " + this.primoGrande);
        System.out.println("");
    }

    public ArrayList<BigInteger[]> buildShares() {
        this.splitDomain();
        for (int i = 0; i < valores.length; i++) {
            this.shares.add(construirSecreto(this.valores[i]));
        }
        return shares;
    }

    /**     
     * Método para obtner el valor secreto de k puntos usando interpolación 
     * Lagrange.
     *
     * @param shareValues
     * @return The secret that will be obtained by interpolating the values from
     * each server
     */
    public BigInteger reconstruirSecreto(BigInteger[] shareValues) {
        BigInteger[] x = this.x.get(this.shares.indexOf(shareValues));
        BigInteger secret = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger prod = BigInteger.ONE;
            for (int j = 0; j < k; j++) {
                if (j != i) {
                    prod = prod.multiply(x[j].multiply((x[j].subtract(x[i])).modInverse(this.primoGrande)));
                }
            }
            secret = secret.add(shareValues[i].multiply(prod));
        }
        return secret.mod(this.primoGrande);
    }

    /**     
     * Construye n puntos que seran distribuidos en n personas, de los cuales
     * solo se necesitaran k<n de esas personas para reconstruir el mensaje.
     *
     * @return Los n puntos que se distribuiran en n personas.
     */
    private BigInteger[] construirSecreto(BigInteger value) {
        BigInteger[] secret = new BigInteger[n];
        this.cocientes = new BigInteger[this.k - 1];
        BigInteger[] shareValues = new BigInteger[n];
        System.out.println("");
        System.out.println("Generando valores entre " + this.dominios.get(value)[0] + " y " + this.dominios.get(value)[1]);
        System.out.println("");
        //Genera el polinomio de coeficientes.
        for (int i = 0; i < this.k - 1; i++) {
            cocientes[i] = this.generarElementoCampo(this.dominios.get(value)[0], this.dominios.get(value)[1]);
            System.out.println("");
            System.out.println("Coeficientes: " + cocientes[i]);
            System.out.println("");
        }
        /*
         * Generate n secret values x and
         * calculate each value of generated polynom in x
         */
        for (int i = 0; i < this.n; i++) {
            secret[i] = this.generarElementoCampo(this.dominios.get(value)[0], this.dominios.get(value)[1]);
            shareValues[i] = value.add(this.valorEnX(cocientes, secret[i]));
        }

        this.x.add(secret);
        return shareValues;
    }

    /**
     * Split [0...largePrime] into the same amount as the number of values
     */
    public void splitDomain() {
        BigInteger split = this.primoGrande.divide(BigInteger.valueOf(this.valores.length));
        BigInteger currentLimit = BigInteger.ZERO;
        for (int i = 0; i < this.valores.length; i++) {
            BigInteger[] limits = new BigInteger[2];
            limits[0] = currentLimit;
            if (this.primoGrande.compareTo(currentLimit.add(split)) != -1) {
                limits[1] = currentLimit.add(split);
            } else {
                limits[1] = this.primoGrande;
            }
            this.dominios.put(this.valores[i], limits);
            //System.out.println("Limits: [" + limits[0] + ", " + limits[1] + "]");
            currentLimit = currentLimit.add(split.add(BigInteger.ONE));
            //System.out.println("Lower than large prime: " + (this.largePrime.compareTo(limits[1]) != -1));
        }
    }

    /**
     * Valor encontrado de una función especifica en un punto x.
     *
     * @param cocientes
     * @param x
     * @return Valor encontrado en un punto x de una fución.
     */
    private BigInteger valorEnX(BigInteger[] cocientes, BigInteger x) {
        BigInteger resultado = BigInteger.ZERO;
        for (int j = 0; j < cocientes.length; j++){
            resultado = resultado.add(
                    cocientes[j].multiply(x.modPow(BigInteger.valueOf(j + 1),
                                    this.primoGrande)));
        }
        return resultado;
    }

    /**
     * Generate prime number of specific byte size
     *
     * @param length
     * @return
     */
    private BigInteger generarPrimo(int length) {
        BigInteger prime = BigInteger.ZERO;

        do {
            prime = BigInteger.probablePrime(length, new Random());
        } while (!prime.isProbablePrime(1));

        return prime;
    }

    /*
     * Genera un número aleatorio de un campo en especifico.
     *     
     * @return Número aleatorio.
     */
    private BigInteger generarElementoCampo(BigInteger lowerLimit, BigInteger higherLimit) {
        BigInteger numero;
        do {
            numero = new BigInteger(SecureShamir.TAMANIO_BIT, new Random());
        } while (numero.compareTo(lowerLimit) < 0 || numero.compareTo(higherLimit) >= 0);
        return numero;
    }
}
