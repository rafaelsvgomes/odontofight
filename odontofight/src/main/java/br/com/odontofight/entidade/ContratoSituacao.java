package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ContratoSituacao
 *
 */
@Entity
@Table(name = "ContratoSituacao")
public class ContratoSituacao extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    public static final long CADASTRADO = 1L;
    public static final long ATIVO = 2L;
    public static final long INATIVO = 3L;
    public static final long BLOQUEADO = 4L;

    public ContratoSituacao() {
    }

    @Id
    @Column(name = "idContratoSituacao", nullable = false, unique = true)
    private Long id;

    @Column(name = "dsContratoSituacao", nullable = false)
    private String descContratoSituacao;

    public ContratoSituacao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescContratoSituacao() {
        return descContratoSituacao;
    }

    public void setDescContratoSituacao(String descContratoSituacao) {
        this.descContratoSituacao = descContratoSituacao;
    }

}
