package br.com.odontofight.mb;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.com.odontofight.enums.Operacao;
import br.com.odontofight.util.UsuarioSessaoUtil;
import br.com.odontofight.vo.UsuarioLogado;

abstract class GenericMB implements Serializable {

    private static final long serialVersionUID = -1581907663496766815L;

    private Operacao operacao;

    public UsuarioLogado getUsuarioLogado() {
        return new UsuarioSessaoUtil().getUsuarioLogado();
    }

    public Boolean isPostBack() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }
}
