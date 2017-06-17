
import java.math.BigInteger;
import java.util.Random;

/**
 * Representación de una campo finito de numeros.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0 - Noviembre/2015
 */
public class Campo {

    //Tamaño del byte del numero generado que servira como elemento del campo.
    private final int TAMANIO_BIT = 256;
    //Parametro de seguridad de un primo grande.
    private final BigInteger primoGrande;
    //Limites del campo, es decir, [0,..., primoGrande]
    private final BigInteger[] limite;

    /**
     * Construye un campo apartir de un numero primo, de esta manera asigna un
     * limite al dominio.
     */
    public Campo() {
        this.primoGrande = new BigInteger("20835161731609124123432674631212444"
                + "8251235562226470491514186331217050270460481");
        this.limite = new BigInteger[2];        
        this.setDominio();
    }

    /**
     * Asigna un dominio apartir de un numero primo, es decir, un campo con sus
     * limites respectivos. Como limite inferior el 0 y como limite superior el
     * número primo asignado en el constructor.
     */
    public final void setDominio() {
        this.limite[0] = BigInteger.ZERO;
        this.limite[1] = this.primoGrande;
    }

    /**
     * Regresa los limites correspondientes al campo finito.
     *
     * @return Limites del campo.
     */
    public BigInteger[] getLimite() {
        return this.limite;
    }

    /**
     * Regresa el número primo con el que se trabaja en el campo finito.
     *
     * @return Número primo del campo finito.
     */
    public BigInteger getNumPrimo() {
        return this.primoGrande;
    }

    /*
     * Genera un número aleatorio del campo finito especificado. Como limite
     * inferior el 0 y como limite superior el numero primo asignado en el
     * constructor.
     *     
     * @return Número aleatorio dentro del campo.
     */
    public BigInteger getNumero() {
        BigInteger numero;
        BigInteger limInf = limite[0];
        BigInteger limSup = limite[1];
        do {
            numero = new BigInteger(this.TAMANIO_BIT, new Random());
        } while (numero.compareTo(limInf) < 0 || numero.compareTo(limSup) >= 0);
        return numero;
    }

    /**
     * Valor encontrado de una función especifica en un punto x.
     *
     * @param cocientes Arreglo con los cocientes de un polinomio.
     * @param x Punto x a evaluar.
     * @return Valor encontrado en un punto x de una fución.
     */
    public BigInteger valorEnX(BigInteger[] cocientes, BigInteger x) {
        BigInteger resultado = BigInteger.ZERO;
        for (int j = 0; j < cocientes.length; j++) {
            resultado = resultado.add(
                    cocientes[j].multiply(x.modPow(BigInteger.valueOf(j + 1),
                                    this.getNumPrimo())));
        }
        return resultado;
    }
}
