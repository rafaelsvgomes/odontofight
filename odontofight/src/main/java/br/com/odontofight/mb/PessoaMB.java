package br.com.odontofight.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.com.odontofight.entidade.Banco;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaConta;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.Situacao;
import br.com.odontofight.entidade.TipoConta;
import br.com.odontofight.entidade.TipoEndereco;
import br.com.odontofight.entidade.TipoTelefone;
import br.com.odontofight.entidade.UF;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.exception.CEPProxyException;
import br.com.odontofight.servico.PessoaServicoEJB;
import br.com.odontofight.util.MensagemUtil;
import br.com.odontofight.vo.CepServiceVO;
import br.com.odontofight.webservices.CepService;

@ManagedBean(name = "pessoaMB")
@ViewScoped
public class PessoaMB extends GenericMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    private PessoaServicoEJB ejb;

    private Long idSelecionado;

    private Pessoa pessoa;

    private List<Pessoa> listaPessoas;

    private PessoaEndereco endereco;

    private PessoaTelefone telefone;

    private PessoaTelefone celular;

    private PessoaConta pessoaConta;

    private Boolean exibirFieldInfBancarias = Boolean.FALSE;

    private List<UF> listaUfs;

    private List<Banco> listaBancos;

    private List<TipoConta> listaTipoConta;

    public PessoaMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void iniciar() {
        listaUfs = ejb.findAll(UF.class);
    }

    private void iniciarIncuir() {
        idSelecionado = null;
        pessoa.setTipoPessoa(TipoPessoa.F);
        pessoa.setSituacao(new Situacao(Situacao.CADASTRADO));
        iniciarTelefonePessoa();
        iniciarEnderecoPessoa();

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
        if (!pessoa.getListaEndereco().isEmpty()) {
            endereco = pessoa.getListaEndereco().get(0);
        } else {
            iniciarEnderecoPessoa();
        }

        if (pessoa.getTelefoneResidencial() == null) {
            iniciarTelefoneResidencial();
        } else {
            telefone = pessoa.getTelefoneResidencial();
        }
        if (pessoa.getTelefoneCelular() == null) {
            iniciarTelefoneCelular();
        } else {
            celular = pessoa.getTelefoneCelular();
        }

    }

    private void iniciarEnderecoPessoa() {
        endereco = new PessoaEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL), pessoa);
        pessoa.addPessoaEndereco(endereco);
    }

    private void iniciarTelefonePessoa() {
        iniciarTelefoneResidencial();
        iniciarTelefoneCelular();
    }

    private void iniciarTelefoneResidencial() {
        telefone = new PessoaTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL), pessoa);
        pessoa.addPessoaTelefone(telefone);
    }

    private void iniciarTelefoneCelular() {
        celular = new PessoaTelefone(new TipoTelefone(TipoTelefone.CELULAR), pessoa);
        pessoa.addPessoaTelefone(celular);
    }

    private void iniciarPessoaConta() {
        pessoaConta = new PessoaConta(pessoa, new Banco(), new TipoConta(), Boolean.TRUE);
        pessoa.addPessoaConta(pessoaConta);
    }

    public String salvar() {
        try {
            if (pessoa.getId() == null || pessoa.getId() == 0) {
                pessoa.setDataCadastro(new Date());
                pessoa.setDataAtualizacao(new Date());
            } else {
                pessoa.setDataAtualizacao(new Date());
            }

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

    /**
     * Metodo responsavel por buscar o cep no webService
     * 
     * @param e
     * @return String
     * 
     */
    public String buscarCep(ValueChangeEvent e) {
        String cep = e.getNewValue().toString();
        if (cep != null && !cep.isEmpty() && cep.trim().replaceAll("_", "").replace("-", "").length() == 8) {
            CepService cepService = new CepService();
            CepServiceVO cepServiceVO = null;

            cepServiceVO = buscarCEPWebService(cep, cepService, cepServiceVO);
            if (cepServiceVO != null) {
                if (cepServiceVO.getErro() != null && !cepServiceVO.getErro().isEmpty()) {
                    MensagemUtil.addMensagemInfo("webservice.cep.nao.encontrado");
                    limpaEndereco(cep);
                }
                populaEndereco(cep, cepServiceVO);
            }
        } else {
            limpaEndereco(cep);
        }
        return "lista_pessoa?faces-redirect=true";
    }

    private CepServiceVO buscarCEPWebService(String cep, CepService cepService, CepServiceVO cepServiceVO) {
        try {
            cepServiceVO = cepService.buscarCepWebService(cep);
        } catch (CEPProxyException e) {
            MensagemUtil.addMensagemInfo("webservice.cep.erro");
            limpaEndereco(cep);
        }
        return cepServiceVO;
    }

    private void limpaEndereco(String cep) {
        this.endereco.setDescBairro("");
        this.endereco.setDescCidade("");
        this.endereco.setDescEndereco("");
        this.endereco.setNumCep(cep);
        this.endereco.setDescNumero("");
        this.endereco.setDescComplemento("");
        this.endereco.setUf(new UF());
    }

    /**
     * Metodo responsavel por popular os enderecos trago pelo web service
     * 
     * @param cep
     * @param cepServiceVO void
     * 
     */
    private void populaEndereco(String cep, CepServiceVO cepServiceVO) {
        this.endereco.setDescBairro(cepServiceVO.getBairro());
        this.endereco.setDescCidade(cepServiceVO.getLocalidade());
        this.endereco.setDescEndereco(cepServiceVO.getLogradouro());
        this.endereco.setNumCep(cep);
        this.endereco.setUf(getUF(cepServiceVO.getUf()));
    }

    private UF getUF(String codUf) {
        for (UF uf : listaUfs) {
            if (codUf.equals(uf.getCodUf())) {
                return uf;
            }
        }
        return null;
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

    public List<UF> getListaUfs() {
        return listaUfs;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public PessoaEndereco getEndereco() {
        return endereco;
    }

    public PessoaTelefone getTelefone() {
        return telefone;
    }

    public PessoaTelefone getCelular() {
        return celular;
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
