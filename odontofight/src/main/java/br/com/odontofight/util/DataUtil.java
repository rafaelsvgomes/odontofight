package br.com.odontofight.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author RafaelSGomes
 */
public class DataUtil {
    /**
     * formato dd/mm/aaaa hh:mm
     */
    public static final String DATA_HORA_COMPLETA = "dd/MM/yyyy HH:mm";
    /**
     * formato dd/mm/aaaa
     */
    public static final String DATA_DIA_MES_ANO = "dd/MM/yyyy";

    /**
     * formato ddmmaaaa
     */
    public static final String DATA_DIA_MES_ANO_SEM_BARRA = "ddMMyyyy";
    /**
     * formato dd/mm
     */
    public static final String DATA_DIA_MES = "dd/MM";
    /**
     * formato mm/aaaa
     */
    public static final String DATA_MES_ANO = "MM/yyyy";
    /**
     * hh:mm:ss
     */
    public static final String HORARIO_HORA_COMPLETA = "HH:mm:ss";
    /**
     * hh:mm
     */
    public static final String HORARIO_HORA_MINUTO = "HH:mm";

    public static String toString(Calendar data, String formato) {
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        return formatador.format(data.getTime());
    }

    public static String toString(Date data, String formato) {
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        return formatador.format(data.getTime());
    }

    public static Calendar gerarCalendar(String data, String formato) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        Calendar dataFormatada = new GregorianCalendar();
        dataFormatada.setTime(formatador.parse(data));
        return dataFormatada;
    }
}
