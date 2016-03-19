package br.com.odontofight.mb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.RowEditEvent;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.ClienteContrato;
import br.com.odontofight.entidade.ClienteLuta;
import br.com.odontofight.entidade.ContratoRateio;
import br.com.odontofight.entidade.ContratoSituacao;
import br.com.odontofight.entidade.ModalidadeLuta;
import br.com.odontofight.entidade.OrigemPagamento;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.PlanoAssinatura;
import br.com.odontofight.entidade.Situacao;
import br.com.odontofight.entidade.TipoEndereco;
import br.com.odontofight.entidade.TipoTelefone;
import br.com.odontofight.entidade.UF;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.exception.CEPProxyException;
import br.com.odontofight.servico.ClienteServicoEJB;
import br.com.odontofight.util.DataUtil;
import br.com.odontofight.util.MensagemUtil;
import br.com.odontofight.vo.CepServiceVO;
import br.com.odontofight.webservices.CepService;

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteMB extends GenericMB {
    private static final long serialVersionUID = -6556028968452915346L;

    @EJB
    private ClienteServicoEJB ejb;

    private Long idSelecionado;

    private Cliente cliente;

    private PessoaEndereco endereco;

    private PessoaTelefone telefone;

    private PessoaTelefone celular;

    private List<Cliente> listaClientes;

    private List<UF> listaUfs;

    private List<PessoaIndicacao> listaPessoasIndicacao;

    private List<PessoaAcademia> listaPessoasAcademia;

    private List<ModalidadeLuta> listaModalidadeLuta;

    private List<OrigemPagamento> listaOrigemPagamento;

    public ClienteMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        listaUfs = ejb.findAll(UF.class);
        listaModalidadeLuta = ejb.findAll(ModalidadeLuta.class);
    }

    public void iniciarIncluir() {
        if (!isPostBack()) {
            idSelecionado = null;
            initListaPessoasIndicacao();
            initListaPessoasAcademia();

            cliente = new Cliente();
            cliente.setTipoPessoa(TipoPessoa.F);
            cliente.setSituacao(new Situacao(Situacao.CADASTRADO));
            cliente.setClienteLuta(new ClienteLuta());
            iniciarTelefonePessoa();
            iniciarEnderecoPessoa();
        }
    }

    public void iniciarEditar() {
        if (!isPostBack()) {
            if (idSelecionado == null) {
                return;
            }
            initListaPessoasIndicacao();
            initListaPessoasAcademia();

            cliente = ejb.obterCliente(idSelecionado);

            if (!cliente.getListaEndereco().isEmpty()) {
                endereco = cliente.getListaEndereco().get(0);
            } else {
                iniciarEnderecoPessoa();
            }

            if (cliente.getTelefoneResidencial() == null) {
                iniciarTelefoneResidencial();
            } else {
                telefone = cliente.getTelefoneResidencial();
            }
            if (cliente.getTelefoneCelular() == null) {
                iniciarTelefoneCelular();
            } else {
                celular = cliente.getTelefoneCelular();
            }

            if (cliente.getClienteLuta() == null) {
                cliente.setClienteLuta(new ClienteLuta());
            }
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

            if (!salvarClienteLuta()) {
                cliente.setClienteLuta(null);
            }

            ejb.salvarCliente(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.cliente", ex.getMessage());
            return "";
        }
        MensagemUtil.addMensagemSucesso("msg.sucesso.salvar.cliente");
        return "lista_cliente?faces-redirect=true";
    }

    private void initListaPessoasIndicacao() {
        listaPessoasIndicacao = ejb.listarPessoasIndicacao();
    }

    private void initListaPessoasAcademia() {
        listaPessoasAcademia = ejb.listarPessoasAcademia();
    }

    private void iniciarEnderecoPessoa() {
        endereco = new PessoaEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL), cliente);
        cliente.addPessoaEndereco(endereco);
    }

    private void iniciarTelefonePessoa() {
        iniciarTelefoneResidencial();
        iniciarTelefoneCelular();
    }

    private void iniciarTelefoneResidencial() {
        telefone = new PessoaTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL), cliente);
        cliente.addPessoaTelefone(telefone);
    }

    private void iniciarTelefoneCelular() {
        celular = new PessoaTelefone(new TipoTelefone(TipoTelefone.CELULAR), cliente);
        cliente.addPessoaTelefone(celular);
    }

    private Boolean salvarClienteLuta() {
        if (cliente.getClienteLuta() != null
                && (cliente.getClienteLuta().getId() != null || cliente.getClienteLuta().getPessoaAcademia() != null || cliente.getClienteLuta().getModalidadeLuta() != null
                        || !cliente.getClienteLuta().getDescGraduacao().isEmpty() || cliente.getClienteLuta().getDataInicioAcademia() != null)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
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

    private ClienteContrato clienteContrato;
    private List<PlanoAssinatura> listaPlanoAssinatura;

    @SuppressWarnings("unchecked")
    public void iniciarIncluirContratoCliente() {
        if (!isPostBack()) {
            listaPlanoAssinatura = ejb.findAll(PlanoAssinatura.class);
            initListaPessoasIndicacao();
            cliente = ejb.obterCliente(idSelecionado);

            clienteContrato = new ClienteContrato();
            clienteContrato.setCliente(cliente);
            clienteContrato.setContratoSituacao(new ContratoSituacao(ContratoSituacao.CADASTRADO));

            ContratoRateio rateio = new ContratoRateio();
            rateio.setClienteContrato(clienteContrato);
            rateio.setPessoaIndicacao(cliente.getPessoaIndicacao());
            rateio.setValorPercentualRateio(new BigDecimal("100"));

            clienteContrato.getListaContratoRateio().add(rateio);
        }
    }

    public void atualizarClienteContratoTela() {
        clienteContrato.setValorContrato(clienteContrato.getPlanoAssinatura().getValorPlanoAssinatura());
        clienteContrato.setQtdParcela(clienteContrato.getPlanoAssinatura().getQtdParcela());
        clienteContrato.setValorParcela(clienteContrato.getValorContrato().divide(new BigDecimal(clienteContrato.getQtdParcela()), RoundingMode.CEILING));
        clienteContrato.setDiaVencimentoParcela(clienteContrato.getPlanoAssinatura().getDiaVencimentoParcela());
        clienteContrato.setDataInicioContrato(new Date());
        Date dataFim = DataUtil.incrementarData(new Date(), Calendar.MONTH, 12);
        dataFim = DataUtil.decrementarData(dataFim, Calendar.DAY_OF_MONTH, 1);
        clienteContrato.setDataFimContrato(dataFim);
        clienteContrato.setValorComissaoContrato(clienteContrato.getValorContrato().divide(new BigDecimal("12"), RoundingMode.CEILING));
    }

    public void onRowDelete(RowEditEvent event) {
        clienteContrato.getListaContratoRateio().remove(((ContratoRateio) event.getObject()));
    }

    public String salvarContratoCliente() {
        try {
            List<ContratoRateio> listaContratoRateioSalvar = new ArrayList<ContratoRateio>();

            BigDecimal valorTotalRateio = BigDecimal.ZERO;
            for (ContratoRateio contratoRateio : clienteContrato.getListaContratoRateio()) {
                if (rateioRepetido(listaContratoRateioSalvar, contratoRateio)) {
                    MensagemUtil.addMensagemAlerta("msg.erro.rateio.repetido");
                    return "";
                }
                if (contratoRateio.getValorPercentualRateio() != null) {
                    valorTotalRateio = valorTotalRateio.add(contratoRateio.getValorPercentualRateio());
                    listaContratoRateioSalvar.add(contratoRateio);
                }
            }
            if (valorTotalRateio.compareTo(new BigDecimal("100")) != 0) {
                MensagemUtil.addMensagemAlerta("msg.erro.rateio.diferente.100");
                return "";
            }

            ejb.salvarClienteContrato(clienteContrato);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.contrato", ex.getMessage());
            return "";
        }
        return "../cliente/lista_cliente.xhtml?faces-redirect=true";
    }

    public void addRow() {
        clienteContrato.getListaContratoRateio().add(new ContratoRateio());
    }

    private Boolean rateioRepetido(List<ContratoRateio> listaclienteSalvar, ContratoRateio rateioAtual) {
        for (ContratoRateio contratoRateio : listaclienteSalvar) {
            if (contratoRateio.getPessoaIndicacao().equals(rateioAtual.getPessoaIndicacao())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
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
        return "lista_cliente?faces-redirect=true";
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
        this.endereco.setDescNumero("");
        this.endereco.setDescComplemento("");
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

    public void iniciarListarClientes() {
        if (!isPostBack()) {
            listaClientes = ejb.listarClientesSimples();
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
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

    public Cliente getCliente() {
        return cliente;
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

    public List<OrigemPagamento> getListaOrigemPagamento() {
        return listaOrigemPagamento;
    }

    public List<PessoaIndicacao> getListaPessoasIndicacao() {
        return listaPessoasIndicacao;
    }

    public List<PessoaAcademia> getListaPessoasAcademia() {
        return listaPessoasAcademia;
    }

    public List<ModalidadeLuta> getListaModalidadeLuta() {
        return listaModalidadeLuta;
    }

    public ClienteContrato getClienteContrato() {
        return clienteContrato;
    }

    public List<PlanoAssinatura> getListaPlanoAssinatura() {
        return listaPlanoAssinatura;
    }
}
