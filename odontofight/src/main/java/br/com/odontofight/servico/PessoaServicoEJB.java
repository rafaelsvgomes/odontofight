package br.com.odontofight.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaConta;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
@SuppressWarnings("unchecked")
public class PessoaServicoEJB extends GenericPersistencia<Pessoa, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Default constructor.
     */
    public PessoaServicoEJB() {
        super(Pessoa.class);
    }

    public List<Pessoa> listarPessoasIndicacaoSimples() {
        return em.createNamedQuery(PessoaIndicacao.LISTAR_PESSOAS_SIMPLES).getResultList();
    }

    public List<Pessoa> listarPessoasAcademiaSimples() {
        return em.createNamedQuery(PessoaAcademia.LISTAR_PESSOAS_SIMPLES).getResultList();
    }

    /**
     * @param id
     * @return Pessoa
     */
    public PessoaIndicacao obterPessoaIndicacao(Long id) {
        PessoaIndicacao pessoa = em.find(PessoaIndicacao.class, id);
        pessoa.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        pessoa.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        pessoa.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return pessoa;
    }

    public PessoaAcademia obterPessoaAcademia(Long id) {
        PessoaAcademia pessoa = em.find(PessoaAcademia.class, id);
        pessoa.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        pessoa.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return pessoa;
    }

    public Boolean emailJaUtilizado(String descUsuario) {
        try {
            em.createNamedQuery(Cliente.OBTER_POR_DESC_USUARIO).setParameter("descUsuario", descUsuario).getSingleResult();
            return Boolean.TRUE;
        } catch (NoResultException e) {
            return Boolean.FALSE;
        }
    }

    public Boolean cpfCnpjJaUtilizado(String numCpfCnpj, Long idClienteSelecionado) {
        try {
            if (idClienteSelecionado != null) {
                em.createNamedQuery(Cliente.OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO).setParameter("numCpfCnpj", numCpfCnpj).setParameter("idSelecionado", idClienteSelecionado)
                        .getSingleResult();
            } else {
                em.createNamedQuery(Cliente.OBTER_POR_NUM_CPF_CNPJ).setParameter("numCpfCnpj", numCpfCnpj).getSingleResult();
            }
            return Boolean.TRUE;
        } catch (NoResultException e) {
            return Boolean.FALSE;
        }
    }

}
