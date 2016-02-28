/**
 * Projeto:         Odontofight
 * Camada Projeto:  odontofight
 * Pacote:          br.com.odontofight.entidade
 * Arquivo:         Pedido.java
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
 * Pedido
 * 
 * @author Rafael.Silva
 */
@Entity
@Table(name = "Pedido")
@SequenceGenerator(name = "seqpedido", sequenceName = "seqpedido", allocationSize = 1)
public class Pedido extends EntidadeGenerica {

    private static final long serialVersionUID = 8886064910592207310L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpedido")
    @Column(name = "idPedido", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedidoTipo", nullable = false)
    private PedidoTipo pedidoTipo;

    @ManyToOne
    @JoinColumn(name = "idPedidoSituacao", nullable = false)
    private PedidoSituacao pedidoSituacao;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idOrigemPagamento", nullable = false)
    private OrigemPagamento origemPagamento;

    @Column(nullable = false)
    private Date dataPedido;

    @Column(name = "vlTotalBruto", nullable = false)
    private BigDecimal valorTotalBruto;

    @Column(name = "vlTotalLiquido", nullable = false)
    private BigDecimal valorTotalLiquido;

    @Column(name = "vlFrete")
    private BigDecimal valorFrete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoTipo getPedidoTipo() {
        return pedidoTipo;
    }

    public void setPedidoTipo(PedidoTipo pedidoTipo) {
        this.pedidoTipo = pedidoTipo;
    }

    public PedidoSituacao getPedidoSituacao() {
        return pedidoSituacao;
    }

    public void setPedidoSituacao(PedidoSituacao pedidoSituacao) {
        this.pedidoSituacao = pedidoSituacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrigemPagamento getOrigemPagamento() {
        return origemPagamento;
    }

    public void setOrigemPagamento(OrigemPagamento origemPagamento) {
        this.origemPagamento = origemPagamento;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotalBruto() {
        return valorTotalBruto;
    }

    public void setValorTotalBruto(BigDecimal valorTotalBruto) {
        this.valorTotalBruto = valorTotalBruto;
    }

    public BigDecimal getValorTotalLiquido() {
        return valorTotalLiquido;
    }

    public void setValorTotalLiquido(BigDecimal valorTotalLiquido) {
        this.valorTotalLiquido = valorTotalLiquido;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

}
