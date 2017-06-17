
import java.util.Calendar;

/**
 * Representación del calendario Gregoriano usado actualmente.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class CalendarioGregoriano {

    /**
     * Regresa la fecha gregoriana para la fecha pasada como parametro.
     *
     * @param calendario - Calendario.
     * @return Fecha gregoriana.
     */
    public Fecha getFecha(Calendar calendario) {
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
     * @param calendario - Calendario que se usara para obtener el día del año.
     * @return - Regresa el día del año.
     */
    public int getDiaAnio(Calendar calendario) {
        return calendario.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Regresa el dia Juliano.
     *
     * @param calendario
     * @return Dia Juliano.
     */
    public double getDiaJuliano(Calendar calendario) {
        Double tiempo = (double) calendario.getTimeInMillis();
        tiempo -= calendario.get(Calendar.ZONE_OFFSET) + calendario.get(Calendar.DST_OFFSET);
        return tiempo / 86400000 + 2440587.5;
    }
}
