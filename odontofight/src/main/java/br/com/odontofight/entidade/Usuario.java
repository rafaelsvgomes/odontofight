package br.com.odontofight.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "sequsuario", sequenceName = "sequsuario", allocationSize = 1)
public class Usuario extends EntidadeGenerica {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequsuario")
    @Column(name = "idusuario", unique = true, nullable = false)
    private Long id;

    @Column(name = "dsusuario", nullable = false)
    private String descUsuario;

    @Column(name = "dssenha", nullable = false)
    private String descSenha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<UsuarioGrupo> listaUsuarioGrupo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<UsuarioPessoa> listaUsuarioPessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescUsuario() {
        return descUsuario;
    }

    public void setDescUsuario(String descUsuario) {
        this.descUsuario = descUsuario;
    }

    public String getDescSenha() {
        return descSenha;
    }

    public void setDescSenha(String descSenha) {
        this.descSenha = descSenha;
    }

    public List<UsuarioGrupo> getListaUsuarioGrupo() {
        if (listaUsuarioGrupo == null) {
            listaUsuarioGrupo = new ArrayList<UsuarioGrupo>();
        }
        return listaUsuarioGrupo;
    }

    public void setListaUsuarioGrupo(List<UsuarioGrupo> listaUsuarioGrupo) {
        this.listaUsuarioGrupo = listaUsuarioGrupo;
    }

    public List<UsuarioPessoa> getListaUsuarioPessoa() {
        if (listaUsuarioPessoa == null) {
            listaUsuarioPessoa = new ArrayList<UsuarioPessoa>();
        }
        return listaUsuarioPessoa;
    }

    public void setListaUsuarioPessoa(List<UsuarioPessoa> listaUsuarioPessoa) {
        this.listaUsuarioPessoa = listaUsuarioPessoa;
    }

}
