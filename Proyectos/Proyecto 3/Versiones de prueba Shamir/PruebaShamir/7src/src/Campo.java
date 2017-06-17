
import java.math.BigInteger;
import java.util.Random;

/**
 * Representación de una campo finito de numeros.
 *
 * @author Flores González Luis Brandon.
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
        System.out.println("Campo: " + this.primoGrande);
        this.setDominio();
    }

    /**
     * Asigna un dominio apartir de un numero primo, es decir, un campo con sus
     * limites respectivos.
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
     * Genera un número aleatorio del campo finito especificado.
     *     
     * @return Número aleatorio.
     */
    public BigInteger getNumero(BigInteger limiteInferior, BigInteger limiteSuperior) {
        BigInteger numero;
        do {
            numero = new BigInteger(this.TAMANIO_BIT, new Random());
        } while (numero.compareTo(limiteInferior) < 0 || numero.compareTo(limiteSuperior) >= 0);
        return numero;
    }
}
