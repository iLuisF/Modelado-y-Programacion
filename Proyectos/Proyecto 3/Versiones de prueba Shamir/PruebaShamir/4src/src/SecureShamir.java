//Borrar dominio dividido

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

public class SecureShamir {

    //Tamaño del byte del numero primo generado que servira como un campo.
    private final static int TAMANIO_BIT = 128;
    //Los valores secretos que serán compartidos, un secreto por cada celda.
    private final BigInteger[] valor;
    //Numero minimo de personas para reconstruir secreto.
    private final int k;
    //Numero de puntos para repartir el mensaje.
    private final int n;    
    //Shares(¿coeficientes?) que serán distribuidos entre los servidores
    private final ArrayList<BigInteger[]> shares;
    //Cocientes de la función de k-1 grado generada
    private BigInteger[] cocientes;
    //Parametro de seguridad de un primo grande.
    private final BigInteger primoGrande;
    //Subconjuntos del conjunto [0,.., primoGrande] que sera asociado a cada valor.
    private final Hashtable<BigInteger, BigInteger[]> dominio;

    /**
     * Construye un secreto.
     *
     * @param valor Secreto que se quiere encriptar, por cada celda habra un
     * secreto.
     * @param k Número minimo de personas para reconstruir el secreto. De estas
     * k personas se generaran k-1 coeficientes.
     * @param n Número de personas(puntos) para repartir el mensaje. De estos n
     * puntos solo se necesitaran k de cualquier punto para reconstruir el
     * secreto.
     */
    public SecureShamir(BigInteger[] valor, int k, int n) {
        Arrays.sort(valor);
        this.valor = valor;
        this.k = k;
        this.n = n;
        //Cambiar primo por el que nos dio el profesor.
        this.primoGrande = new BigInteger("331908329300706409175972204745165044917");        
        this.shares = new ArrayList<>(valor.length);
        this.dominio = new Hashtable<>(valor.length);
        System.out.println("Campo: " + this.primoGrande);        
    }
    

    public ArrayList<BigInteger[]> buildShares() {
        this.asignarDominio();
        this.shares.add(construirSecreto(this.valor[0]));
        return shares;
    }

    /**
     * Construye n puntos que seran distribuidos en n personas, de los cuales
     * solo se necesitaran k<n de esas personas para reconstruir el mensaje.
     *
     * @param valorSecreto Valor secreto que se desea guardar.
     * @return Los n puntos que se distribuiran en n personas.
     */
    private BigInteger[] construirSecreto(BigInteger valorSecreto) {
        BigInteger[] secreto = new BigInteger[n];
        this.cocientes = new BigInteger[this.k - 1];
        BigInteger[] valoresCompartidos = new BigInteger[n];
        System.out.println("Generando valores entre " + this.dominio.get(valorSecreto)[0] + " y " + this.dominio.get(valorSecreto)[1]);
        //Genera el polinomio de coeficientes.
        for (int i = 0; i < this.k - 1; i++) {
            cocientes[i] = this.generarElementoCampo(this.dominio.get(valorSecreto)[0],
                    this.dominio.get(valorSecreto)[1]);
            System.out.println("Coeficientes: " + cocientes[i]);
        }
        //Genera los valores compartidos a n personas.           
        for (int i = 0; i < this.n; i++) {
            secreto[i] = this.generarElementoCampo(this.dominio.get(valorSecreto)[0],
                    this.dominio.get(valorSecreto)[1]);
            valoresCompartidos[i] = valorSecreto.add(this.valorEnX(cocientes, secreto[i]));
        }
        System.out.print("Valores en X: ");
        System.out.println(Arrays.toString(secreto));                
        return valoresCompartidos;
    }

     /**
     * Método para obtener el valor secreto de k puntos usando interpolación
     * Lagrange.
     *
     * @param secreto1 Arreglo(secreto) que se genera en construirSecreto y se agrega a ArrayList x. ///Corregir comentario
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
        BigInteger[] limites = new BigInteger[2];
        limites[0] = BigInteger.ZERO; //Limite actual
        limites[1] = this.primoGrande;
        this.dominio.put(this.valor[0], limites); //Hashtable                        
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
     * Genera un número aleatorio de un campo en especifico.
     *     
     * @return Número aleatorio.
     */
    private BigInteger generarElementoCampo(BigInteger limiteInferior, BigInteger limiteSuperior) {
        BigInteger numero;
        do {
            numero = new BigInteger(SecureShamir.TAMANIO_BIT, new Random());
        } while (numero.compareTo(limiteInferior) < 0 || numero.compareTo(limiteSuperior) >= 0);
        return numero;
    }
}
