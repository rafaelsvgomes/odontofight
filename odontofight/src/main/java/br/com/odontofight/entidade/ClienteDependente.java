package br.com.odontofight.entidade;

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
 * Entity implementation class for Entity: ClienteDependente
 *
 */
@Entity
@Table(name = "ClienteDependente")
@SequenceGenerator(name = "seqclientedependente", sequenceName = "seqclientedependente", allocationSize = 1)
public class ClienteDependente extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public ClienteDependente() {
        super();
    }

    public ClienteDependente(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "idClienteDependente", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqclientedependente")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idParentesco", nullable = false)
    private Parentesco parentesco;

    @Column(name = "nomeDependente", nullable = false)
    private String nomeDependente;

    @Column(nullable = false)
    private Date dataNascimento;

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

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public String getNomeDependente() {
        return nomeDependente;
    }

    public void setNomeDependente(String nomeDependente) {
        this.nomeDependente = nomeDependente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
