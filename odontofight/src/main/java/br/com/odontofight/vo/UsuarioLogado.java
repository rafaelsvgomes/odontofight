package br.com.odontofight.vo;

import java.io.Serializable;

public class UsuarioLogado implements Serializable {
    private static final long serialVersionUID = 1L;

    public UsuarioLogado() {
    }

    private Long idCliente;
    private String descUsuario;
    private Long idSituacao;
    private String codGrupo;
    private Long idUsuario;
    private String nomePessoa;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescUsuario() {
        return descUsuario;
    }

    public void setDescUsuario(String descUsuario) {
        this.descUsuario = descUsuario;
    }

    public Long getIdSituacao() {
        return idSituacao;
    }

    public void setIdSituacao(Long idClienteSituacao) {
        this.idSituacao = idClienteSituacao;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

}
