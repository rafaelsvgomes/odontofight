package br.com.odontofight.entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@PrimaryKeyJoinColumn(name = "idFornecedor", referencedColumnName = "idPessoa")
public class Fornecedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    // @OneToMany(mappedBy = "fornecedor")
    // private List<Produto> listraProduto;

    public Fornecedor() {
        super();
    }

    // public List<Produto> getListaProduto() {
    // return this.listraProduto;
    // }
    //
    // public void setListaProduto(List<Produto> listaProduto) {
    // this.listraProduto = listaProduto;
    // }
    //
    // public Produto addProduto(Produto produto) {
    // if (getListaProduto() == null) {
    // setListaProduto(new ArrayList<Produto>());
    // }
    // getListaProduto().add(produto);
    // produto.setFornecedor(this);
    //
    // return produto;
    // }
    //
    // public Produto removeProduto(Produto produto) {
    // getListaProduto().remove(produto);
    // produto.setFornecedor(null);
    //
    // return produto;
    // }

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
        Fornecedor other = (Fornecedor) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}