package br.com.odontofight.entidade.validation;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.odontofight.servico.ClienteServicoEJB;
import br.com.odontofight.util.MensagemUtil;

@ManagedBean(name = "emailValidator")
@RequestScoped
public class EmailValidator implements Validator {

    @EJB
    private ClienteServicoEJB ejb;

    // TODO: Verificar validação, esta apenas para cliente.

    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {
        if (submittedValue == null) {
            return;
        }
        String username = (String) submittedValue;
        UIParameter p = (UIParameter) component.findComponent("idSelecionado");
        Long idClienteSelecionado = p.getValue() != null ? (Long) p.getValue() : null;

        if (ejb.emailJaUtilizado(username, idClienteSelecionado)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, MensagemUtil.getMessageFromValidationMessages("email.ja.cadastrado"), null));
        }

    }
}
