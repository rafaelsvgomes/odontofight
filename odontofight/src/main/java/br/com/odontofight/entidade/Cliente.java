package br.com.odontofight.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.enums.TipoSexo;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idCliente", referencedColumnName = "idPessoa")
@NamedQueries({
        @NamedQuery(name = Cliente.OBTER_POR_DESC_USUARIO, query = "SELECT c.id FROM Cliente c WHERE c.descEmail = :descUsuario"),
        @NamedQuery(name = Cliente.OBTER_POR_NUM_CPF_CNPJ, query = "SELECT c.id FROM Cliente c WHERE c.numCpfCnpj = :numCpfCnpj"),
        @NamedQuery(name = Cliente.OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO, query = "SELECT c.id FROM Cliente c WHERE c.numCpfCnpj = :numCpfCnpj and c.id not in (:idSelecionado)"),
        @NamedQuery(name = Cliente.LISTAR_CLIENTES_INDICADORES, query = "SELECT new br.com.odontofight.entidade.Cliente(c.id, c.nomePessoa) FROM Cliente c"),
        @NamedQuery(name = Cliente.OBTER_CLIENTE_EDITAR, query = "SELECT new br.com.odontofight.entidade.Cliente(c.id, c.nomePessoa, c.descRazaoSocial, c.tipoPessoa, c.numCpfCnpj, c.tipoSexo, "
                + "c.dataNascimento, c.descEmail, c.dataCadastro, c.clienteSituacao.id, c.clienteSituacao.descClienteSituacao, c.clienteRede.id, c.clienteRede.clienteIndicador.id, "
                + "c.clienteRede.clienteIndicador.nomePessoa, c.planoAssinatura.id, c.planoAssinatura.nomePlanoAssinatura, c.planoAssinatura.valorAdesao, c.planoAssinatura.produto.id) "
                + "FROM Cliente c WHERE c.id = :idCliente"),
        @NamedQuery(name = Cliente.LISTAR_CLIENTES_SIMPLES, query = "SELECT new br.com.odontofight.entidade.Cliente(c.id, c.nomePessoa, c.tipoPessoa, c.numCpfCnpj, c.clienteSituacao.id, "
                + "c.clienteSituacao.descClienteSituacao) FROM Cliente c") })
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String OBTER_POR_DESC_USUARIO = "obterPorDescUsuario";
    public static final String OBTER_POR_NUM_CPF_CNPJ = "obterPorNumCpfCnpj";
    public static final String OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO = "obterPorNumCpfCnpjIgnoraSelecionado";
    public static final String LISTAR_CLIENTES_INDICADORES = "listarClientesIndicadores";
    public static final String OBTER_CLIENTE_EDITAR = "obterClienteEditar";
    public static final String LISTAR_CLIENTES_SIMPLES = "listarClientesSimples";

    /**
     * LISTAR_CLIENTES_INDICADORES
     */
    public Cliente(Long id, String nomePessoa) {
        super(id, nomePessoa);
    }

    /**
     * LISTAR_CLIENTES_SIMPLES
     */
    public Cliente(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj, Long idClienteSituacao, String descClienteSituacao) {
        super(id, nomePessoa, tipoPessoa, numCpfCnpj);
        this.clienteSituacao = new ClienteSituacao(idClienteSituacao, descClienteSituacao);

    }

    /**
     * OBTER_CLIENTE_EDITAR
     */
    public Cliente(Long id, String nomePessoa, String descRazaoSocial, TipoPessoa tipoPessoa, String numCpfCnpj, TipoSexo tipoSexo, Date dataNascimento, String descEmail,
            Date dataCadastro, Long idClienteSituacao, String descClienteSituacao, Long idClienteRede, Long idClienteIndicador, String nomeClienteIndicador,
            Short idPlanoAssinatura, String nomePlanoAssinatura, BigDecimal valorAdesao, Long idProdutoPlano) {
        super(id, nomePessoa, descRazaoSocial, tipoPessoa, numCpfCnpj, tipoSexo, dataNascimento, descEmail, dataCadastro);
        this.clienteSituacao = new ClienteSituacao(idClienteSituacao, descClienteSituacao);
        this.clienteRede = new ClienteRede(idClienteRede, new Cliente(id, nomePessoa), new Cliente(idClienteIndicador, nomePessoa));
        this.planoAssinatura = new PlanoAssinatura(idPlanoAssinatura, nomePlanoAssinatura, valorAdesao, idProdutoPlano);
    }

    public Cliente() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanoAssinatura")
    private PlanoAssinatura planoAssinatura;

    @ManyToOne
    @JoinColumn(name = "idClienteSituacao", nullable = true)
    private ClienteSituacao clienteSituacao;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClienteRede clienteRede;

    private Date dataAtualizacao;

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public ClienteSituacao getClienteSituacao() {
        return clienteSituacao;
    }

    public void setClienteSituacao(ClienteSituacao clienteSituacao) {
        this.clienteSituacao = clienteSituacao;
    }

    public ClienteRede getClienteRede() {
        return this.clienteRede;
    }

    public void setClienteRede(ClienteRede clienteRede) {
        this.clienteRede = clienteRede;
    }

    public PlanoAssinatura getPlanoAssinatura() {
        return planoAssinatura;
    }

    public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
