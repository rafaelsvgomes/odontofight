package br.com.odontofight.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = MensagemUtil.getPropriedades("email.username");
    private static final String PASSWORD = MensagemUtil.getPropriedades("email.password");
    private static final String EMAILORIGEM = MensagemUtil.getPropriedades("email.remetente");

    public static org.apache.commons.mail.Email conectaEmail() throws EmailException {
        org.apache.commons.mail.Email email = new SimpleEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static void enviaEmail(Email mensagem) throws EmailException {
        org.apache.commons.mail.Email email = new SimpleEmail();
        email = conectaEmail();
        email.setSubject(mensagem.getTitulo());
        email.setMsg(mensagem.getMensagem());
        email.addTo(mensagem.getDestino());
        String resposta = email.send();
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " +
        // mensagem.getDestino(), "Informacao"));
    }
}
