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
 * Entity implementation class for Entity: ClienteLuta
 *
 */
@Entity
@Table(name = "ClienteLuta")
@SequenceGenerator(name = "seqclienteluta", sequenceName = "seqclienteluta", allocationSize = 1)
public class ClienteLuta extends EntidadeGenerica {
    private static final long serialVersionUID = 1L;

    public ClienteLuta() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqclienteluta")
    @Column(name = "idClienteLuta", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idAcademia", nullable = false)
    private Pessoa pessoaAcademia;

    @ManyToOne
    @JoinColumn(name = "idModalidadeLuta", nullable = false)
    private ModalidadeLuta modalidadeLuta;

    @Column(name = "dsGraduacao")
    private String descGraduação;

    private Date dataInicioAcademia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoaAcademia() {
        return pessoaAcademia;
    }

    public void setPessoaAcademia(Pessoa pessoaAcademia) {
        this.pessoaAcademia = pessoaAcademia;
    }

    public ModalidadeLuta getModalidadeLuta() {
        return modalidadeLuta;
    }

    public void setModalidadeLuta(ModalidadeLuta modalidadeLuta) {
        this.modalidadeLuta = modalidadeLuta;
    }

    public String getDescGraduação() {
        return descGraduação;
    }

    public void setDescGraduação(String descGraduação) {
        this.descGraduação = descGraduação;
    }

    public Date getDataInicioAcademia() {
        return dataInicioAcademia;
    }

    public void setDataInicioAcademia(Date dataInicioAcademia) {
        this.dataInicioAcademia = dataInicioAcademia;
    }

}
