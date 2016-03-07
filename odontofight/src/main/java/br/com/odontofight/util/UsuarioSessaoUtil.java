/**
 * Projeto:         Clay Cosméticos
 * Camada Projeto:  clay-mmn
 * Pacote:          br.com.clay.util
 * Arquivo:         UsuarioSessaoUtil.java
 * Data Criação:    04/03/2016
 */
package br.com.odontofight.util;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.odontofight.servico.UsuarioServicoEJB;
import br.com.odontofight.vo.UsuarioLogado;

/**
 * UsuarioSessaoUtil
 * 
 * @author Rafael.Silva
 */
public class UsuarioSessaoUtil {

    public static String USUARIO_LOGADO = "usuarioLogado";

    @EJB
    private UsuarioServicoEJB usuarioServico;

    public void atualizarUsuarioSessao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        UsuarioLogado usuario = usuarioServico.obterUsuario(getUsuarioLogado().getIdCliente());
        session.setAttribute(USUARIO_LOGADO, usuario);
    }

    public UsuarioLogado getUsuarioLogado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            return null;
        }
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        return (UsuarioLogado) session.getAttribute(USUARIO_LOGADO);
    }

}
