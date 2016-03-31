package br.com.odontofight.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ClienteContrato
 *
 */
@Entity
@Table(name = "ClienteContrato")
@SequenceGenerator(name = "seqclientecontrato", sequenceName = "seqclientecontrato", allocationSize = 1)
public class ClienteContrato extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public ClienteContrato() {
        super();
    }

    public ClienteContrato(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "idClienteContrato")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqclientecontrato")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idContratoSituacao", nullable = false)
    private ContratoSituacao contratoSituacao;

    @ManyToOne
    @JoinColumn(name = "idPlanoAssinatura", nullable = false)
    private PlanoAssinatura planoAssinatura;

    @Column(name = "vlContrato", nullable = false)
    private BigDecimal valorContrato;

    @Column(name = "vlParcela", nullable = false)
    private BigDecimal valorParcela;

    @Column(name = "vlComissaoContrato", nullable = false)
    private BigDecimal valorComissaoContrato;

    @Column(nullable = false)
    private Integer qtdParcela;

    @Column(nullable = false)
    private Integer diaVencimentoParcela;

    @Column(nullable = false)
    private Date dataInicioContrato;

    @Column(nullable = false)
    private Date dataFimContrato;

    @OneToMany(mappedBy = "clienteContrato", cascade = CascadeType.ALL)
    private List<ContratoRateio> listaContratoRateio;

    @OneToMany(mappedBy = "clienteContrato", cascade = CascadeType.ALL)
    private List<PlanoPagamento> listaPlanoPagamentos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ContratoSituacao getContratoSituacao() {
        return contratoSituacao;
    }

    public void setContratoSituacao(ContratoSituacao contratoSituacao) {
        this.contratoSituacao = contratoSituacao;
    }

    public PlanoAssinatura getPlanoAssinatura() {
        return planoAssinatura;
    }

    public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Integer getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(Integer qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public Date getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(Date dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public Date getDataFimContrato() {
        return dataFimContrato;
    }

    public void setDataFimContrato(Date dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }

    public Integer getDiaVencimentoParcela() {
        return diaVencimentoParcela;
    }

    public void setDiaVencimentoParcela(Integer diaVencimentoParcela) {
        this.diaVencimentoParcela = diaVencimentoParcela;
    }

    public List<ContratoRateio> getListaContratoRateio() {
        if (listaContratoRateio == null) {
            listaContratoRateio = new ArrayList<ContratoRateio>();
        }
        return listaContratoRateio;
    }

    public void setListaContratoRateio(List<ContratoRateio> listaContratoRateio) {
        this.listaContratoRateio = listaContratoRateio;
    }

    public BigDecimal getValorComissaoContrato() {
        return valorComissaoContrato;
    }

    public void setValorComissaoContrato(BigDecimal valorComissaoContrato) {
        this.valorComissaoContrato = valorComissaoContrato;
    }

    public List<PlanoPagamento> getListaPlanoPagamentos() {
        if (listaPlanoPagamentos == null) {
            listaPlanoPagamentos = new ArrayList<PlanoPagamento>();
        }
        return listaPlanoPagamentos;
    }

    public void setListaPlanoPagamentos(List<PlanoPagamento> listaPlanoPagamentos) {
        this.listaPlanoPagamentos = listaPlanoPagamentos;
    }

}
