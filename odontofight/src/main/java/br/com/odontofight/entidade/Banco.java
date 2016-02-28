package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Banco
 *
 */
@Entity
@Table(name = "Banco")
public class Banco extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;
	
	//Teste

    @Id
    @Column(name = "codBanco", unique = true, nullable = false)
    private Short codBanco;

    @Column(name = "dsBanco", nullable = false)
    private String descBanco;

    public String getDescBanco() {
        return descBanco;
    }

    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }

    public Short getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Short codBanco) {
        this.codBanco = codBanco;
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
        result = prime * result + ((codBanco == null) ? 0 : codBanco.hashCode());
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
        Banco other = (Banco) obj;
        if (codBanco == null) {
            if (other.getCodBanco() != null)
                return false;
        } else if (!codBanco.equals(other.getCodBanco()))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.odontofight.entidade.EntidadeGenerica#getId()
     */
    @Override
    public Number getId() {
        return null;
    }

}
