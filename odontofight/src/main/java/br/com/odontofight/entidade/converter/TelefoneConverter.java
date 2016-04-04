package br.com.odontofight.entidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Conversor de NumTelefone.
 *
 * @author Rafael
 */
@FacesConverter(value = "telefoneConverter")
public class TelefoneConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String telefone = value;
        if (value != null && !value.equals("")) {
            telefone = value.replace("(", "").replace(")", "");
        }
        return telefone;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {

        String telefone = (String) value;
        if (telefone != null && telefone.length() > 3) {
            telefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2);
        }
        return telefone;
    }
}