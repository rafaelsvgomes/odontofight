package br.com.odontofight.mb;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

import br.com.odontofight.entidade.PessoaEndereco;
import br.com.odontofight.entidade.UF;
import br.com.odontofight.exception.CEPProxyException;
import br.com.odontofight.util.MensagemUtil;
import br.com.odontofight.vo.CepServiceVO;
import br.com.odontofight.webservices.CepService;

abstract class GenericPessoaMB extends GenericMB {

    private static final long serialVersionUID = 1L;

    private PessoaEndereco endereco;

    private List<UF> listaUfs;

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

}
