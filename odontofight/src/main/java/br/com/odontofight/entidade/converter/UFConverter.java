package br.com.odontofight.entidade.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.odontofight.entidade.UF;

@FacesConverter(value = "ufConverter", forClass = UF.class)
public class UFConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null && !"".equals(value)) {
            UF entity = (UF) value;

            if (entity.getCodUf() != null) {
                this.addAttribute(component, entity);

                if (entity.getCodUf() != null) {
                    return String.valueOf(entity.getCodUf());
                }
                return (String) value;
            }
        }
        return "";
    }

    private void addAttribute(UIComponent component, UF o) {
        this.getAttributesFrom(component).put(o.getCodUf().toString(), o);
    }

    private Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}