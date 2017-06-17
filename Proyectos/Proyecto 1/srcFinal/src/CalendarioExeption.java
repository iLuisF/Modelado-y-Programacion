
/**
 * Excepción en caso de escribir una fecha incorrecta de un calendario.
 *
 * @author Flores Gonzáles Luis Brandon.
 * @version 1.0
 */
public class CalendarioExeption extends RuntimeException {

    /**
     * Constructor por defecto.
     */
    public CalendarioExeption() {
        super();
    }

    /**
     * Constructor que recibe una cadena como mensaje.
     * 
     * @param mensaje Mensaje de error.
     */
    public CalendarioExeption(String mensaje) {
        super(mensaje);
    }
}
