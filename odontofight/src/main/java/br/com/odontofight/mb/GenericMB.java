package br.com.odontofight.mb;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.odontofight.vo.UsuarioLogado;

abstract class GenericMB implements Serializable {

    private static final long serialVersionUID = -1581907663496766815L;

    public UsuarioLogado getUsuarioLogado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        return (UsuarioLogado) session.getAttribute("usuarioLogado");
    }
    
    public Boolean isPostBack(){
        return FacesContext.getCurrentInstance().isPostback();
    }

}
