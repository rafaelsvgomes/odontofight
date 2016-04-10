package br.com.odontofight.mb;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

import br.com.odontofight.entidade.Pessoa;
import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.PessoaTelefone;
import br.com.odontofight.entidade.TipoEndereco;
import br.com.odontofight.entidade.TipoTelefone;
import br.com.odontofight.entidade.UF;
import br.com.odontofight.exception.CEPProxyException;
import br.com.odontofight.util.MensagemUtil;
import br.com.odontofight.vo.CepServiceVO;
import br.com.odontofight.webservices.CepService;

abstract class GenericPessoaMB extends GenericMB {

    private static final long serialVersionUID = 1L;

    public static final String OUTCOME_HOME = "/layout/home.xhtml";

    private PessoaEndereco endereco;

    private PessoaTelefone telefone;

    private PessoaTelefone celular;

    private List<UF> listaUfs;

    public void iniciarEnderecoPessoa(Pessoa pessoa) {
        if (!pessoa.getListaEndereco().isEmpty()) {
            setEndereco(pessoa.getListaEndereco().get(0));
        } else {
            setEndereco(new PessoaEndereco(new TipoEndereco(TipoEndereco.RESIDENCIAL), pessoa));
            pessoa.addPessoaEndereco(getEndereco());
        }
    }

    public void iniciarTelefonePessoa(Pessoa pessoa) {
        if (pessoa.getTelefoneResidencial() == null) {
            iniciarTelefoneResidencial(pessoa);
        } else {
            telefone = pessoa.getTelefoneResidencial();
        }

        if (pessoa.getTelefoneCelular() == null) {
            iniciarTelefoneCelular(pessoa);
        } else {
            celular = pessoa.getTelefoneCelular();
        }
    }

    private void iniciarTelefoneResidencial(Pessoa pessoa) {
        telefone = new PessoaTelefone(new TipoTelefone(TipoTelefone.RESIDENCIAL), pessoa);
        pessoa.addPessoaTelefone(telefone);
    }

    private void iniciarTelefoneCelular(Pessoa pessoa) {
        celular = new PessoaTelefone(new TipoTelefone(TipoTelefone.CELULAR), pessoa);
        pessoa.addPessoaTelefone(celular);
    }

    /**
     * Metodo responsavel por buscar o cep no webService
     * 
     * @param e
     * @return String
     * 
     */
    public String buscarCep(ValueChangeEvent e) {
        String cep = e.getNewValue().toString();
        if (cep != null && !cep.isEmpty() && cep.trim().replaceAll("_", "").replace("-", "").length() == 8) {
            CepService cepService = new CepService();
            CepServiceVO cepServiceVO = null;

            cepServiceVO = buscarCEPWebService(cep, cepService, cepServiceVO);
            if (cepServiceVO != null) {
                if (cepServiceVO.getErro() != null && !cepServiceVO.getErro().isEmpty()) {
                    MensagemUtil.addMensagemInfo("webservice.cep.nao.encontrado");
                    limpaEndereco(cep);
                }
                populaEndereco(cep, cepServiceVO);
            }
        } else {
            limpaEndereco(cep);
        }
        return "lista_cliente?faces-redirect=true";
    }

    private CepServiceVO buscarCEPWebService(String cep, CepService cepService, CepServiceVO cepServiceVO) {
        try {
            cepServiceVO = cepService.buscarCepWebService(cep);
        } catch (CEPProxyException e) {
            MensagemUtil.addMensagemInfo("webservice.cep.erro");
            limpaEndereco(cep);
        }
        return cepServiceVO;
    }

    private void limpaEndereco(String cep) {
        this.endereco.setDescBairro("");
        this.endereco.setDescCidade("");
        this.endereco.setDescEndereco("");
        this.endereco.setDescNumero("");
        this.endereco.setDescComplemento("");
        this.endereco.setNumCep(cep);
        this.endereco.setUf(new UF());
    }

    /**
     * Metodo responsavel por popular os enderecos trago pelo web service
     * 
     * @param cep
     * @param cepServiceVO void
     * 
     */
    private void populaEndereco(String cep, CepServiceVO cepServiceVO) {
        this.endereco.setDescBairro(cepServiceVO.getBairro());
        this.endereco.setDescCidade(cepServiceVO.getLocalidade());
        this.endereco.setDescEndereco(cepServiceVO.getLogradouro());
        this.endereco.setNumCep(cep);
        this.endereco.setUf(getUF(cepServiceVO.getUf()));
    }

    private UF getUF(String codUf) {
        for (UF uf : listaUfs) {
            if (codUf.equals(uf.getCodUf())) {
                return uf;
            }
        }
        return null;
    }

    public PessoaEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(PessoaEndereco endereco) {
        this.endereco = endereco;
    }

    public List<UF> getListaUfs() {
        return listaUfs;
    }

    public void setListaUfs(List<UF> listaUfs) {
        this.listaUfs = listaUfs;
    }

    public PessoaTelefone getTelefone() {
        return telefone;
    }

    public void setTelefone(PessoaTelefone telefone) {
        this.telefone = telefone;
    }

    public PessoaTelefone getCelular() {
        return celular;
    }

    public void setCelular(PessoaTelefone celular) {
        this.celular = celular;
    }

}
