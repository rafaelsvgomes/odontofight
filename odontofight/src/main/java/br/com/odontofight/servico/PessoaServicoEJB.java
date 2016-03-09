package br.com.odontofight.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
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

    @SuppressWarnings("unchecked")
    public List<Pessoa> listarPessoasSimples() {
        return em.createNamedQuery(PessoaIndicacao.LISTAR_PESSOAS_SIMPLES).getResultList();
    }

    /**
     * @param id
     * @return Pessoa
     */
    @SuppressWarnings("unchecked")
    public Cliente obterPessoa(Long id) {
        Cliente cliente = (Cliente) em.createNamedQuery(Cliente.OBTER_CLIENTE_EDITAR).setParameter("idCliente", id).getSingleResult();

        cliente.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return cliente;
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
