package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOAENDERECO")
@SequenceGenerator(name = "SEQPESSOAENDERECO", sequenceName = "SEQPESSOAENDERECO", allocationSize = 1)
@NamedQuery(name = PessoaEndereco.LISTAR_POR_ID_PESSOA, query = "SELECT e FROM PessoaEndereco e WHERE e.pessoa.id = :idPessoa")
public class PessoaEndereco extends EntidadeGenerica {
    private static final long serialVersionUID = 3017364402878640980L;

    public static final String LISTAR_POR_ID_PESSOA = "listarEnderecoPorIdPessoa";

    @Id
    @Column(name = "IDPESSOAENDERECO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPESSOAENDERECO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDPESSOA", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "IDTIPOENDERECO", nullable = false)
    private TipoEndereco tipoEndereco;

    @Column(name = "dsEndereco")
    private String descEndereco;

    @Column(name = "dsNumero")
    private String descNumero;

    @Column(name = "dsComplemento")
    private String descComplemento;

    @Column(name = "dsBairro")
    private String descBairro;

    @Column(name = "dsCidade")
    private String descCidade;

    // @Cep
    private String numCep;

    @ManyToOne
    @JoinColumn(name = "CODUF", nullable = false)
    private UF uf;

    public PessoaEndereco() {
    }

    public PessoaEndereco(String numCep) {
        super();
        this.numCep = numCep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getDescEndereco() {
        return descEndereco;
    }

    public void setDescEndereco(String descEndereco) {
        this.descEndereco = descEndereco;
    }

    public String getDescNumero() {
        return descNumero;
    }

    public void setDescNumero(String descNumero) {
        this.descNumero = descNumero;
    }

    public String getDescComplemento() {
        return descComplemento;
    }

    public void setDescComplemento(String descComplemento) {
        this.descComplemento = descComplemento;
    }

    public String getDescBairro() {
        return descBairro;
    }

    public void setDescBairro(String descBairro) {
        this.descBairro = descBairro;
    }

    public String getDescCidade() {
        return descCidade;
    }

    public void setDescCidade(String descCidade) {
        this.descCidade = descCidade;
    }

    public String getNumCep() {
        return numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}
