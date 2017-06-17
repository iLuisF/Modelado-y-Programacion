
import java.util.Calendar;

/**
 * Clase para probar la correlación con los dos tipos de calendario. La entrada
 * es estandar y requiere del siguiente formato. DIA MES AÑO donde DIA son dos
 * digitos a lo más, en Mes es al menos tres caracteres para el nombre del mes
 * en español y en AÑO es exactamente 4 digitos. Esta entrada debe ser con un
 * espacio y con el orden especificado.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Main {

    /**
     * Método principal para probar el programa, el cual requiere tres
     * parametros desde la entrada estandar. El formato de los parametros es el
     * siguiente DIA MES AÑO donde DIA es a lo más dos digitos, MES al menos
     * tres caracteres para el nombre del mes en español, AÑO exactamente 4
     * digitos.
     *
     * @param args - args[0] Es el año con exactamente 4 digitos, args[1] Es el
     * mes con al menos tres caracteres para el nombre del mes en español,
     * args[2] Es el día del mes con dos digitos a lo mas.
     */
    public static void main(String[] args) {
        try {
            Calendar fecha = Calendar.getInstance();
            CalendarioGregoriano calendarioGregoriano = new CalendarioGregoriano();
            //Si es una fecha valida devuelve una fecha del calendario Tonalpohualli.
            if (calendarioGregoriano.esFechaValida(args[0] + " "
                    + String.valueOf(calendarioGregoriano.convierteMes(args[1]) + 1)
                    + " " + args[2])) {
                fecha.set(Integer.parseInt(args[2]), calendarioGregoriano.convierteMes(args[1]),
                        Integer.parseInt(args[0]));     
                CalendarioTonalpohualli calendarioTonalpohualli = new CalendarioTonalpohualli(fecha);
                System.out.println(calendarioTonalpohualli + "\nFecha: " + args[0] + "/"
                        + (calendarioGregoriano.convierteMes(args[1]) + 1)
                        + "/" + args[2]);
            } else {
                throw new CalendarioExeption("Fecha incorrecta.");
            }
        } catch (Exception excepcion) {
            System.err.println("Debes escribir el siguiente formato de fecha DIA MES AÑO"
                    + " con el orden y espacios correspondientes."
                    + "\n- DIA: Dos digitos a lo más para el día del mes."
                    + "\n- MES: Al menos tres caracteres para el nombre del mes en español."
                    + "\n- AÑO: Exactamente 4 digitos para el año.\n"
                    + excepcion.getMessage());
        }
    }
}
