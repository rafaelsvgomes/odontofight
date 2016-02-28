package br.com.odontofight.mb;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LogoutMB {
    public void efetuarLogout() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsp");
        FacesContext.getCurrentInstance().responseComplete();
    }
}
