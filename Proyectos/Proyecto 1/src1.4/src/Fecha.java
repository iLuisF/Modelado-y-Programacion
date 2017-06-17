
/**
 * Representación de una fecha con los datos de día, mes, año y dia de la semana.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Fecha {

    private final int dia;
    private final int mes;
    private final int anio;
    private final int diaSemana;

    /**
     * Constructor de una fecha con los atributos de dia de la semana, dia, mes,
     * y año.
     *
     * @param diaSemana - Número de dia de la semana.
     * @param dia - Número de dia.
     * @param mes - Número de mes.
     * @param anio - Número de año.
     */
    public Fecha(int diaSemana, int dia, int mes, int anio) {
        this.diaSemana = diaSemana;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /**
     * Regresa el número de dia.
     *
     * @return - Regresa el dia.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Regresa el número de mes.
     *
     * @return - Regresa el mes.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Regresa el número de año.
     *
     * @return - El año.
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Regresa el número de dia de la semana.
     *
     * @return - El dia de la semana.
     */
    public int getDiaSemana() {
        return diaSemana;
    }
}
