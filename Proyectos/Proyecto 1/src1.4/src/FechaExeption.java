
/**
 * Excepción en caso de escribir una fecha incorrecta.
 *
 * @author Flores Gonzáles Luis Brandon.
 * @version 1.0
 */
public class FechaExeption extends RuntimeException {

    /**
     * Constructor por defecto.
     */
    public FechaExeption() {
        super();
    }

    /**
     * Constructor que recibe una cadena como mensaje.
     * 
     * @param mensaje - Mensaje de error.
     */
    public FechaExeption(String mensaje) {
        super(mensaje);
    }
}
