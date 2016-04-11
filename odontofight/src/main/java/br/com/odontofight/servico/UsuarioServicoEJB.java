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

    // TODO: rafalel - Se o usuario estiver em mais de um grupo vai retornar mais de um registro e vai dar problema na montagem do objeto.
    public UsuarioLogado obterUsuario(String userName) {
        Object[] usuario = (Object[]) em.createNativeQuery(Usuario.OBTER_POR_USERNAME).setParameter("userName", userName).getSingleResult();

        return montarUsuarioLogado(usuario);
    }

    public UsuarioLogado obterUsuario(Long idUser) {
        Object[] usuario = (Object[]) em.createNativeQuery(Usuario.OBTER_POR_ID_USUARIO).setParameter("idUser", idUser).getSingleResult();

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
