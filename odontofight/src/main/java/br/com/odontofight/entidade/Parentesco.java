package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Parentesco
 *
 */
@Entity
@Table(name = "Parentesco")
public class Parentesco extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idParentesco", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsParentesco", nullable = false)
    private String descParentesco;

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
        Parentesco other = (Parentesco) obj;
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

    public String getDescParentesco() {
        return descParentesco;
    }

    public void setDescParentesco(String descParentesco) {
        this.descParentesco = descParentesco;
    }

}
