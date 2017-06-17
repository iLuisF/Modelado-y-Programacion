
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Representación del calendario Gregoriano usado actualmente.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class CalendarioGregoriano {

    private Calendar calendario;

    /**
     * Constructor sin parametros de un calendario Gregoriano.
     */
    public CalendarioGregoriano() {
    }

    /**
     * Constructor de un calendario Gregoriano que requiere de una instancia de
     * Calendar.
     *
     * @param calendario
     */
    public CalendarioGregoriano(Calendar calendario) {
        this.calendario = calendario;
    }

    /**
     * Regresa la fecha gregoriana para la fecha pasada como parametro.
     *
     * @return Fecha gregoriana.
     */
    public Fecha getFecha() {
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK - 1);
        System.out.println(diaSemana);
        if (diaSemana < 1) {
            diaSemana = diaSemana + 7;
        }
        return new Fecha(diaSemana, calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.MONTH) + 1, calendario.get(Calendar.YEAR));
    }

    /**
     * Regresa el dia del año del calendario pasado como parametro.
     *
     * @return - Regresa el día del año.
     */
    public int getDiaAnio() {
        return calendario.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Regresa el dia Juliano.
     *
     * @return Dia Juliano.
     */
    public double getDiaJuliano() {
        Double tiempo = (double) calendario.getTimeInMillis();
        tiempo -= calendario.get(Calendar.ZONE_OFFSET) + calendario.get(Calendar.DST_OFFSET);
        return tiempo / 86400000 + 2440587.5;
    }

    /**
     * Verifica si una fecha es valida o no.
     *
     * @param fecha Fecha que se quiere verificar si es correcta o no.
     * @return True si es una fecha corresta y false en otro caso.
     */
    public boolean esFechaValida(String fecha) {
        try {
            //Formato para identificar la fecha.
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy", Locale.FRENCH);
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException excepcion) {
            return false;
        }
        return true;
    }

    /**
     * Convierte la cadena del mes en el número de mes correspondiente. Solo
     * toma los tres primeros caracteres en cuenta. Si alguno de estos tres
     * caracteres no corresponde algun mes, se lanzara un excepción.
     *
     * @param mes - Mes que se quiere convertir a número.
     * @return - Regresa el número de mes correspondiente.
     */
    public Integer convierteMes(String mes) {
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
                throw new FechaExeption("Mes incorrecto.");
        }
    }
}
