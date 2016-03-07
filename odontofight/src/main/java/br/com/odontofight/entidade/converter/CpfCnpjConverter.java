package br.com.odontofight.entidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Conversor de CPF.
 *
 * @author Rafael
 */
public class CpfCnpjConverter implements Converter {

    /**
     * Converter CPF/CNPJ formatado para um sem pontos e traço. Ex.: 355.245.198-87 torna-se 35524519887. Ex.: 43.206.717/0001-05 torna-se 43206717000105.
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String cpfCnpj = value;
        if (value != null && !value.equals("")) {
            cpfCnpj = value.replace(".", "").replace("-", "").replace("/", "");
        }
        return cpfCnpj;
    }

    /**
     * Converter CPF/CNPJ não formatado para um com pontos e traço. Ex.: 35524519887 torna-se 355.245.198-87. Ex.: 43206717000105 torna-se 43.206.717/0001-05.
     */
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {

        String cpfCnpj = (String) value;
        if (cpfCnpj != null && cpfCnpj.length() == 11) {
            cpfCnpj = cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3, 6) + "." + cpfCnpj.substring(6, 9) + "-" + cpfCnpj.substring(9, 11);
        } else {
            cpfCnpj = cpfCnpj.substring(0, 2) + "." + cpfCnpj.substring(2, 5) + "." + cpfCnpj.substring(5, 8) + "/" + cpfCnpj.substring(8, 12) + "-" + cpfCnpj.substring(12);

        }

        return cpfCnpj;
    }
}