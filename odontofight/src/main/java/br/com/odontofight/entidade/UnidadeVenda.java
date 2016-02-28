package br.com.odontofight.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the unidadevenda database table.
 * 
 */
@Entity
@NamedQuery(name="UnidadeVenda.findAll", query="SELECT u FROM UnidadeVenda u")
public class UnidadeVenda extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idunidadevenda")
	private Long id;

	@Column(name = "dsunidadevenda")
	private String descUnidadeVenda;

	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="unidadeVenda")
	private List<Produto> listaProduto;

	public UnidadeVenda() {
	}

	public void setIdUnidadeVenda(Long idUnidadeVenda) {
		this.id = idUnidadeVenda;
	}

	public String getDescUnidadeVenda() {
		return this.descUnidadeVenda;
	}

	public void setDescUnidadeVenda(String descUnidadeVenda) {
		this.descUnidadeVenda = descUnidadeVenda;
	}

	public List<Produto> getListaProduto() {
		return this.listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public Produto addProduto(Produto produto) {
		if(getListaProduto() == null) {
			setListaProduto(new ArrayList<Produto>());
		}
		getListaProduto().add(produto);
		produto.setUnidadeVenda(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getListaProduto().remove(produto);
		produto.setUnidadeVenda(null);

		return produto;
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
        UnidadeVenda other = (UnidadeVenda) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}