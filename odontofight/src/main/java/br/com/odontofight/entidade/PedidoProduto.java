/**
 * Projeto:         Odontofight
 * Camada Projeto:  odontofight
 * Pacote:          br.com.odontofight.entidade
 * Arquivo:         PedidoProduto.java
 * Data Criação:    25/02/2016
 */
package br.com.odontofight.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PedidoProduto
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "PedidoProduto ")
@SequenceGenerator(name = "seqpedidoproduto", sequenceName = "seqpedidoproduto", allocationSize = 1)
public class PedidoProduto extends EntidadeGenerica {

    private static final long serialVersionUID = -8324873231584771846L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpedidoproduto")
    @Column(name = "idPedidoProduto", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedidoTipo", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idProdutoValor", nullable = false)
    private ProdutoValor produtoValor;

    @ManyToOne
    @JoinColumn(name = "idPedidoProdutoSituacao", nullable = false)
    private PedidoProdutoSituacao pedidoProdutoSituacao;

    @Column(nullable = false)
    private Long qtdProduto;

    @Column(name = "vlDesconto")
    private BigDecimal valorDesconto;

    private Long qtdProdutoEntregue;

    private Date dataEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoValor getProdutoValor() {
        return produtoValor;
    }

    public void setProdutoValor(ProdutoValor produtoValor) {
        this.produtoValor = produtoValor;
    }

    public PedidoProdutoSituacao getPedidoProdutoSituacao() {
        return pedidoProdutoSituacao;
    }

    public void setPedidoProdutoSituacao(PedidoProdutoSituacao pedidoProdutoSituacao) {
        this.pedidoProdutoSituacao = pedidoProdutoSituacao;
    }

    public Long getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Long qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Long getQtdProdutoEntregue() {
        return qtdProdutoEntregue;
    }

    public void setQtdProdutoEntregue(Long qtdProdutoEntregue) {
        this.qtdProdutoEntregue = qtdProdutoEntregue;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

}
