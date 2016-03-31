package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: PessoaConta
 *
 */
@Entity
@Table(name = "PessoaConta")
@SequenceGenerator(name = "seqpessoaconta", sequenceName = "seqpessoaconta", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = PessoaConta.LISTAR_POR_ID_PESSOA, query = "SELECT p FROM PessoaConta p WHERE p.pessoa.id = :idPessoa") })
public class PessoaConta extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_POR_ID_PESSOA = "listarPessoaContaPorIdPessoa";

    public PessoaConta() {
    }

    public PessoaConta(Cliente cliente) {
        this.pessoa = cliente;
    }

    /**
     * @param pessoa
     * @param banco
     * @param tipoConta
     * @param contaPrincipal
     */
    public PessoaConta(Pessoa pessoa, Banco banco, TipoConta tipoConta, Boolean contaPrincipal) {
        this.setPessoa(pessoa);
        this.setBanco(banco);
        this.setTipoConta(tipoConta);
        this.setBolContaPrincipal(contaPrincipal);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoaconta")
    @Column(name = "idPessoaConta", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idTipoConta", nullable = false)
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "codBanco", nullable = false)
    private Banco banco;

    @Column(nullable = false)
    private String numAgencia;

    @Column(nullable = false)
    private String numConta;

    @Column(nullable = false)
    private Boolean bolContaPrincipal;

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

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public Boolean getBolContaPrincipal() {
        return bolContaPrincipal;
    }

    public void setBolContaPrincipal(Boolean bolContaPrincipal) {
        this.bolContaPrincipal = bolContaPrincipal;
    }

}
