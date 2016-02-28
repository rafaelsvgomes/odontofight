/**
 * Projeto:         Odontofight
 * Camada Projeto:  odontofight
 * Pacote:          br.com.odontofight.entidade
 * Arquivo:         OrigemPagamento.java
 * Data Criação:    25/02/2016
 */
package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrigemPagamento
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "OrigemPagamento")
public class OrigemPagamento extends EntidadeGenerica {

    private static final long serialVersionUID = 919101219244401727L;

    public static final short PAG_SEGURO = 2;

    @Id
    @Column(name = "idOrigemPagamento", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsOrigemPagamento", nullable = false)
    private String descOrigemPagamento;

    public Short getId() {
        return id;
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
        OrigemPagamento other = (OrigemPagamento) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescOrigemPagamento() {
        return descOrigemPagamento;
    }

    public void setDescOrigemPagamento(String descOrigemPagamento) {
        this.descOrigemPagamento = descOrigemPagamento;
    }
}
