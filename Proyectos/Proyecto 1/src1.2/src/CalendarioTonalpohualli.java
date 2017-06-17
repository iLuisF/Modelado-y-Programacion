
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
    public final String[] SEMANA = new String[]{"(Cipactli - Caimán)", "(Ehecatl - Viento)",
        "(Calli - Casa)", "(Cuetzpalin - Lagartija)", "(Cóhuatl - Serpiente)", "(Miquiztli - Muerte)",
        "(Mázatl - Ciervo)", "(Tochtli - Conejo)", "(Atl - Agua)", "(Itzcuintli - Perro)",
        "(Ozomatli - Mono)", "(Malinalli - Hierba torcida)", "(Acatl - Caña)", "(Ocelotl - Jaguar)",
        "(Cuauhtli - Aguila)", "(Cozcacuauhtli - Buitre)", "(Olin - Movimiento)", "(Técpatl - Pedernal)",
        "(Quiahuitl - Lluvia)", "(Xóchitl - Flor)"};

    //Nombres del mes del calendario Azteca. //Borrar         
    public final String[] MESES = new String[]{"Atlcahuallo", "Tlacaxipehualitzi",
        "Tozoztontli", "Huey Tozoztli", "Toxcatl", "Etzalculizti", "Tecuilhuitontli",
        "Huey Tecuilhuitl", "Tlaxochimaco", "Xocotl Huetzi", "Ochpaniztli", "Teotelco",
        "Tepeilhuitl", "Quecholli", "Panquetzaliztli", "Atemozli", "Tititl", "Izcalli",
        "Nemontemi"};

    /**
     * Nombres de los 4 signos del calendario Azteca.
     */
    public final String[] SIGNOS = new String[]{"(Tochtli - Conejo)", "(Acatl - Caña)",
        "(Técpatl - Pedernal)", "(Calli - Casa)"};

    /**
     * Constructor por omisión sin parametros.
     */    
    public CalendarioTonalpohualli(){        
    }
    
    public CalendarioTonalpohualli(Calendar calendario){
        this.calendario = calendario;
    }
    
    public String getSacredDate() {
        CalendarioGregoriano calendarioGregoriano = new CalendarioGregoriano();
        CalendarioTonalpohualli calendarioTonalpohualli = new CalendarioTonalpohualli();
        int julianDay = new Double(calendarioGregoriano.getDiaJuliano(
                calendario) - 0.5).intValue();
        int origin = 163;
        int aztecDay = julianDay - origin;

        int tonalliDaySign = aztecDay % 20;
        int tonalliDayNumber = aztecDay % 13;

        int trecenasIndex = ((tonalliDaySign + 20) - tonalliDayNumber) % 20;

        return String.format("1-%s %s-%s",
                calendarioTonalpohualli.SEMANA[trecenasIndex], tonalliDayNumber + 1,
                calendarioTonalpohualli.SEMANA[tonalliDaySign]);
    }

   public String getSolarDate() {
		int year = calendario.get(Calendar.YEAR);
		int dayInYear = calendario.get(Calendar.DAY_OF_YEAR);
		int dateOffset = 31 + 28 + (esAnioBisiesto(year) ? 1 : 0);

		int aztecDay = dayInYear - dateOffset;
		if (aztecDay < 0) {
			year--;
			aztecDay = aztecDay + 365 + (esAnioBisiesto(year) ? 1 : 0);
		}

		int month = aztecDay / 20;
		int dayInMonth = aztecDay % 20;

		int yearOffset = 50;
		int yearNumber = (year - yearOffset) % 13;
		int yearSign = (year - yearOffset) % 4;

		while (yearNumber < 0)
			yearNumber = yearNumber + 13;
		while (yearSign < 0)
			yearSign = yearSign + 4;

		return String.format("%s %s %s-%s", dayInMonth + 1, MESES[month],
				yearNumber + 1, SIGNOS[yearSign]);
	}

	public String getDate() {
		return String.format("Solar date: " + getSolarDate()
				+ ", Sacred date: " + getSacredDate());
	}

    public boolean esAnioBisiesto(int anio) {

        // 1. les ann�es divisibles par 4 mais non divisibles par 100
        // 2. les ann�es divisibles par 400
        return anio % 400 == 0 || anio % 4 == 0 && anio % 100 != 0;
    }

    
    public String toString() {
        return getSacredDate() + getSolarDate() + ":)";
    }

    /**
     * Eliminar public String getDate() { return String.format("Solar date: " +
     * getSolarDate() + ", Sacred date: " + getSacredDate()); }
     */
}
