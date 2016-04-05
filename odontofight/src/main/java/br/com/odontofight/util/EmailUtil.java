package br.com.odontofight.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = MensagemUtil.getPropriedades("email.username");
    private static final String PASSWORD = MensagemUtil.getPropriedades("email.password");
    private static final String EMAILORIGEM = MensagemUtil.getPropriedades("email.remetente");

    public static Email conectaEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static HtmlEmail conectaEmailHtml() throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setSSL(true);
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static void enviaEmail(DadosEmail dados) throws EmailException {
        Email email = new SimpleEmail();
        email = conectaEmail();
        email.setSubject(dados.getTitulo());
        email.addTo(dados.getDestino());
        email.setMsg(dados.getMensagem());
        email.send();
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " +
        // mensagem.getDestino(), "Informacao"));
    }

    public static void enviaEmailHtml(DadosEmail dados) throws EmailException, IOException {
        HtmlEmail email = conectaEmailHtml();
        email.setSubject(dados.getTitulo());
        email.addTo(dados.getDestino());
        email.setHtmlMsg(dados.getMensagem());
        email.send();

        // configure uma mensagem alternativa caso o servidor não suporte HTML
        // email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
        // email.setSmtpPort(465);

    }

    public static void enviaEmailBoasVindas(DadosEmail dados, String arquivoHtml, String nomePessoa, Long codUsuario, Date dataInicioContrato, Date dataFimContrato)
            throws IOException, EmailException {
        // String conteudo = getConteudoEmailHtml(arquivoHtml, nomePessoa, codUsuario.toString(), DataUtil.toString(dataInicioContrato,
        // DataUtil.DATA_DIA_MES_ANO),
        // DataUtil.toString(dataFimContrato, DataUtil.DATA_DIA_MES_ANO));
        // enviaEmailHtml(dados, conteudo);
    }

    public static String getConteudoEmailHtml(String arquivoHtml, String usuario, Long codUsuario, Date dtInicioContrato, Date dtFimContrato) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileInputStream stream = new FileInputStream("/resources/" + arquivoHtml);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while (linha != null) {
            sb.append(linha);
            linha = br.readLine();
        }
        br.close();

        // System.out.println(sb.toString().replace("@usuario", usuario).replace("@codusuario", codUsuario).replace("@validadeinicio", dtInicioContrato)
        // .replace("@validadefim", dtFimContrato));

        return sb.toString().replace("@usuario", usuario).replace("@codusuario", codUsuario.toString())
                .replace("@validadeinicio", DataUtil.toString(dtInicioContrato, DataUtil.DATA_DIA_MES_ANO))
                .replace("@validadefim", DataUtil.toString(dtFimContrato, DataUtil.DATA_DIA_MES_ANO));
    }
}
