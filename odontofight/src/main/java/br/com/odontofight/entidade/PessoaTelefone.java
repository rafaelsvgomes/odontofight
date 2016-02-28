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
}
