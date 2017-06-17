
import java.util.Calendar;

/**
 * Clase para probar la correlación con los dos tipos de calendario. La entrada
 * es estandar y requiere del siguiente formato. DIA MES AÑO donde DIA son dos
 * digitos a lo más, en Mes es al menos tres caracteres para el nombre del mes
 * en español y en AÑO es exactamente 4 digitos. Esta entrada debe ser con un
 * espacio y con el orden especificado.
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Main {

    /**
     * Método principal para probar el programa, el cual requiere tres parametros
     * desde la entrada estandar. El formato de los parametros es el siguiente
     * DIA MES AÑO donde DIA es a lo más dos digitos, MES al menos tres caracteres
     * para el nombre del mes en español, AÑO exactamente 4 digitos.
     * @param args - args[0] Es el año con exactamente 4 digitos, args[1] Es el
     * mes con al menos tres caracteres para el nombre del mes en español, 
     * args[2] Es el día del mes con dos digitos a lo mas.
     */
    public static void main(String[] args) { //Alfonso Caso :D
        try {            
            Calendar fecha = Calendar.getInstance();     
            CalendarioGregoriano calendarioGregoriano = new CalendarioGregoriano();
            fecha.set(Integer.parseInt(args[2]), calendarioGregoriano.convierteMes(args[1]), 
                    Integer.parseInt(args[0]) - 1); // Diferencia de un dia. Si se quiere el dia 15 se debe poner el dia 14.            
            CalendarioTonalpohualli calendarioTonalpohualli = new CalendarioTonalpohualli(fecha);
            System.out.println(calendarioTonalpohualli + "\nFecha: " + args[0] + "/" + 
                     (calendarioGregoriano.convierteMes(args[1]) +1) + "/" + args[2]);
        } catch (FechaExeption excepcion) {
            System.err.println(excepcion);
        } catch(ArrayIndexOutOfBoundsException excepcion){
            System.err.println("Debes escribir el siguiente formato de fecha\t Dia Mes Año"
                    + " \tcon el orden y espacios correspondientes."
                    + "\n- Dia: Dos digitos a lo más para el día del mes."
                    + "\n- Mes: Al menos tres caracteres para el nombre del mes en español."
                    + "\n- Año: Exactamente 4 digitos para el año.\n" + excepcion );
        }catch(Exception excepcion){
            System.out.println(excepcion.getMessage());
        }       
    }    
}
