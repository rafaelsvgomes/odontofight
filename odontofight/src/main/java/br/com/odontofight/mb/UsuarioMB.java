/**
 * Projeto:         Odontofight
 * Camada Projeto:  odontofight
 * Pacote:          br.com.odontofight.mb
 * Arquivo:         UsuarioMB.java
 * Data Criação:    22/02/2016
 */
package br.com.odontofight.mb;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.odontofight.entidade.ClienteSituacao;
import br.com.odontofight.entidade.Grupo;
import br.com.odontofight.entidade.Usuario;
import br.com.odontofight.servico.UsuarioServicoEJB;
import br.com.odontofight.util.MensagemUtil;
import br.com.odontofight.util.SenhaUtil;

/**
 * UsuarioMB
 * 
 * @author Rafael.Silva
 */
@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends GenericMB {

    private static final long serialVersionUID = 1L;
    private String senhaAtual;
    private String novaSenha;

    @EJB
    UsuarioServicoEJB ejb;

    public void alterarSenha() {
        try {
            Usuario usuario = ejb.find(getUsuarioLogado().getIdUsuario());
            if (!usuario.getDescSenha().equals(SenhaUtil.criptografarSenha(senhaAtual))) {
                MensagemUtil.addMensagemErro("msg.erro.senha.atual.incorreta", "");
                return;
            }
            usuario.setDescSenha(SenhaUtil.criptografarSenha(novaSenha));
            ejb.save(usuario);
            MensagemUtil.addMensagemSucesso("msg.sucesso.alterar.senha");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.alterar.senha", ex.getMessage());
        }
    }

    public void efetuarLogout() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsp");
        FacesContext.getCurrentInstance().responseComplete();
    }

    public boolean isAdmin() {
        return getUsuarioLogado().getCodGrupo().equals(Grupo.ADMIN);
    }

    public boolean isCliente() {
        return getUsuarioLogado().getCodGrupo().equals(Grupo.CLIENTE);
    }

    public boolean isGestor() {
        return getUsuarioLogado().getCodGrupo().equals(Grupo.GESTOR);
    }

    public boolean isUser() {
        return getUsuarioLogado().getCodGrupo().equals(Grupo.USER);
    }

    public boolean exibeParaTodos() {
        return isAdmin() || isCliente() || isGestor() || isUser();
    }

    public boolean exibeEfetuarPagamento() {
        return exibeParaTodos() && getUsuarioLogado().getIdClienteSituacao() == ClienteSituacao.CADASTRADO;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

}
