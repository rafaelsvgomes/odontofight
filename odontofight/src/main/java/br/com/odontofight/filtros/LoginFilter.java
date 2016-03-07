package br.com.odontofight.filtros;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityContextAssociation;

import br.com.odontofight.servico.UsuarioServicoEJB;
import br.com.odontofight.util.UsuarioSessaoUtil;
import br.com.odontofight.vo.UsuarioLogado;

public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }

    @EJB
    private UsuarioServicoEJB usuarioServico;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextAssociation.getPrincipal() != null && getUsuarioSessao(servletRequest) == null) {
            String userName = SecurityContextAssociation.getPrincipal().getName();

            inserirUsuarioSessao(userName.trim(), servletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private Object getUsuarioSessao(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession().getAttribute(UsuarioSessaoUtil.USUARIO_LOGADO);
    }

    private void inserirUsuarioSessao(String userName, ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        UsuarioLogado usuario = usuarioServico.obterUsuario(userName);

        session.setAttribute(UsuarioSessaoUtil.USUARIO_LOGADO, usuario);
    }
}