package br.com.odontofight.servico;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaConta;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.Situacao;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.exception.NegocioException;

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
        List<PessoaIndicacao> listaClientes = new ArrayList<PessoaIndicacao>();
        // p.idpessoa, p.nomepessoa, p.codtipopessoa, p.numcpfcnpj, s.idsituacao, s.dssituacao, pt.dstelefone, c.idcliente 
        List<Object[]> listaClientesObject = em.createNativeQuery(PessoaIndicacao.LISTAR_PESSOA_SIMPLES_SQL).getResultList();
        for (Object[] cliente : listaClientesObject) {
            Long idPessoa = ((BigInteger) cliente[0]).longValue();
            String nomePessoa = cliente[1].toString();
            TipoPessoa tipoPessoa = TipoPessoa.valueOf(cliente[2].toString());
            String numCpfCnpj = cliente[3].toString();
            Long idSituacao = ((BigInteger) cliente[4]).longValue();
            String descSituacao = cliente[5].toString();
            String numCelular = cliente[6].toString();
            Long idCliente = cliente[8] != null ? ((BigInteger) cliente[8]).longValue() : null;
            listaClientes.add(new PessoaIndicacao(id, nomePessoa, tipoPessoa, numCpfCnpj) Cliente(idPessoa, nomePessoa, tipoPessoa, numCpfCnpj, idSituacao, descSituacao, numCelular, dataAtualizacao, idClienteContrato));
        }

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

    public void salvarPessoa(Pessoa pessoa) {
        removerTelefone(pessoa);
        removerEndereco(pessoa);

        if (pessoa instanceof PessoaIndicacao) {
            atualizarPessoaIndicacao((PessoaIndicacao) pessoa);
        } else {
            atualizarPessoaAcademia((PessoaAcademia) pessoa);
        }

        save(pessoa);
    }

    private void atualizarPessoaIndicacao(PessoaIndicacao pessoa) {
        if (pessoa.getId() == null || pessoa.getId() == 0) {
            pessoa.setDataCadastro(new Date());
            pessoa.setDataAtualizacao(new Date());
        } else {
            pessoa.setDataAtualizacao(new Date());
        }
    }

    private void atualizarPessoaAcademia(PessoaAcademia pessoa) {
        if (pessoa.getId() == null || pessoa.getId() == 0) {
            pessoa.setDataCadastro(new Date());
            pessoa.setDataAtualizacao(new Date());
        } else {
            pessoa.setDataAtualizacao(new Date());
        }
    }

    public void removerTelefone(Pessoa cliente) {
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

    public void removerEndereco(Pessoa pessoa) {
        PessoaEndereco endereco = pessoa.getListaEndereco().get(0);
        if (endereco.getNumCep().isEmpty()) {
            if (endereco.getId() != null && endereco.getId() != 0) {
                em.remove(em.find(PessoaEndereco.class, endereco.getId()));
            }
            pessoa.removePessoaEndereco(endereco);
        }
    }

    /**
     * Método responsável por converter uma pessoa para cliente.
     * 
     * @param idPessoa void
     * @param idPessoaLogado
     * @throws NegocioException
     * 
     */
    public void salvaCliente(Long idPessoa, Long idPessoaLogado) throws NegocioException {
        Cliente cliente = em.find(Cliente.class, idPessoa);
        if (cliente != null) {
            throw new NegocioException("Pessoa já é um cliente.");
        }

        Query query = em.createNativeQuery(Cliente.INSERIR_CLIENTE_CONVERSAO_QUERY_SQL);
        query.setParameter(1, idPessoa);
        query.setParameter(2, Situacao.ATIVO);
        query.setParameter(3, idPessoaLogado);
        query.setParameter(4, new Date());
        query.executeUpdate();
    }
}
