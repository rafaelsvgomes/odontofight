package br.com.odontofight.entidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Conversor de CPF.
 *
 * @author Rafael
 */
@FacesConverter(value = "telefoneConverter")
public class TelefoneConverter implements Converter {

    /**
     * Converter CPF formatado para um sem pontos e traço. Ex.: 355.245.198-87 torna-se 35524519887.
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String telefone = value;
        if (value != null && !value.equals("")) {
            telefone = value.replace("(", "").replace(")", "");
        }
        return telefone;
    }

    /**
     * Converter CPF não formatado para um com pontos e traço. Ex.: 35524519887 torna-se 355.245.198-87.
     */
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {

        String telefone = (String) value;
        if (telefone != null && telefone.length() == 11) {
            telefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2);
        }
        return telefone;
    }

    public static void main(String[] args) {
        String telefone = "(61)92336666";
        if (telefone != null && telefone.length() == 11) {
            telefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2);
        }
        System.out.println(telefone);
    }
}