package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: SituacaoParcela
 *
 */
@Entity
@Table(name = "SituacaoParcela")
public class SituacaoParcela extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    public static final long ABERTO = 1L;
    public static final long CANCELADO = 2L;
    public static final long BAIXADO = 3L;
    public static final long LIQUIDADO = 4L;

    public SituacaoParcela() {
    }

    @Id
    @Column(name = "idSituacaoParcela", nullable = false, unique = true)
    private Long id;

    @Column(name = "dsSituacaoParcela", nullable = false)
    private String descSituacaoParcela;

    public SituacaoParcela(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescSituacaoParcela() {
        return descSituacaoParcela;
    }

    public void setDescSituacaoParcela(String descSituacaoParcela) {
        this.descSituacaoParcela = descSituacaoParcela;
    }

}
