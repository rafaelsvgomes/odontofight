package br.com.odontofight.entidade;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the valorproduto database table.
 * 
 */
@Entity
@Table(name = "PRODUTOVALOR")
@NamedQuery(name = ProdutoValor.LISTAR_POR_ID_PRODUTO, query = "SELECT pv FROM ProdutoValor pv WHERE pv.produto.id = :idProduto")
public class ProdutoValor extends EntidadeGenerica{
	private static final long serialVersionUID = 1L;

	public static final String LISTAR_POR_ID_PRODUTO = "listarValorProdutoPorIdProduto";
	
	@Id
	@Column(name = "idProdutoValor")
	private Long id;

	@Column(name = "dataatualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAtualizacao;

	@Column(name = "vlcusto")
	private BigDecimal valorCusto;

	@Column(name = "vldesconto")
	private BigDecimal valorDesconto;

	@Column(name = "vlproduto")
	private BigDecimal valorProduto;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="idproduto")
	private Produto produto;

	public ProdutoValor() {
	}
	
	public void setIdValorProduto(Long idValorProduto) {
		this.id = idValorProduto;
	}

	public Calendar getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public BigDecimal getValorCusto() {
		return this.valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getValorDesconto() {
		return this.valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorProduto() {
		return this.valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

    /* (non-Javadoc)
     * @see br.com.odontofight.entidade.EntidadeGenerica#getId()
     */
    @Override
    public Number getId() {
        return this.id;
    }

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
        ProdutoValor other = (ProdutoValor) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }
}