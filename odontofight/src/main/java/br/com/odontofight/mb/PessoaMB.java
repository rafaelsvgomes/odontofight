package br.com.odontofight.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.odontofight.entidade.Banco;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaConta;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.Situacao;
import br.com.odontofight.entidade.TipoConta;
import br.com.odontofight.entidade.UF;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.enums.TipoSexo;
import br.com.odontofight.servico.PessoaServicoEJB;
import br.com.odontofight.util.MensagemUtil;

@ManagedBean(name = "pessoaMB")
@ViewScoped
public class PessoaMB extends GenericPessoaMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    private PessoaServicoEJB ejb;

    private Long idSelecionado;

    private Pessoa pessoa;

    private List<Pessoa> listaPessoas;

    private PessoaConta pessoaConta;

    private Boolean exibirFieldInfBancarias = Boolean.FALSE;

    private List<Banco> listaBancos;

    private List<TipoConta> listaTipoConta;

    public PessoaMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void iniciar() {
        setListaUfs(ejb.findAll(UF.class));
    }

    private void iniciarIncuir() {
        idSelecionado = null;
        pessoa.setTipoPessoa(TipoPessoa.F);
        pessoa.setTipoSexo(TipoSexo.M);
        pessoa.setSituacao(new Situacao(Situacao.CADASTRADO));
        iniciarTelefonePessoa(pessoa);
        iniciarEnderecoPessoa(pessoa);

    }

    @SuppressWarnings("unchecked")
    public void iniciarIncluirIndicacao() {
        if (!isPostBack()) {
            pessoa = new PessoaIndicacao();
            iniciarIncuir();
            iniciarPessoaConta();

            listaBancos = ejb.findAll(Banco.class);
            listaTipoConta = ejb.findAll(TipoConta.class);
            exibirFieldInfBancarias = Boolean.TRUE;
        }
    }

    public void iniciarIncluirAcademia() {
        if (!isPostBack()) {
            pessoa = new PessoaAcademia();
            iniciarIncuir();
        }
    }

    public void iniciarEditarAcademia() {
        if (!isPostBack()) {
            if (idSelecionado == null) {
                return;
            }
            pessoa = ejb.obterPessoaAcademia(idSelecionado);
            iniciarPessoaEditar(idSelecionado);
        }
    }

    @SuppressWarnings("unchecked")
    public void iniciarEditarIndicacao() {
        if (!isPostBack()) {
            if (idSelecionado == null) {
                return;
            }
            pessoa = ejb.obterPessoaIndicacao(idSelecionado);
            pessoaConta = pessoa.getListaPessoaConta().get(0);
            iniciarPessoaEditar(idSelecionado);

            listaBancos = ejb.findAll(Banco.class);
            listaTipoConta = ejb.findAll(TipoConta.class);
            exibirFieldInfBancarias = Boolean.TRUE;
        }
    }

    private void iniciarPessoaEditar(Long idPessoa) {
        iniciarEnderecoPessoa(pessoa);
        iniciarTelefonePessoa(pessoa);
    }

    private void iniciarPessoaConta() {
        pessoaConta = new PessoaConta(pessoa, new Banco(), new TipoConta(), Boolean.TRUE);
        pessoa.addPessoaConta(pessoaConta);
    }

    public String salvar() {
        try {
            ejb.salvarPessoa(pessoa);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.pessoa", ex.getMessage());
            return "";
        }
        MensagemUtil.addMensagemSucesso("msg.sucesso.salvar.pessoa");
        if (pessoa instanceof PessoaIndicacao) {
            return "lista_pessoa_indicacao?faces-redirect=true";
        } else {
            return "lista_pessoa_academia?faces-redirect=true";
        }
    }

    public String remover() {
        try {
            ejb.remove(pessoa);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.remover.pessoa", ex.getMessage());
            return "";
        }
        return "lista_pessoa?faces-redirect=true";
    }

    public void converterPessoaParaCliente(Long idPessoa) {
        ejb.salvaCliente(idPessoa, getUsuarioLogado().getIdPessoa());
    }

    public void iniciarListarPessoasIndicacao() {
        if (!isPostBack()) {
            listaPessoas = ejb.listarPessoasIndicacaoSimples();
        }
    }

    public void iniciarListarPessoasAcademia() {
        if (!isPostBack()) {
            listaPessoas = ejb.listarPessoasAcademiaSimples();
        }
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

    public List<Banco> getListaBancos() {
        return listaBancos;
    }

    public List<TipoConta> getListaTipoConta() {
        return listaTipoConta;
    }

    public Boolean getExibirFieldInfBancarias() {
        return exibirFieldInfBancarias;
    }

}
