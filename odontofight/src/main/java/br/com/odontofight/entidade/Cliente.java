package br.com.odontofight.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
        @NamedQuery(name = Cliente.OBTER_CLIENTE_EDITAR, query = "SELECT new br.com.odontofight.entidade.Cliente(c.id, c.nomePessoa, c.descRazaoSocial, c.tipoPessoa, c.numCpfCnpj, "
                + "c.tipoSexo, c.dataNascimento, c.descEmail, c.dataCadastro, c.situacao.id, c.situacao.descSituacao, c.pessoaIndicacao.id, c.pessoaIndicacao.nomePessoa) "
                + "FROM Cliente c WHERE c.id = :idCliente"),
        @NamedQuery(name = Cliente.LISTAR_CLIENTES_SIMPLES, query = "SELECT new br.com.odontofight.entidade.Cliente(c.id, c.nomePessoa, c.tipoPessoa, c.numCpfCnpj, c.situacao.id, "
                + "c.situacao.descSituacao) FROM Cliente c"),
        @NamedQuery(name = Cliente.LISTAR_CLIENTES_SIMPLES_COM_CONTRATO, query = "SELECT new br.com.odontofight.entidade.Cliente(c.id, c.nomePessoa, c.tipoPessoa, c.numCpfCnpj, c.situacao.id, "
                + "c.situacao.descSituacao, pt.descTelefone, c.dataAtualizacao, cc.id) FROM PessoaTelefone pt, Cliente c LEFT JOIN c.listaClienteContrato cc WHERE c.id = pt.pessoa.id and pt.tipoTelefone.id = 2") })
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String OBTER_POR_DESC_USUARIO = "Cliente.obterPorDescUsuario";
    public static final String OBTER_CLIENTE_EDITAR = "Cliente.obterClienteEditar";
    public static final String LISTAR_CLIENTES_SIMPLES = "Cliente.listarClientesSimples";
    public static final String LISTAR_CLIENTES_SIMPLES_COM_CONTRATO = "Cliente.listarClientesSimplesComContrato";
    public static final String LISTAR_CLIENTES_SIMPLES_COM_CONTRATO_SQL = "SELECT p.idpessoa, p.nomepessoa, p.codtipopessoa, p.numcpfcnpj, s.idsituacao, s.dssituacao, pt.dstelefone, c.dataatualizacao, cc.idclientecontrato "
            + "FROM Pessoa p, PessoaTelefone pt, Situacao s, Cliente c LEFT JOIN ClienteContrato cc ON c.idcliente = cc.idcliente "
            + "WHERE p.idpessoa = pt.idpessoa AND p.idpessoa = c.idcliente AND c.idsituacao = s.idsituacao AND pt.idtipotelefone = " + TipoTelefone.CELULAR;
    public static final String INSERIR_CLIENTE_CONVERSAO_QUERY_SQL = "INSERT INTO CLIENTE (idCliente, idSituacao, idPessoaIndicacao, dataAtualizacao) VALUES (?,?,?,?)";

    /**
     * LISTAR_CLIENTES_INDICADORES
     */
    public Cliente(Long id, String nomePessoa) {
        super(id, nomePessoa);
    }

    /**
     * LISTAR_CLIENTES_SIMPLES
     */
    public Cliente(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj, Long idSituacao, String descSituacao) {
        super(id, nomePessoa, tipoPessoa, numCpfCnpj);
        this.situacao = new Situacao(idSituacao, descSituacao);

    }

    /**
     * LISTAR_CLIENTES_SIMPLES_COM_CONTRATO
     */
    public Cliente(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj, Long idSituacao, String descSituacao, Long idClienteContrato) {
        super(id, nomePessoa, tipoPessoa, numCpfCnpj);
        this.situacao = new Situacao(idSituacao, descSituacao);
        if (idClienteContrato != null) {
            this.getListaClienteContrato().add(new ClienteContrato(idClienteContrato));
        }
    }

    public Cliente(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj, Long idSituacao, String descSituacao, String celular, Date dataAtualizacao,
            Long idClienteContrato) {
        super(id, nomePessoa, tipoPessoa, numCpfCnpj, celular);
        this.situacao = new Situacao(idSituacao, descSituacao);
        this.dataAtualizacao = dataAtualizacao;
        if (idClienteContrato != null) {
            this.getListaClienteContrato().add(new ClienteContrato(idClienteContrato));
        }
    }

    /**
     * OBTER_CLIENTE_EDITAR
     */
    public Cliente(Long id, String nomePessoa, String descRazaoSocial, TipoPessoa tipoPessoa, String numCpfCnpj, TipoSexo tipoSexo, Date dataNascimento, String descEmail,
            Date dataCadastro, Long idSituacao, String descSituacao, Long idPessoaIndicacao, String nomePessoaIndicao) {
        super(id, nomePessoa, descRazaoSocial, tipoPessoa, numCpfCnpj, tipoSexo, dataNascimento, descEmail, dataCadastro);
        this.situacao = new Situacao(idSituacao, descSituacao);
    }

    public Cliente() {
    }

    @ManyToOne
    @JoinColumn(name = "idSituacao", nullable = true)
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "idPessoaIndicacao", nullable = true)
    private PessoaIndicacao pessoaIndicacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idClienteLuta", nullable = true)
    private ClienteLuta clienteLuta;

    private Date dataAtualizacao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ClienteContrato> listaClienteContrato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ClienteDependente> listaClienteDependente;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

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

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public ClienteLuta getClienteLuta() {
        return clienteLuta;
    }

    public void setClienteLuta(ClienteLuta clienteLuta) {
        this.clienteLuta = clienteLuta;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public PessoaIndicacao getPessoaIndicacao() {
        return pessoaIndicacao;
    }

    public void setPessoaIndicacao(PessoaIndicacao pessoaIndicacao) {
        this.pessoaIndicacao = pessoaIndicacao;
    }

    public List<ClienteContrato> getListaClienteContrato() {
        if (listaClienteContrato == null) {
            listaClienteContrato = new ArrayList<ClienteContrato>();
        }
        return listaClienteContrato;
    }

    public void setListaClienteContrato(List<ClienteContrato> listaClienteContrato) {
        this.listaClienteContrato = listaClienteContrato;
    }

    public List<ClienteDependente> getListaClienteDependente() {
        if (listaClienteDependente == null) {
            listaClienteDependente = new ArrayList<ClienteDependente>();
        }
        return listaClienteDependente;
    }

    public void setListaClienteDependente(List<ClienteDependente> listaClienteDependente) {
        this.listaClienteDependente = listaClienteDependente;
    }

    public String getCodCliente() {
        String cod = id.toString();
        while (cod.length() < 5) {
            cod = "0" + cod;
        }
        return cod;
    }
}
