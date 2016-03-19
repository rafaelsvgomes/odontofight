package br.com.odontofight.servico;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.ClienteContrato;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;

/**
 * Session Bean implementation class ClienteEJB
 */

@Stateless
@LocalBean
@SuppressWarnings("unchecked")
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

    public List<Cliente> listarClientesSimples() {
        return em.createNamedQuery(Cliente.LISTAR_CLIENTES_SIMPLES).getResultList();
    }

    /**
     * @param id
     * @return Pessoa
     */
    public Cliente obterCliente(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
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
                em.createNamedQuery(Pessoa.OBTER_POR_NUM_CPF_CNPJ_IGNORA_SELECIONADO).setParameter("numCpfCnpj", numCpfCnpj).setParameter("idSelecionado", idClienteSelecionado)
                        .getSingleResult();
            } else {
                em.createNamedQuery(Pessoa.OBTER_POR_NUM_CPF_CNPJ).setParameter("numCpfCnpj", numCpfCnpj).getSingleResult();
            }
            return Boolean.TRUE;
        } catch (NoResultException e) {
            return Boolean.FALSE;
        }
    }

    /**
     * @return List<PessoaIndicacao>
     */
    public List<PessoaIndicacao> listarPessoasIndicacao() {
        return em.createNamedQuery(PessoaIndicacao.LISTAR_PESSOAS_INDICACAO).getResultList();
    }

    /**
     * @return List<PessoaAcademia>
     */
    public List<PessoaAcademia> listarPessoasAcademia() {
        return em.createNamedQuery(PessoaAcademia.LISTAR_PESSOAS_ACADEMIA).getResultList();
    }

    /**
     * @param cliente
     * @param listaTelefoneExcluir void
     * 
     */
    public void salvarCliente(Cliente cliente) {
        removerTelefone(cliente);
        removerEndereco(cliente);
        save(cliente);
    }

    public void removerTelefone(Cliente cliente) {
        List<PessoaTelefone> listaTelefoneRemover = new ArrayList<PessoaTelefone>();
        for (PessoaTelefone telefone : cliente.getListaTelefone()) {
            if (telefone.getDescTelefone().isEmpty()) {
                if (telefone.getId() != null && telefone.getId() != 0) {
                    em.remove(em.find(PessoaTelefone.class, telefone.getId()));
                }
                listaTelefoneRemover.add(telefone);
            }
        }
        cliente.getListaTelefone().removeAll(listaTelefoneRemover);
    }

    public void removerEndereco(Cliente cliente) {
        PessoaEndereco endereco = cliente.getListaEndereco().get(0);
        if (endereco.getNumCep().isEmpty()) {
            if (endereco.getId() != null && endereco.getId() != 0) {
                em.remove(em.find(PessoaEndereco.class, endereco.getId()));
            }
            cliente.removePessoaEndereco(endereco);
        }
    }

    public void salvarClienteContrato(ClienteContrato clienteContrato) {
        em.persist(clienteContrato);
    }

}
