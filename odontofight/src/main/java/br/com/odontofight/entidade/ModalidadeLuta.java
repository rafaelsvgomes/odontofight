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

}
