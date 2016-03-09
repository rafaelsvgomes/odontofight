package br.com.odontofight.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaTelefone;
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

    private List<UF> listaUfs;

    public PessoaMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        listaUfs = ejb.findAll(UF.class);
    }

    public void incluir() {
        if (!isPostBack()) {
            idSelecionado = null;

            pessoa = new Cliente();
            pessoa.setTipoPessoa(TipoPessoa.F);
            setTelefonePessoa();
            setEnderecoPessoa();
        }
    }

    public void editar() {
        if (!isPostBack()) {
            if (idSelecionado == null) {
                return;
            }
            iniciarClienteEditar(idSelecionado);
        }
    }

    private void iniciarClienteEditar(Long idCliente) {
        pessoa = ejb.obterPessoa(idCliente);
        endereco = pessoa.getListaEndereco().get(0);
        telefone = pessoa.getTelefoneResidencial();
        celular = pessoa.getTelefoneCelular();

    }

    private void setEnderecoPessoa() {
        endereco = new PessoaEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL), pessoa);
        pessoa.addPessoaEndereco(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new PessoaTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL), pessoa);
        celular = new PessoaTelefone(new TipoTelefone(TipoTelefone.CELULAR), pessoa);
        pessoa.addPessoaTelefone(telefone);
        pessoa.addPessoaTelefone(celular);
    }

    public String salvar() {
        try {
            if (pessoa.getId() == null || pessoa.getId() == 0) {
                pessoa.setDataCadastro(new Date());
                // TODO: rafael - pedir para Anderson incluir na pessoa
                // pessoa.setDataAtualizacao(new Date());
            }
            // else {
            // pessoa.setDataAtualizacao(new Date());
            // }

            ejb.save(pessoa);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.pessoa", ex.getMessage());
            return "";
        }
        MensagemUtil.addMensagemSucesso("msg.sucesso.salvar.pessoa");
        return "lista_pessoa?faces-redirect=true";
    }

    public String remover() {
        try {
            ejb.remove(pessoa);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.remover.cliente", ex.getMessage());
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

    public void iniciarListarPessoas() {
        if (!isPostBack()) {
            listaPessoas = ejb.listarPessoasSimples();
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

}
