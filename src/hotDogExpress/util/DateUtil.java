/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotDogExpress.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    /** O padrão usado para conversão. Mude como quiser. */
    private static final String DATE_PATTERN = "dd/MM/yyyy";

    /** O formatador de data. */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Retorna os dados como String formatado. O 
     * {@link DateUtil#DATE_PATTERN}  (padrão de data) que é utilizado.
     * 
     * @param date A data a ser retornada como String
     * @return String formadado
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converte um String no formato definido {@link DateUtil#DATE_PATTERN} 
     * para um objeto {@link LocalDate}.
     * 
     * Retorna null se o String não puder se convertido.
     * 
     * @param dateString a data como String
     * @return o objeto data ou null se não puder ser convertido
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checa se o String é uma data válida.
     * 
     * @param dateString A data como String
     * @return true se o String é uma data válida
     */
    public static boolean validDate(String dateString) {
        // Tenta converter o String.
        return DateUtil.parse(dateString) != null;
    }    
}
