/**
 * Excepción en caso de escribir una fecha incorrecta.
 * @author Flores Gonzáles Luis Brandon.
 */
public class FechaExeption extends RuntimeException{
    
    public FechaExeption(){
        super();
    }
    
    public FechaExeption(String mensaje){
        super(mensaje);
    }
    
}
