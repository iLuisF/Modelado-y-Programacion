
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
     * Convierte la cadena del mes en el número de mes correspondiente.
     *
     * @param mes - Mes que se quiere convertir a número.
     * @return - Regresa el número de mes correspondiente.
     */
    public static Integer convierteMes(String mes) {
        char[] caracteres = mes.toCharArray();
        String mesLetras = "";
        for (int i = 0; i < 3; i++) {
            mesLetras = mesLetras + String.valueOf(caracteres[i]);
        }
        switch (mesLetras.toLowerCase()) {
            case "ene":
                return 0;
            case "feb":
                return 1;
            case "mar":
                return 2;
            case "abr":
                return 3;
            case "may":
                return 4;
            case "jun":
                return 5;
            case "jul":
                return 6;
            case "ago":
                return 7;
            case "sep":
                return 8;
            case "oct":
                return 9;
            case "nov":
                return 10;
            case "dic":
                return 11;
            default:
                throw new FechaExeption("Fecha incorrecta.");
        }        
    }
    
    public static void main(String[] args) { //Alfonso Caso :D
        try {            
            Calendar fecha = Calendar.getInstance();
            CalendarioTonalpohualli calendarioTonalpohualli = new CalendarioTonalpohualli(fecha);            
            fecha.set(Integer.parseInt(args[0]), convierteMes(args[1]), Integer.parseInt(args[2]) - 1); // Diferencia de un dia. Si se quiere el dia 15 se debe poner el dia 14.            
            System.out.println(calendarioTonalpohualli + "\nFecha: " + args[0] + "/" + 
                    args[1] + "/" + args[2]);
        } catch (FechaExeption excepcion) {
            System.out.println(excepcion);
        } catch(ArrayIndexOutOfBoundsException excepcion){
            System.out.println("Debes escribir el siguiente formato de fecha\t Dia Mes Año"
                    + " \tcon el orden y espacios correspondientes."
                    + "\n- Dia: Dos digitos a lo más para el día del mes."
                    + "\n- Mes: Al menos tres caracteres para el nombre del mes en español."
                    + "\n- Año: Exactamente 4 digitos para el año." );
        }        
    }    
}
