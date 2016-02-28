package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.odontofight.enums.LadoArvore;

/**
 * Entity implementation class for Entity: ClienteRede
 *
 */
@Entity
@Table(name = "CLIENTEREDE")
@SequenceGenerator(name = "seqpessoarede", sequenceName = "seqpessoarede", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ClienteRede.LISTAR_POR_ID_CLIENTE, query = "SELECT cr FROM ClienteRede cr WHERE cr.cliente.id = :idCliente") })
public class ClienteRede extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_POR_ID_CLIENTE = "listarClienteRedeIdCliente";

    public ClienteRede() {
    }

    public ClienteRede(Long idClienteRede, Cliente cliente, Cliente clienteIndicador) {
        this.id = idClienteRede;
        this.cliente = cliente;
        this.clienteIndicador = clienteIndicador;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpessoarede")
    @Column(name = "idClienteRede", unique = true, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", nullable = false, updatable = false)
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClientePai", nullable = true)
    private Cliente clientePai;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClienteIndicacao", nullable = true)
    private Cliente clienteIndicador;

    @Column(name = "codNivel")
    private Long codNivel;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "codLado")
    private LadoArvore ladoArvore;

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

    public Cliente getClientePai() {
        return clientePai;
    }

    public void setClientePai(Cliente clientePai) {
        this.clientePai = clientePai;
    }

    public Cliente getClienteIndicador() {
        return clienteIndicador;
    }

    public void setClienteIndicador(Cliente clienteIndicador) {
        this.clienteIndicador = clienteIndicador;
    }

    public Long getCodNivel() {
        return codNivel;
    }

    public void setCodNivel(Long codNivel) {
        this.codNivel = codNivel;
    }

    public LadoArvore getLadoArvore() {
        return ladoArvore;
    }

    public void setLadoArvore(LadoArvore ladoArvore) {
        this.ladoArvore = ladoArvore;
    }
}
