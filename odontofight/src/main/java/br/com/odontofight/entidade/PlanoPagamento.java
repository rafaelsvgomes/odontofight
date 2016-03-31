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
 * Entity implementation class for Entity: PlanoPagamento
 *
 */
@Entity
@Table(name = "PlanoPagamento")
@SequenceGenerator(name = "seqplanopagamento", sequenceName = "seqplanopagamento", allocationSize = 1)
public class PlanoPagamento extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public PlanoPagamento() {
        super();
    }

    @Id
    @Column(name = "idPlanoPagamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqplanopagamento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idClienteContrato", nullable = false)
    private ClienteContrato clienteContrato;

    @ManyToOne
    @JoinColumn(name = "idSituacaoParcela", nullable = false)
    private SituacaoParcela situacaoParcela;

    @Column(nullable = false)
    private Date dataVencimentoParcela;

    @Column(name = "vlParcela", nullable = false)
    private BigDecimal valorParcela;

    private Date dataPagamentoParcela;

    @Column(name = "vlPago")
    private BigDecimal valorPago;

    private Long numParcela;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteContrato getClienteContrato() {
        return clienteContrato;
    }

    public void setClienteContrato(ClienteContrato clienteContrato) {
        this.clienteContrato = clienteContrato;
    }

    public SituacaoParcela getSituacaoParcela() {
        return situacaoParcela;
    }

    public void setSituacaoParcela(SituacaoParcela situacaoParcela) {
        this.situacaoParcela = situacaoParcela;
    }

    public Date getDataVencimentoParcela() {
        return dataVencimentoParcela;
    }

    public void setDataVencimentoParcela(Date dataVencimentoParcela) {
        this.dataVencimentoParcela = dataVencimentoParcela;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getDataPagamentoParcela() {
        return dataPagamentoParcela;
    }

    public void setDataPagamentoParcela(Date dataPagamentoParcela) {
        this.dataPagamentoParcela = dataPagamentoParcela;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Long getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Long numParcela) {
        this.numParcela = numParcela;
    }

}
