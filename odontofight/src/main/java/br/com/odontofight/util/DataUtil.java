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

    public static Date getDataVencimentoMesAtual(Integer diaVencimento) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, diaVencimento);
        return c.getTime();
    }

    /**
     * Incrementa uma data a partir da sua escala, definida pelas constantes de Calendar.
     * 
     * @param data a data a ser incrementada.
     * @param escala a escala.
     * @param valor o valor a ser incrementado.
     * @return uma nova data, incrementada.
     */
    public static Date incrementarData(Date data, int escala, int valor) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        calendario.add(escala, valor);

        return calendario.getTime();
    }

    /**
     * Decrementa uma data a partir da sua escala, definida pelas constantes de Calendar.
     * 
     * @param data a data a ser decrementada.
     * @param escala a escala.
     * @param valor o valor a ser decrementado.
     * @return uma nova data, decrementada.
     */
    public static Date decrementarData(Date data, int escala, int valor) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        calendario.add(escala, -valor);

        return calendario.getTime();
    }
}
