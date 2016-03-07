package br.com.odontofight.entidade;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity implementation class for Entity: PessoaIndicacao
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idPessoaIndicacao", referencedColumnName = "idPessoa")
@NamedQuery(name = PessoaIndicacao.LISTAR_PESSOAS_INDICACAO, query = "SELECT new br.com.odontofight.entidade.PessoaIndicacao(p.id, p.nomePessoa) FROM PessoaIndicacao p")
public class PessoaIndicacao extends Pessoa {
    private static final long serialVersionUID = 1L;

    public static final String LISTAR_PESSOAS_INDICACAO = "listarPessoasIndicacao";

    public PessoaIndicacao() {
    }

    public PessoaIndicacao(Long id, String nomePessoa) {
        super(id, nomePessoa);
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
