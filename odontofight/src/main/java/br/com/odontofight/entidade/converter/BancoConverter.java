package br.com.odontofight.entidade.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.odontofight.entidade.Banco;

@FacesConverter(value = "bancoConverter", forClass = Banco.class)
public class BancoConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null && !"".equals(value)) {
            Banco entity = (Banco) value;

            if (entity.getCodBanco() != null) {
                this.addAttribute(component, entity);

                if (entity.getCodBanco() != null) {
                    return String.valueOf(entity.getCodBanco());
                }
                return (String) value;
            }
        }
        return "";
    }

    private void addAttribute(UIComponent component, Banco o) {
        this.getAttributesFrom(component).put(o.getCodBanco().toString(), o);
    }

    private Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}