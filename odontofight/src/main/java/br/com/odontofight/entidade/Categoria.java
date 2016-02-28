package br.com.odontofight.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria extends EntidadeGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcategoria")
	private Long id;

	@Column(name = "dscategoria")
	private String descCategoria;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idcategoriapai")
	private Categoria categoriaPai;

	//bi-directional many-to-one association to Categoria
	@OneToMany(mappedBy="categoriaPai")
	private List<Categoria> categorias;

	@OneToMany(mappedBy="categoria")
	private List<Produto> listaProduto;

	public Categoria() {
	}
	
	public void setIdcategoria(Long idcategoria) {
		this.id = idcategoria;
	}

	public String getDescCategoria() {
		return this.descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	public Categoria getCategoria() {
		return this.categoriaPai;
	}

	public void setCategoria(Categoria categoria) {
		this.categoriaPai = categoria;
	}

	public List<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria addCategoria(Categoria categoria) {
		getCategorias().add(categoria);
		categoria.setCategoria(this);

		return categoria;
	}

	public Categoria removeCategoria(Categoria categoria) {
		getCategorias().remove(categoria);
		categoria.setCategoria(null);

		return categoria;
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
		produto.setCategoria(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getListaProduto().remove(produto);
		produto.setCategoria(null);

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
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }
    
}