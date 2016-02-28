package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOENDERECO")
public class TipoEndereco extends EntidadeGenerica {
    private static final long serialVersionUID = 5493317362445950406L;

    public static final short RESIDENCIAL = 1;
    public static final short COMERCIAL = 2;

    public TipoEndereco() {
    }

    public TipoEndereco(short idTipoEndereco) {
        this.id = idTipoEndereco;
    }

    @Id
    @Column(name = "IDTIPOENDERECO", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsTipoEndereco", nullable = false)
    private String descTipoEndereco;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescTipoEndereco() {
        return descTipoEndereco;
    }

    public void setDescTipoEndereco(String descTipoEndereco) {
        this.descTipoEndereco = descTipoEndereco;
    }
}
