package br.com.odontofight.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: SituacaoCliente
 *
 */
@Entity
@Table(name = "ClienteSituacao")
public class ClienteSituacao extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    public static final long CADASTRADO = 1L;
    public static final long ATIVO = 2L;
    public static final long INATIVO = 3L;
    public static final long BLOQUEADO = 4L;

    @Id
    @Column(name = "idClienteSituacao", nullable = false, unique = true)
    private Long id;

    @Column(name = "dsClienteSituacao", nullable = false)
    private String descClienteSituacao;

    public ClienteSituacao(Long id) {
        this.id = id;
    }

    public ClienteSituacao() {
    }

    /**
     * @param idClienteSituacao
     * @param descClienteSituacao2
     */
    public ClienteSituacao(Long idClienteSituacao, String descClienteSituacao) {
        this.id = idClienteSituacao;
        this.descClienteSituacao = descClienteSituacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescClienteSituacao() {
        return descClienteSituacao;
    }

    public void setDescClienteSituacao(String descClienteSituacao) {
        this.descClienteSituacao = descClienteSituacao;
    }

}
