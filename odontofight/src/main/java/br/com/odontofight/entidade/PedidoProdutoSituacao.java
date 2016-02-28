/**
 * Projeto:         Odontofight
 * Camada Projeto:  odontofight
 * Pacote:          br.com.odontofight.entidade
 * Arquivo:         PedidoSituacao.java
 * Data Criação:    25/02/2016
 */
package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PedidoSituacao
 * 
 * @author Rafael.Silva
 */

@Entity
@Table(name = "PedidoProdutoSituacao")
public class PedidoProdutoSituacao extends EntidadeGenerica {

    private static final long serialVersionUID = -5464111484357685263L;

    @Id
    @Column(name = "idPedidoProdutoSituacao", nullable = false, unique = true)
    private Short id;

    @Column(name = "dsPedidoProdutoSituacao")
    private String descPedidoProdutoSituacao;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescPedidoProdutoSituacao() {
        return descPedidoProdutoSituacao;
    }

    public void setDescPedidoProdutoSituacao(String descPedidoProdutoSituacao) {
        this.descPedidoProdutoSituacao = descPedidoProdutoSituacao;
    }

}
