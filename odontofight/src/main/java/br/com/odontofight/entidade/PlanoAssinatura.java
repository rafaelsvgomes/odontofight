package br.com.odontofight.entidade;

import java.math.BigDecimal;

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
 * Entity implementation class for Entity: TipoConta
 *
 */
@Entity
@Table(name = "PlanoAssinatura")
@SequenceGenerator(name = "seqplanoassinatura", sequenceName = "seqplanoassinatura", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = PlanoAssinatura.LISTAR_SIPLES, query = "SELECT new br.com.odontofight.entidade.PlanoAssinatura(p.id, p.nomePlanoAssinatura, p.valorAdesao, p.produto.id) FROM PlanoAssinatura p") })
public class PlanoAssinatura extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_SIPLES = "listarSimples";

    public PlanoAssinatura() {
    }

    public PlanoAssinatura(Short id, String nomePlano, BigDecimal valorAdesao, Long idProduto) {
        this.id = id;
        this.nomePlanoAssinatura = nomePlano;
        this.valorAdesao = valorAdesao;
        this.produto = new Produto();
        this.produto.setId(idProduto);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqplanoassinatura")
    @Column(name = "idPlanoAssinatura", unique = true, nullable = false)
    private Short id;

    @Column(nullable = false)
    private String nomePlanoAssinatura;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    @Column(name = "dsplanoassinatura")
    private String descPlanoAssinatura;

    @Column(name = "vladesao")
    private BigDecimal valorAdesao;

    @Column(name = "vlRenovacao")
    private BigDecimal valorRenovacao;

    @Column(name = "vlCompraMinima")
    private BigDecimal valorCompraMinima;

    private Boolean bolAtivo;

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

    public BigDecimal getValorAdesao() {
        return valorAdesao;
    }

    public void setValorAdesao(BigDecimal valorAdesao) {
        this.valorAdesao = valorAdesao;
    }

    public BigDecimal getValorRenovacao() {
        return valorRenovacao;
    }

    public void setValorRenovacao(BigDecimal valorRenovacao) {
        this.valorRenovacao = valorRenovacao;
    }

    public BigDecimal getValorCompraMinima() {
        return valorCompraMinima;
    }

    public void setValorCompraMinima(BigDecimal valorCompraMinima) {
        this.valorCompraMinima = valorCompraMinima;
    }

    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
