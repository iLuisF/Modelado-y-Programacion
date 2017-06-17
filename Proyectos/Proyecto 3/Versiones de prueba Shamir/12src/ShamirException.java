
/**
 * Representación de una excepción en el esquema de shamir.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class ShamirException extends RuntimeException{

    /**
     * Constructor por omision sin parametros.
     */
    public ShamirException(){
        super();
    }
    
    /*
    * Construye una excepción dada del esquema de shamir con un mensaje.
    */
    public ShamirException(String excepcion){
        super(excepcion);
    }
    
}
