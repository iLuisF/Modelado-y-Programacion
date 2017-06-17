
import java.util.Calendar;

/**
 * Convierte fechas del calendario Gregoriano a fechas del Calendario
 * Tonalpohualli.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class CalendarioTonalpohualli {

    private Calendar calendario;

    /**
     * 20 Dias de la semana del Calendario en Nahuatl y Español.
     */
    private final String[] SEMANA = new String[]{"(Cipactli - Caiman)", "(Ehecatl - Viento)",
        "(Calli - Casa)", "(Cuetzpalin - Lagartija)", "(Cohuatl - Serpiente)", "(Miquiztli - Muerte)",
        "(Mazatl - Ciervo)", "(Tochtli - Conejo)", "(Atl - Agua)", "(Itzcuintli - Perro)",
        "(Ozomatli - Mono)", "(Malinalli - Hierba torcida)", "(Acatl - Caña)", "(Ocelotl - Jaguar)",
        "(Cuauhtli - Aguila)", "(Cozcacuauhtli - Buitre)", "(Olin - Movimiento)", "(Tecpatl - Pedernal)",
        "(Quiahuitl - Lluvia)", "(Xochitl - Flor)"};

    /**
     * Nombres de los 4 signos del calendario Azteca.
     */
    private final String[] SIGNOS = new String[]{"(Tochtli - Conejo)", "(Acatl - Caña)",
        "(Tecpatl - Pedernal)", "(Calli - Casa)"};

    /**
     * Constructor por omisión sin parametros.
     */
    public CalendarioTonalpohualli() {
    }

    /**
     * Constructor de un calendario, con parametro de un calendario Gregoriano.
     *
     * @param calendario - Calendario Gregoriano.
     */
    public CalendarioTonalpohualli(Calendar calendario) {
        this.calendario = calendario;
    }

    public String getDia() {
        CalendarioGregoriano calendarioGregoriano = new CalendarioGregoriano();
        CalendarioTonalpohualli calendarioTonalpohualli = new CalendarioTonalpohualli();
        int diaJuliano = new Double(calendarioGregoriano.getDiaJuliano(calendario) - 0.5).intValue();
        int origen = 163;
        int diaAzteca = diaJuliano - origen;
        int signoDia = diaAzteca % 20;
        int numeroDia = diaAzteca % 13;
        return (numeroDia + 1) + " - " + calendarioTonalpohualli.SEMANA[signoDia] + " ";
    }

    public String getAnioSolar() {
        int anio = calendario.get(Calendar.YEAR);
        int diaEnAnio = calendario.get(Calendar.DAY_OF_YEAR);
        int dateOffset = 31 + 28 + (esAnioBisiesto(anio) ? 1 : 0);
        int DiaAzteca = diaEnAnio - dateOffset;
        if (DiaAzteca < 0) {
            anio--;
            DiaAzteca = DiaAzteca + 365 + (esAnioBisiesto(anio) ? 1 : 0);
        }
        int mes = DiaAzteca / 20;
        int diaEnMes = DiaAzteca % 20;
        int yearOffset = 50;
        int NumeroAnio = (anio - yearOffset) % 13;
        int signoAnio = (anio - yearOffset) % 4;
        while (NumeroAnio < 0) {
            NumeroAnio = NumeroAnio + 13;
        }
        while (signoAnio < 0) {
            signoAnio = signoAnio + 4;
        }
        return NumeroAnio + 1 + " - " + SIGNOS[signoAnio];

    }

    /**
     * Determina si un año es bisiesto o no.
     *
     * @param anio - Año que se requiere saber si es bisiesto o no.
     * @return True si es bisiesto y False en otro caso.
     */
    public boolean esAnioBisiesto(int anio) {
        //Un año es bisiesto si es divisible por 4, excepto los principios de año
        //(los divisible por 100), ó que para ser bisiesto deben de ser divisibles
        //tambien por 400.
        return ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)));
    }

    /**
     * Representación del calendario Tonalpohualli en una cadena.
     *
     * @return Cadena con fecha Tonalpohualli.
     */
    @Override
    public String toString() {
        return getDia() + getAnioSolar();
    }
}
