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
 * Entity implementation class for Entity: ClienteContrato
 *
 */
@Entity
@Table(name = "ContratoRateio")
@SequenceGenerator(name = "seqcontratorateio", sequenceName = "seqcontratorateio", allocationSize = 1)
public class ContratoRateio extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public ContratoRateio() {
        super();
    }

    @Id
    @Column(name = "idContratoRateio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqcontratorateio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idClienteContrato", nullable = false)
    private ClienteContrato clienteContrato;

    @ManyToOne
    @JoinColumn(name = "idPessoaIndicacao", nullable = false)
    private PessoaIndicacao pessoaIndicacao;

    @Column(name = "vlPercentualRateio", nullable = false)
    private BigDecimal valorPercentualRateio;

    @Column(name = "vlPago", nullable = false)
    private BigDecimal valorPago;

    @Column(nullable = false)
    private Date dataRepasse;

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

    public PessoaIndicacao getPessoaIndicacao() {
        return pessoaIndicacao;
    }

    public void setPessoaIndicacao(PessoaIndicacao pessoaIndicacao) {
        this.pessoaIndicacao = pessoaIndicacao;
    }

    public BigDecimal getValorPercentualRateio() {
        return valorPercentualRateio;
    }

    public void setValorPercentualRateio(BigDecimal valorPercentualRateio) {
        this.valorPercentualRateio = valorPercentualRateio;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataRepasse() {
        return dataRepasse;
    }

    public void setDataRepasse(Date dataRepasse) {
        this.dataRepasse = dataRepasse;
    }

}
