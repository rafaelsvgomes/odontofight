package br.com.odontofight.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: TipoConta
 *
 */
@Entity
@Table(name = "PlanoAssinatura")
@SequenceGenerator(name = "seqplanoassinatura", sequenceName = "seqplanoassinatura", allocationSize = 1)
// @NamedQuery(name = PlanoAssinatura.LISTAR_SIPLES, query =
// "SELECT new br.com.odontofight.entidade.PlanoAssinatura(p.id, p.nomePlanoAssinatura, p.valorAdesao, p.produto.id) FROM PlanoAssinatura p")
public class PlanoAssinatura extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_SIPLES = "listarSimples";

    public PlanoAssinatura() {
    }

    public PlanoAssinatura(Short id, String nomePlano) {
        this.id = id;
        this.nomePlanoAssinatura = nomePlano;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqplanoassinatura")
    @Column(name = "idPlanoAssinatura", unique = true, nullable = false)
    private Short id;

    @Column(nullable = false)
    private String nomePlanoAssinatura;

    @Column(name = "dsplanoassinatura")
    private String descPlanoAssinatura;

    @Column(name = "vlPlanoAssinatura")
    private BigDecimal valorPlanoAssinatura;

    private Boolean bolAtivo;

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
        PlanoAssinatura other = (PlanoAssinatura) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNomePlanoAssinatura() {
        return nomePlanoAssinatura;
    }

    public void setNomePlanoAssinatura(String nomePlanoAssinatura) {
        this.nomePlanoAssinatura = nomePlanoAssinatura;
    }

    public String getDescPlanoAssinatura() {
        return descPlanoAssinatura;
    }

    public void setDescPlanoAssinatura(String descPlanoAssinatura) {
        this.descPlanoAssinatura = descPlanoAssinatura;
    }

    public BigDecimal getValorPlanoAssinatura() {
        return valorPlanoAssinatura;
    }

    public void setValorPlanoAssinatura(BigDecimal valorPlanoAssinatura) {
        this.valorPlanoAssinatura = valorPlanoAssinatura;
    }

    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

}
