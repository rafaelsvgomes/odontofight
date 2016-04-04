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
 * Entity implementation class for Entity: PessoaIndicacao
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idPessoaIndicacao", referencedColumnName = "idPessoa")
@NamedQueries({
        @NamedQuery(name = PessoaIndicacao.LISTAR_PESSOAS_INDICACAO, query = "SELECT new br.com.odontofight.entidade.PessoaIndicacao(p.id, p.numCpfCnpj, p.nomePessoa) FROM PessoaIndicacao p"),
        @NamedQuery(name = PessoaIndicacao.LISTAR_PESSOAS_SIMPLES, query = "SELECT new br.com.odontofight.entidade.PessoaIndicacao(p.id, p.nomePessoa, p.tipoPessoa, p.numCpfCnpj) "
                + " FROM PessoaIndicacao p WHERE p.id NOT IN (1)") })
public class PessoaIndicacao extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_PESSOAS_INDICACAO = "PessoaIndicacao.listarPessoasIndicacao";
    public static final String LISTAR_PESSOAS_SIMPLES = "PessoaIndicacao.listarPessoasSimples";
    public static final String LISTAR_PESSOA_SIMPLES_SQL = "SELECT p.idpessoa, p.nomepessoa, p.codtipopessoa, p.numcpfcnpj, s.idsituacao, s.dssituacao, pt.dstelefone, c.idcliente "
            + "FROM pessoaindicacao pi, PessoaTelefone pt, Situacao s, Pessoa p LEFT JOIN Cliente c ON p.idpessoa = c.idcliente "
            + "WHERE pi.idpessoaindicacao = p.idpessoa and p.idpessoa = pt.idpessoa AND pi.idsituacao = s.idsituacao AND pt.idtipotelefone = " + TipoTelefone.CELULAR;

    public PessoaIndicacao() {
    }

    public PessoaIndicacao(Long idPessoa) {
        this.id = idPessoa;
    }

    public PessoaIndicacao(Long id, String numCpfCnpj, String nomePessoa) {
        super(id, numCpfCnpj, nomePessoa);
    }

    @ManyToOne
    @JoinColumn(name = "idSituacao", nullable = true)
    private Situacao situacao;

    private Date dataAtualizacao;

    /**
     * LISTAR_PESSOAS_SIMPLES
     */
    public PessoaIndicacao(Long id, String nomePessoa, TipoPessoa tipoPessoa, String numCpfCnpj) {
        super(id, nomePessoa, tipoPessoa, numCpfCnpj);

    }

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
        PessoaIndicacao other = (PessoaIndicacao) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }
}
