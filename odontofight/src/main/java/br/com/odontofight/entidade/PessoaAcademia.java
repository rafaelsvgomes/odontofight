package br.com.odontofight.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.odontofight.enums.TipoPessoa;

/**
 * Entity implementation class for Entity: PessoaAcademia
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idPessoaAcademia", referencedColumnName = "idPessoa")
@NamedQueries({
        @NamedQuery(name = PessoaAcademia.LISTAR_PESSOAS_ACADEMIA, query = "SELECT new br.com.odontofight.entidade.PessoaAcademia(p.id, p.nomePessoa) FROM PessoaAcademia p"),
        @NamedQuery(name = PessoaAcademia.LISTAR_PESSOAS_SIMPLES, query = "SELECT new br.com.odontofight.entidade.PessoaAcademia(p.id, p.nomePessoa, p.tipoPessoa, p.numCpfCnpj) "
                + " FROM PessoaAcademia p") })
public class PessoaAcademia extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_PESSOAS_ACADEMIA = "PessoaAcademia.listarPessoasAcademia";
    public static final String LISTAR_PESSOAS_SIMPLES = "PessoaAcademia.listarPessoasSimples";
    public static final String LISTAR_PESSOA_SIMPLES_SQL = "SELECT p.idpessoa, p.nomepessoa, p.codtipopessoa, p.numcpfcnpj, p.dsemail, s.idsituacao, s.dssituacao, pt.dstelefone, c.idcliente "
            + "FROM PessoaAcademia pa, PessoaTelefone pt, Situacao s, Pessoa p LEFT JOIN Cliente c ON p.idpessoa = c.idcliente "
            + "WHERE pa.idpessoaacademia = p.idpessoa and p.idpessoa = pt.idpessoa AND pa.idsituacao = s.idsituacao AND pt.idtipotelefone = " + TipoTelefone.CELULAR;

    public PessoaAcademia() {
    }

    public PessoaAcademia(Long id, String nomePessoa) {
        super(id, nomePessoa);
    }

    /**
     * LISTAR_PESSOAS_SIMPLES
     */
    public PessoaAcademia(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj) {
        super(id, nomePessoa, tipoPessoa, numCpfCnpj);

    }

    @ManyToOne
    @JoinColumn(name = "idSituacao", nullable = true)
    private Situacao situacao;

    private Date dataAtualizacao;

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PessoaAcademia other = (PessoaAcademia) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
