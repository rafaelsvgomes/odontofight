package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOTELEFONE")
public class TipoTelefone extends EntidadeGenerica {
    private static final long serialVersionUID = -4114897303647563942L;

    public static final short RESIDENCIAL = 1;
    public static final short CELULAR = 2;
    public static final short COMERCIAL = 3;

    public TipoTelefone() {
    }

    public TipoTelefone(short idTipoTelefone) {
        this.id = idTipoTelefone;
    }

    @Id
    @Column(name = "IDTIPOTELEFONE", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsTipoTelefone", nullable = false)
    private String descTipoTelefone;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescTipoTelefone() {
        return descTipoTelefone;
    }

    public void setDescTipoTelefone(String descTipoTelefone) {
        this.descTipoTelefone = descTipoTelefone;
    }

}
