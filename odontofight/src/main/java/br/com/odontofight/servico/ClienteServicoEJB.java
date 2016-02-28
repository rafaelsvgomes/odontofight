package br.com.odontofight.servico;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.PessoaConta;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.PlanoAssinatura;
import br.com.odontofight.entidade.Produto;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
public class ClienteServicoEJB extends GenericPersistencia<Cliente, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Default constructor.
     */
    public ClienteServicoEJB() {
        super(Cliente.class);
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> listarClientesSimples() {
        return em.createNamedQuery(Cliente.LISTAR_CLIENTES_SIMPLES).getResultList();
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
        cliente.setListaPessoaConta(em.createNamedQuery(PessoaConta.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        return cliente;
    }

    @SuppressWarnings("unchecked")
    public Cliente obterCliente(Long idCliente) {
        Cliente cliente = find(idCliente);
        if (cliente != null) {
            cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", cliente.getId()).getResultList());
            cliente.getPlanoAssinatura().getProduto().getListaProdutoFilho().get(0);
            cliente.getPlanoAssinatura().getProduto().setListaProdutoFilho(cliente.getPlanoAssinatura().getProduto().getListaProdutoFilho());
        }
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

    /**
     * Método responsável por recuperar os produtos de um produto pai
     * 
     * @param idProdutoPai
     * @return List<Produto>
     * 
     */
    public List<Produto> listarProdutosKit(Long idProdutoPai) {
        Produto produtoPai = em.find(Produto.class, idProdutoPai);
        produtoPai.getListaProdutoFilho().get(0);
        return produtoPai.getListaProdutoFilho();
    }

    /**
     * @return Object
     * 
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> listarClientesIndicadores() {
        return em.createNamedQuery(Cliente.LISTAR_CLIENTES_INDICADORES).getResultList();
    }

    /**
     * @return List<PlanoAssinatura>
     * 
     */
    @SuppressWarnings("unchecked")
    public List<PlanoAssinatura> listarPlanoAssinatura() {
        return em.createNamedQuery(PlanoAssinatura.LISTAR_SIPLES).getResultList();
    }

}
