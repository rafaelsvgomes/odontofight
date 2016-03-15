package br.com.odontofight.servico;

import java.math.BigInteger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.odontofight.entidade.Usuario;
import br.com.odontofight.vo.UsuarioLogado;

/**
 * Session Bean implementation class UsuarioServicoEJB
 */
@Stateless
@LocalBean
public class UsuarioServicoEJB extends GenericPersistencia<Usuario, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Default constructor.
     */
    public UsuarioServicoEJB() {
        super(Usuario.class);
    }

    // TODO: rafalel - Se o usuario estiver em mais de um grupo vai retornar mais de um registro.
    public UsuarioLogado obterUsuario(String userName) {
        String sql = "SELECT p.idpessoa, u.dsusuario, ug.cdgrupo, u.idusuario, p.nomepessoa FROM usuario u, usuariopessoa up, usuariogrupo ug, pessoa p "
                + "WHERE p.idpessoa = up.idpessoa and u.idusuario = up.idusuario and u.idusuario = ug.idusuario and u.dsusuario = :userName";

        Object[] usuario = (Object[]) em.createNativeQuery(sql).setParameter("userName", userName).getSingleResult();

        return montarUsuarioLogado(usuario);
    }

    public UsuarioLogado obterUsuario(Long idUser) {
        String sql = "SELECT p.idpessoa, u.dsusuario, ug.cdgrupo, u.idusuario, p.nomepessoa FROM usuario u, usuariopessoa up, usuariogrupo ug, pessoa p "
                + "WHERE p.idpessoa = up.idpessoa and u.idusuario = up.idusuario and u.idusuario = ug.idusuario and u.idusuario = :idUser";

        Object[] usuario = (Object[]) em.createNativeQuery(sql).setParameter("idUser", idUser).getSingleResult();

        return montarUsuarioLogado(usuario);
    }

    private UsuarioLogado montarUsuarioLogado(Object[] usuario) {
        UsuarioLogado usuarioLogado = new UsuarioLogado();
        usuarioLogado.setIdPessoa(((BigInteger) usuario[0]).longValue());
        usuarioLogado.setDescUsuario((String) usuario[1]);
        usuarioLogado.setCodGrupo((String) usuario[2]);
        usuarioLogado.setIdUsuario(((BigInteger) usuario[3]).longValue());
        usuarioLogado.setNomePessoa((String) usuario[4]);

        return usuarioLogado;
    }
}
