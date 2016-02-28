/**
 * Projeto:         Odontofight
 * Camada Projeto:  odontofight
 * Pacote:          br.com.odontofight.entidade
 * Arquivo:         PedidoTipo.java
 * Data Criação:    25/02/2016
 */
package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PedidoTipo
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "PedidoTipo")
public class PedidoTipo extends EntidadeGenerica {

    private static final long serialVersionUID = -6751034595445330176L;

    @Id
    @Column(name = "idPedidoTipo", nullable = false, unique = true)
    private Short id;

    @Column(name = "dsPedidoTipo", nullable = false)
    private String descPedidoTipo;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescPedidoTipo() {
        return descPedidoTipo;
    }

    public void setDescPedidoTipo(String descPedidoTipo) {
        this.descPedidoTipo = descPedidoTipo;
    }

}
