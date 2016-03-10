package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: SituacaoCliente
 *
 */
@Entity
@Table(name = "Situacao")
public class Situacao extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    public static final long CADASTRADO = 1L;
    public static final long ATIVO = 2L;
    public static final long INATIVO = 3L;
    public static final long BLOQUEADO = 4L;

    @Id
    @Column(name = "idSituacao", nullable = false, unique = true)
    private Long id;

    @Column(name = "dsSituacao", nullable = false)
    private String descSituacao;

    public Situacao(Long id) {
        this.id = id;
    }

    public Situacao() {
    }

    /**
     * @param idClienteSituacao
     * @param descSituacao2
     */
    public Situacao(Long idClienteSituacao, String descSituacao) {
        this.id = idClienteSituacao;
        this.descSituacao = descSituacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescSituacao() {
        return descSituacao;
    }

    public void setDescSituacao(String descSituacao) {
        this.descSituacao = descSituacao;
    }

}
