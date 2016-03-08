package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ModalidadeLuta
 *
 */
@Entity
@Table(name = "ModalidadeLuta")
public class ModalidadeLuta extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    public ModalidadeLuta() {
        super();
    }

    @Id
    @Column(name = "idModalidadeLuta", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsModalidadeLuta")
    private String descModalidadeLuta;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescModalidadeLuta() {
        return descModalidadeLuta;
    }

    public void setDescModalidadeLuta(String descModalidadeLuta) {
        this.descModalidadeLuta = descModalidadeLuta;
    }

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
        ModalidadeLuta other = (ModalidadeLuta) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
