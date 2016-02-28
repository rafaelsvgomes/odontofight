package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity
@Table(name = "GRUPO")
public class Grupo extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String GESTOR = "GESTOR";
    public static final String CLIENTE = "CLIENTE";

    public Grupo() {
        super();
    }

    public Grupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    @Id
    @Column(name = "cdGrupo", unique = true, nullable = false)
    private String codGrupo;

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setDescGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    @Override
    public Number getId() {
        return null;
    }
}
