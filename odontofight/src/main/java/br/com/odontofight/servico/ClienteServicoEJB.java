package br.com.odontofight.servico;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.odontofight.entidade.Cliente;
import br.com.odontofight.entidade.ClienteContrato;
import br.com.odontofight.entidade.ClienteDependente;
import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaAcademia;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaIndicacao;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.PlanoPagamento;
import br.com.odontofight.entidade.SituacaoParcela;
import br.com.odontofight.enums.TipoPessoa;
import br.com.odontofight.util.DataUtil;

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
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        // p.idpessoa, p.nomepessoa, p.codtipopessoa, p.numcpfcnpj, s.idsituacao, s.dssituacao, pt.dstelefone, c.dataatualizacao, cc.idclientecontrato
        List<Object[]> listaClientesObject = em.createNativeQuery(Cliente.LISTAR_CLIENTES_SIMPLES_COM_CONTRATO_SQL).getResultList();
        for (Object[] cliente : listaClientesObject) {
            Long idPessoa = ((BigInteger) cliente[0]).longValue();
            String nomePessoa = cliente[1].toString();
            TipoPessoa tipoPessoa = TipoPessoa.valueOf(cliente[2].toString());
            String numCpfCnpj = cliente[3].toString();
            Long idSituacao = ((BigInteger) cliente[4]).longValue();
            String descSituacao = cliente[5].toString();
            String numCelular = cliente[6].toString();
            Date dataAtualizacao = new Date(((Timestamp) cliente[7]).getTime());
            Long idClienteContrato = cliente[8] != null ? ((BigInteger) cliente[8]).longValue() : null;
            listaClientes.add(new Cliente(idPessoa, nomePessoa, tipoPessoa, numCpfCnpj, idSituacao, descSituacao, numCelular, dataAtualizacao, idClienteContrato));
        }

        return listaClientes;
    }

    /**
     * @param id
     * @return Pessoa
     */
    public Cliente obterCliente(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        cliente.setListaEndereco(em.createNamedQuery(PessoaEndereco.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        cliente.setListaTelefone(em.createNamedQuery(PessoaTelefone.LISTAR_POR_ID_PESSOA).setParameter("idPessoa", id).getResultList());
        if (!cliente.getListaClienteContrato().isEmpty() && !cliente.getListaClienteContrato().get(0).getListaContratoRateio().isEmpty()) {
            cliente.getListaClienteContrato().get(0).getListaContratoRateio().get(0);
        }
        if (!cliente.getListaClienteDependente().isEmpty()) {
            cliente.getListaClienteDependente().get(0);
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
        removerDependentes(cliente);

        if (cliente.getId() == null || cliente.getId() == 0) {
            cliente.setDataCadastro(new Date());
            cliente.setDataAtualizacao(new Date());
        } else {
            cliente.setDataAtualizacao(new Date());
        }

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

    public void removerDependentes(Cliente cliente) {
        em.createNamedQuery(ClienteDependente.DELETE_CLIENTE_DEPENDENTE).setParameter("idCliente", cliente.getId()).executeUpdate();
    }

    public void salvarClienteContrato(ClienteContrato clienteContrato) {
        setPlanoPagamentoContrato(clienteContrato);
        em.persist(clienteContrato);
    }

    private void setPlanoPagamentoContrato(ClienteContrato clienteContrato) {
        Date dataVencimentoAux = DataUtil.getDataVencimentoMesAtual(clienteContrato.getDiaVencimentoParcela());
        for (int i = 0; i < clienteContrato.getPlanoAssinatura().getQtdParcela(); i++) {
            PlanoPagamento planoPagamento = new PlanoPagamento();
            planoPagamento.setClienteContrato(clienteContrato);
            planoPagamento.setValorParcela(clienteContrato.getValorParcela());
            planoPagamento.setNumParcela(Long.valueOf(i + 1));

            if (i != 0) {
                planoPagamento.setSituacaoParcela(new SituacaoParcela(SituacaoParcela.ABERTO));
                planoPagamento.setDataVencimentoParcela(getPoximaDataVencimento(dataVencimentoAux));
                dataVencimentoAux = planoPagamento.getDataVencimentoParcela();
            } else {
                planoPagamento.setSituacaoParcela(new SituacaoParcela(SituacaoParcela.LIQUIDADO));
                planoPagamento.setValorPago(clienteContrato.getValorParcela());
                planoPagamento.setDataVencimentoParcela(new Date());
                planoPagamento.setDataPagamentoParcela(new Date());
            }
            clienteContrato.getListaPlanoPagamentos().add(planoPagamento);
        }
    }

    private Date getPoximaDataVencimento(Date dataUltimaParcela) {
        return DataUtil.incrementarData(dataUltimaParcela, Calendar.MONTH, 1);
    }

}
