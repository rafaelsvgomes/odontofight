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
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Telefone
 *
 */
@Entity
@Table(name = "PessoaTelefone")
@SequenceGenerator(name = "seqpessoatelefone", sequenceName = "seqpessoatelefone", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = PessoaTelefone.LISTAR_POR_ID_PESSOA, query = "SELECT t FROM PessoaTelefone t WHERE t.pessoa.id = :idPessoa") })
public class PessoaTelefone extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_POR_ID_PESSOA = "listarTelefonePorIdPessoa";

    public PessoaTelefone() {
    }

    public PessoaTelefone(TipoTelefone tipoTelefone, Pessoa pessoa) {
        this.tipoTelefone = tipoTelefone;
        this.pessoa = pessoa;
    }

    public PessoaTelefone(TipoTelefone tipoTelefone, String numero) {
        this.tipoTelefone = tipoTelefone;
        this.descTelefone = numero;
    }

    @Id
    @Column(name = "IDPessoaTelefone")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoatelefone")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTipoTelefone", nullable = false)
    private TipoTelefone tipoTelefone;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    @Column(name = "dsTelefone", nullable = false)
    private String descTelefone;

    @Transient
    private Boolean apagar;

    /**
     * Método responsável por obter apenas o ddd
     * 
     * @return String
     * 
     */
    public String getDDD() {
        if (this.getDescTelefone() != null) {
            return this.getDescTelefone().substring(0, 2);
        }
        return null;
    }

    /**
     * Método responsável por obter o número sem ddd
     * 
     * @return String
     * 
     */
    public String getNumero() {
        if (this.getDescTelefone() != null) {
            return this.getDescTelefone().substring(2);
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescTelefone() {
        return descTelefone;
    }

    public void setDescTelefone(String descTelefone) {
        this.descTelefone = descTelefone;
    }

    public Boolean getApagar() {
        return apagar;
    }

    public void setApagar(Boolean apagar) {
        this.apagar = apagar;
    }
}
