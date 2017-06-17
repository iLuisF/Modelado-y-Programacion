
/**
 * Representación de una fecha.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Fecha {

    private final Integer dia;
    private final Integer mes;
    private final Integer anio;
    private final Integer diaSemana;

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
    public Integer getDia() {
        return dia;
    }

    /**
     * Regresa el número de mes.
     *
     * @return - Regresa el mes.
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * Regresa el número de año.
     *
     * @return - El año.
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * Regresa el número de dia de la semana.
     *
     * @return - El dia de la semana.
     */
    public Integer getDiaSemana() {
        return diaSemana;
    }    
}
