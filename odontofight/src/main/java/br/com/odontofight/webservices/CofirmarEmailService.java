package br.com.odontofight.webservices;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.odontofight.servico.ClienteServicoEJB;

/**
 * Servlet implementation class CofirmarEmailService
 */
@WebServlet("/CofirmarEmailService")
public class CofirmarEmailService extends HttpServlet {

    private static final long serialVersionUID = 6619113612514405394L;

    @EJB
    ClienteServicoEJB ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CofirmarEmailService() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Solicitação Recebida. GET");
        Long idCliente = Long.parseLong(request.getParameter("CodCliente"));
        String emailCliente = request.getParameter("Email");
        System.out.println("GET: IdCliente = " + idCliente + " Email = " + emailCliente);

        ejb.validarEmail(idCliente, emailCliente);

        response.sendRedirect(request.getContextPath() + "/emailvalidado.html");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Solicitação Recebida. POST");
        Long idCliente = Long.parseLong(request.getParameter("CodCliente"));
        String emailCliente = request.getParameter("Email");
        System.out.println("POST: IdCliente = " + idCliente + " Email = " + emailCliente);

        ejb.validarEmail(idCliente, emailCliente);

        response.sendRedirect(request.getContextPath() + "/emailvalidado.html");
    }

}
