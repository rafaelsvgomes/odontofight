package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: TipoConta
 *
 */
@Entity
@Table(name = "TipoConta")
public class TipoConta extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idTipoConta", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsTipoConta", nullable = false)
    private String descTipoConta;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescTipoConta() {
        return descTipoConta;
    }

    public void setDescTipoConta(String descTipoConta) {
        this.descTipoConta = descTipoConta;
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
        TipoConta other = (TipoConta) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
