package br.com.odontofight.mb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.ClienteLuta;
import br.com.odontofight.entidade.Situacao;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.servico.ClienteServicoEJB;
import br.com.odontofight.util.MensagemUtil;

@ManagedBean(name = "contratoMB")
@ViewScoped
public class ContratoMB extends GenericMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    private ClienteServicoEJB ejb;

    private Long idSelecionado;

    private Cliente cliente;

    public ContratoMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
    }

    public void incluir() {
        if (!isPostBack()) {
            idSelecionado = null;
            // initListaPessoasIndicacao();
            // initListaPessoasAcademia();

            cliente = new Cliente();
            cliente.setTipoPessoa(TipoPessoa.F);
            cliente.setSituacao(new Situacao(Situacao.CADASTRADO));
            cliente.setClienteLuta(new ClienteLuta());
        }
    }

    public void editar() {
        if (!isPostBack()) {
            if (idSelecionado == null) {
                return;
            }
            // initListaPessoasIndicacao();
            // initListaPessoasAcademia();

            cliente = ejb.obterPessoa(idSelecionado);

        }
    }

    public String salvar() {
        try {
            if (cliente.getId() == null || cliente.getId() == 0) {
                cliente.setDataCadastro(new Date());
                cliente.setDataAtualizacao(new Date());
            } else {
                cliente.setDataAtualizacao(new Date());
            }

            // ejb.salvarCliente(cliente, verificarTelefone());
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.cliente", ex.getMessage());
            return "";
        }
        MensagemUtil.addMensagemSucesso("msg.sucesso.salvar.cliente");
        return "lista_cliente?faces-redirect=true";
    }

    public String remover() {
        try {
            ejb.remove(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.remover.cliente", ex.getMessage());
            return "";
        }
        return "lista_cliente?faces-redirect=true";
    }

    public void iniciarListarClientes() {
        if (!isPostBack()) {
            // listaClientes = ejb.listarClientesSimples();
        }
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
