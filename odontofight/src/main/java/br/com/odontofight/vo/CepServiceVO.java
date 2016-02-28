package br.com.odontofight.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xmlcep")
public class CepServiceVO {

    @XStreamAlias("erro")
    private String erro;

    @XStreamAlias("cep")
    private String cep;

    @XStreamAlias("logradouro")
    private String logradouro;

    @XStreamAlias("complemento")
    private String complemento;

    @XStreamAlias("bairro")
    private String bairro;

    @XStreamAlias("localidade")
    private String localidade;

    @XStreamAlias("uf")
    private String uf;

    @XStreamAlias("ibge")
    private String ibge;

    @XStreamAlias("gia")
    private String gia;

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

}