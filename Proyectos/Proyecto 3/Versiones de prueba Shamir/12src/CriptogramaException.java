
/**
 * Excepción en caso de error en el criptograma.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class CriptogramaException extends Exception {

    /**
     * Constructor por omisión.
     */
    public CriptogramaException() {
    }

    /**
     * Constructor con un mensaje de la excepción.
     *
     * @param mensaje Mensaje de la excepción.
     * @param throwable
     */
    public CriptogramaException(String mensaje, Throwable throwable) {
        super(mensaje, throwable);
    }
}
