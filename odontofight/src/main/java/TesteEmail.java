import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class TesteEmail {
    private SimpleEmail getEmail() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo("rafaelsvgomes@gmail.com", "Guilherme"); // destinat�rio
        email.setFrom("rafaelsvgomes@gmail.com", "Eu"); // remetente
        email.setAuthentication("rafaelsvgomes", "Sic00b15973");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);

        return email;
    }

    public TesteEmail() throws EmailException, IOException {
        // enviaEmailSimples();
        // enviaEmailComAnexo();
        enviaEmailFormatoHtml();
    }

    /**
     * envia email simples(somente texto)
     * 
     * @throws EmailException
     */
    public void enviaEmailSimples() throws EmailException {
        SimpleEmail email = getEmail();
        email.setSubject("Teste -> Email simples"); // assunto do e-mail
        email.setMsg("Teste de Email utilizando commons-email"); // conteudo do e-mail

        email.send();
    }

    /**
     * envia email com arquivo anexo
     * 
     * @throws EmailException
     */
    public void enviaEmailComAnexo() throws EmailException {
        // cria o anexo 1.
        EmailAttachment anexo1 = new EmailAttachment();
        anexo1.setPath("teste.txt"); // caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)
        anexo1.setDisposition(EmailAttachment.ATTACHMENT);
        anexo1.setDescription("Exemplo de arquivo anexo");
        anexo1.setName("teste.txt");
        // cria o anexo 2.
        EmailAttachment anexo2 = new EmailAttachment();
        anexo2.setPath("teste2.txt"); // caminho do arquivo (RAIZ_PROJETO/teste/teste2.jsp)
        anexo2.setDisposition(EmailAttachment.ATTACHMENT);
        anexo2.setDescription("Exemplo de arquivo anexo");
        anexo2.setName("teste2.jsp");
        // configura o email
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo("destinatario@gmail.com", "Guilherme"); // destinat�rio
        email.setFrom("user@gmail.com", "Eu"); // remetente
        email.setSubject("Teste -> Email com anexos"); // assunto do e-mail
        email.setMsg("Teste de Email utilizando commons-email"); // conteudo do e-mail
        email.setAuthentication("user", "senha");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        // adiciona arquivo(s) anexo(s)
        email.attach(anexo1);
        email.attach(anexo2);
        // envia o email
        email.send();
    }

    /**
     * Envia email no formato HTML
     * 
     * @throws EmailException
     * @throws IOException
     */
    public void enviaEmailFormatoHtml() throws EmailException, IOException {
        HtmlEmail email = new HtmlEmail();
        // adiciona uma imagem ao corpo da mensagem e retorna seu id
        // URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        // String cid = email.embed(url, "Apache logo");
        // configura a mensagem para o formato HTML
        email.setHtmlMsg(getConteudoEmailHtml("Rafael Gomes", "123", "10/10/2000", "10/10/2001"));
        // configure uma mensagem alternativa caso o servidor n�o suporte HTML
        email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo("liraanderson@gmail.com", "Anderson"); // destinat�rio
        email.addTo("rafaelsvgomes@gmail.com", "Rafael");
        email.setFrom("rafaelsvgomes@gmail.com", "Eu"); // remetente
        email.setSubject("Teste -> Html Email"); // assunto do e-mail
        // email.setMsg(getEmailHtml()); // conteudo do e-mail
        email.setAuthentication("rafaelsvgomes", "Sic00b15973");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        // envia email
        email.send();
    }

    public String getConteudoEmailHtml(String usuario, String codUsuario, String dtInicioContrato, String dtFimContrato) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // InputStream input = classLoader.getResourceAsStream("test.png");

        StringBuilder sb = new StringBuilder();
        InputStream stream = classLoader.getResourceAsStream("email_boas_vindas.html");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while (linha != null) {
            sb.append(linha);
            linha = br.readLine();
        }
        br.close();

        System.out.println(sb.toString().replace("@usuario", usuario).replace("@codusuario", codUsuario).replace("@validadeinicio", dtInicioContrato)
                .replace("@validadefim", dtFimContrato));

        return sb.toString().replace("@usuario", usuario).replace("@codusuario", codUsuario).replace("@validadeinicio", dtInicioContrato).replace("@validadefim", dtFimContrato);
    }

    /**
     * @param args
     * @throws EmailException
     * @throws IOException
     */
    public static void main(String[] args) throws EmailException, IOException {
        new TesteEmail();
    }

}
