package br.com.odontofight.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.com.odontofight.entidade.Banco;
import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.ClienteRede;
import br.com.odontofight.entidade.ClienteSituacao;
import br.com.odontofight.entidade.Grupo;
import br.com.odontofight.entidade.OrigemPagamento;
import br.com.odontofight.entidade.Pedido;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaConta;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.PlanoAssinatura;
import br.com.odontofight.entidade.Produto;
import br.com.odontofight.entidade.TipoConta;
import br.com.odontofight.entidade.TipoEndereco;
import br.com.odontofight.entidade.TipoTelefone;
import br.com.odontofight.entidade.UF;
import br.com.odontofight.entidade.Usuario;
import br.com.odontofight.entidade.UsuarioGrupo;
import br.com.odontofight.entidade.UsuarioPessoa;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.exception.CEPProxyException;
import br.com.odontofight.servico.ClienteServicoEJB;
import br.com.odontofight.util.CpfCnpjUtil;
import br.com.odontofight.util.Email;
import br.com.odontofight.util.EmailUtil;
import br.com.odontofight.util.MensagemUtil;
import br.com.odontofight.util.SenhaUtil;
import br.com.odontofight.vo.CepServiceVO;
import br.com.odontofight.webservices.CepService;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

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

    private PessoaConta pessoaConta;

    private List<Cliente> listaClientes;

    private List<UF> listaUfs;

    private List<Banco> listaBancos;

    private List<TipoConta> listaTipoConta;

    private List<PlanoAssinatura> listaPlanoAssinatura;

    private List<Produto> listaProdutosPlanoAssinatura;

    private List<Cliente> listaClientesIndicadores;

    private List<OrigemPagamento> listaOrigemPagamento;

    private Pedido pedido;

    private Boolean isPagSeguro;

    public ClienteMB() {
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        listaUfs = ejb.findAll(UF.class);
        listaBancos = ejb.findAll(Banco.class);
        listaTipoConta = ejb.findAll(TipoConta.class);
        listaPlanoAssinatura = ejb.listarPlanoAssinatura();
    }

    public void incluir() {
        if (!isPostBack()) {
            idSelecionado = null;

            cliente = new Cliente();
            cliente.setTipoPessoa(TipoPessoa.F);
            cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.CADASTRADO));
            setTelefonePessoa();
            setEnderecoPessoa();
            setPessoaConta();

            ClienteRede clienteRede = new ClienteRede();
            clienteRede.setClienteIndicador(new Cliente(getUsuarioLogado().getIdCliente(), getUsuarioLogado().getNomePessoa()));
            clienteRede.setCliente(cliente);
            cliente.setClienteRede(clienteRede);

            initListaClienteIndicador();
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
        cliente = ejb.obterPessoa(idCliente);
        endereco = cliente.getListaEndereco().get(0);
        pessoaConta = cliente.getListaPessoaConta().get(0);

        if (cliente.getClienteRede() != null) {
            listaClientesIndicadores = new ArrayList<Cliente>();
            listaClientesIndicadores.add(cliente.getClienteRede().getClienteIndicador());
        }

        listaProdutosPlanoAssinatura = ejb.listarProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());

        for (PessoaTelefone tel : cliente.getListaTelefone()) {
            if (tel.getTipoTelefone().getId().equals(TipoTelefone.RESIDENCIAL)) {
                telefone = tel;
            } else {
                celular = tel;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void iniciarPagamento() {
        // TODO: rafael
        // Na hora de gravar salva a alteração efetuada no plano (Ou pode deixar pra alterar só no cadastro) chama o pagamento.
        // Implementar ativar com botão no listar se for grupo admin.
        if (!isPostBack()) {
            iniciarClienteEditar(getUsuarioLogado().getIdCliente());

            listaOrigemPagamento = ejb.findAll(OrigemPagamento.class);
            listaProdutosPlanoAssinatura = ejb.listarProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());
            pedido = new Pedido();
        }
    }

    private String checkoutCode;

    public Boolean getIsPagSeguro() throws PagSeguroServiceException {
        if (pedido.getOrigemPagamento() != null) {
            isPagSeguro = pedido.getOrigemPagamento().getId() == OrigemPagamento.PAG_SEGURO;
            if (isPagSeguro) {
                checkoutCode = new CreateCheckout().getCheckoutCode();
            }
        }
        return isPagSeguro;
    }

    public void setIsPagSeguro(Boolean pagSeguro) {
        this.isPagSeguro = pagSeguro;
    }

    public String ativarCliente(Long idCliente) {
        try {
            cliente = ejb.obterPessoa(idCliente);
            cliente.setClienteSituacao(new ClienteSituacao(ClienteSituacao.ATIVO));
            ejb.save(cliente);

            MensagemUtil.addMensagemSucesso("msg.sucesso.ativar.cliente");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.cliente", ex.getMessage());
        }
        // TODO: rafael - Ao utilizar este return nao aparece o p:growl de sucesso e erro.
        return "lista_cliente?faces-redirect=true";
    }

    private void setEnderecoPessoa() {
        endereco = new PessoaEndereco();
        endereco.setPessoa(cliente);

        endereco.setTipoEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL));
        cliente.setListaEndereco(new ArrayList<PessoaEndereco>());
        cliente.getListaEndereco().add(endereco);
    }

    private void setTelefonePessoa() {
        telefone = new PessoaTelefone();
        telefone.setPessoa(cliente);
        celular = new PessoaTelefone();
        celular.setPessoa(cliente);

        telefone.setTipoTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL));
        celular.setTipoTelefone(new TipoTelefone(TipoTelefone.CELULAR));

        cliente.setListaTelefone(new ArrayList<PessoaTelefone>());
        cliente.getListaTelefone().add(telefone);
        cliente.getListaTelefone().add(celular);
    }

    private void setPessoaConta() {
        pessoaConta = new PessoaConta();
        pessoaConta.setPessoa(cliente);
        pessoaConta.setBanco(new Banco());
        pessoaConta.setTipoConta(new TipoConta());
        pessoaConta.setBolContaPrincipal(Boolean.TRUE);
        cliente.setListaPessoaConta(new ArrayList<PessoaConta>());
        cliente.getListaPessoaConta().add(pessoaConta);
    }

    private void setUsuario() {
        if (idSelecionado == null) {
            Usuario usuario = new Usuario();
            UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
            UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
            usuarioGrupo.setGrupo(new Grupo(Grupo.USER));

            usuario.setDescUsuario(cliente.getDescEmail());
            usuario.setDescSenha(SenhaUtil.gerarSenhaUsuario(cliente));
            usuario.getListaUsuarioPessoa().add(usuarioPessoa);
            usuario.getListaUsuarioGrupo().add(usuarioGrupo);

            usuarioPessoa.setPessoa(cliente);
            usuarioPessoa.setUsuario(usuario);

            usuarioGrupo.setUsuario(usuario);

            cliente.getListaUsuarioPessoa().add(usuarioPessoa);
        }
    }

    public String salvar() {
        Boolean enviarEmail = Boolean.FALSE;
        try {
            if (cliente.getId() == null || cliente.getId() == 0) {
                cliente.setDataCadastro(new Date());
                cliente.setDataAtualizacao(new Date());
                enviarEmail = Boolean.TRUE;
            } else {
                cliente.setDataAtualizacao(new Date());
            }

            // TODO: rafael - Substituir replaces por Validator
            cliente.setNumCpfCnpj(CpfCnpjUtil.getCpfCnpjLimpo(cliente.getNumCpfCnpj()));
            cliente.getListaEndereco().get(0).setNumCep(cliente.getListaEndereco().get(0).getNumCep().replace("-", ""));

            setUsuario();

            ejb.save(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.salvar.cliente", ex.getMessage());
            return "";
        } finally {
            // TODO: rafael - ajustar lógica de enviar email e salvar sincronamente
            if (enviarEmail) {
                try {
                    EmailUtil.enviaEmail(getEmailCadastro());
                } catch (Exception emailEx) {
                    emailEx.printStackTrace();
                    MensagemUtil.addMensagemErro("msg.cliente.salvo.erro.enviar.email", emailEx.getMessage());
                }
            }
        }
        MensagemUtil.addMensagemSucesso("msg.sucesso.salvar.cliente");
        return "lista_cliente?faces-redirect=true";
    }

    private Email getEmailCadastro() {
        Email mensagem = new Email();
        mensagem.setDestino(cliente.getDescEmail());
        mensagem.setMensagem("Usuario Cadastrado com Sucesso!!! \nUsuario: " + cliente.getDescEmail() + "\nSenha: " + SenhaUtil.getSenhaPadrao(cliente));
        mensagem.setTitulo("Cadastro com sucesso - Odontofight");
        return mensagem;
    }

    public String remover() {
        try {
            ejb.remove(cliente);
            System.out.println("Cliente removido");
        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemUtil.addMensagemErro("msg.erro.remover.cliente", ex.getMessage());
            return "";
        }
        return "lista_cliente?faces-redirect=true";
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

    private void initListaClienteIndicador() {
        listaClientesIndicadores = ejb.listarClientesIndicadores();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<UF> getListaUfs() {
        return listaUfs;
    }

    public List<Banco> getListaBancos() {
        return listaBancos;
    }

    public List<TipoConta> getListaTipoConta() {
        return listaTipoConta;
    }

    public List<PlanoAssinatura> getListaPlanoAssinatura() {
        return listaPlanoAssinatura;
    }

    public void atualizaListaProdutosPlanoAssinatura() {
        if (cliente.getPlanoAssinatura() != null) {
            listaProdutosPlanoAssinatura = ejb.listarProdutosKit(cliente.getPlanoAssinatura().getProduto().getId());
        }
    }

    public List<Produto> getListaProdutosPlanoAssinatura() {
        return listaProdutosPlanoAssinatura;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public PessoaEndereco getEndereco() {
        return endereco;
    }

    public PessoaTelefone getTelefone() {
        return telefone;
    }

    public String getDDDCelular() {
        for (PessoaTelefone tel : cliente.getListaTelefone()) {
            if (tel.getTipoTelefone().getId().equals(TipoTelefone.CELULAR)) {
                return tel.getDescTelefone().substring(0, 2);
            }
        }
        return null;
    }

    public PessoaTelefone getCelular() {
        return celular;
    }

    public PessoaConta getPessoaConta() {
        return pessoaConta;
    }

    public List<Cliente> getListaClientesIndicadores() {
        return listaClientesIndicadores;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<OrigemPagamento> getListaOrigemPagamento() {
        return listaOrigemPagamento;
    }

    public String getCheckoutCode() {
        return checkoutCode;
    }

    public void setCheckoutCode(String checkoutCode) {
        this.checkoutCode = checkoutCode;
    }
}
