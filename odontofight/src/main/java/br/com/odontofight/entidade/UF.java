package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "UF")
@NamedQueries({ @NamedQuery(name = UF.LISTAR, query = "SELECT u FROM UF u") })
public class UF extends EntidadeGenerica {
    private static final long serialVersionUID = -8342220191530234596L;

    public static final String LISTAR = "UF.Listar";
    
    public UF(String codUf) {
        this.codUf = codUf;
    }
    
    public UF() {
    }

    @Id
    @Column(name = "CODUF", unique = true, nullable = false)
    private String codUf;

    @Column(name = "dsuf")
    private String descUf;

    public String getCodUf() {
        return codUf;
    }

    public void setCodUf(String cod) {
        this.codUf = cod;
    }

    public String getDescUf() {
        return descUf;
    }

    public void setDescUf(String descUf) {
        this.descUf = descUf;
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
        result = prime * result + ((codUf == null) ? 0 : codUf.hashCode());
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
        UF other = (UF) obj;
        if (codUf == null) {
            if (other.getCodUf() != null)
                return false;
        } else if (!codUf.equals(other.getCodUf()))
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
