package br.com.odontofight.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.odontofight.entidade.ClienteContrato;

public class EmailUtil {

    private static final String HOSTNAME = MensagemUtil.getPropriedades("email.smtp");
    private static final Integer PORTA = Integer.valueOf(MensagemUtil.getPropriedades("email.porta.smtp"));
    private static final String USERNAME = MensagemUtil.getPropriedades("email.username");
    private static final String PASSWORD = MensagemUtil.getPropriedades("email.password");
    private static final String EMAILORIGEM = MensagemUtil.getPropriedades("email.remetente");

    public static Email conectaEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(PORTA);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setTLS(true);
        email.setSSL(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static HtmlEmail conectaEmailHtml() throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(PORTA);
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

    public static void enviaEmailHtml(DadosEmail dados) throws EmailException {
        try {
            HtmlEmail email = conectaEmailHtml();
            email.setSubject(dados.getTitulo());
            email.addTo(dados.getDestino());
            email.setHtmlMsg(dados.getMensagem());
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailException("Erro ao enviar email");
        }

        // configure uma mensagem alternativa caso o servidor não suporte HTML
        // email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
        // email.setSmtpPort(465);

    }

    public static void enviaEmailBoasVindas(ClienteContrato clienteContrato) throws EmailException {
        enviaEmailHtml(getDadosEmailBoasVindas(clienteContrato));
    }

    private static DadosEmail getDadosEmailBoasVindas(ClienteContrato clienteContrato) throws EmailException {
        DadosEmail dados = new DadosEmail();
        dados.setDestino(clienteContrato.getCliente().getDescEmail());
        dados.setTitulo("Seja Bem Vindo a ODONTOFight");
        dados.setMensagem(EmailUtil.getConteudoEmailHtml(MensagemUtil.getPropriedades("template.email.boasvindas"), clienteContrato.getCliente().getNomePessoa(), clienteContrato
                .getCliente().getCodCliente(), clienteContrato.getDataInicioContrato(), clienteContrato.getDataFimContrato()));

        return dados;
    }

    public static String getConteudoEmailHtml(String arquivoHtml, String usuario, String codCliente, Date dtInicioContrato, Date dtFimContrato) throws EmailException {
        try {
            StringBuilder sb = new StringBuilder();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classLoader.getResourceAsStream(arquivoHtml);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            while (linha != null) {
                sb.append(linha);
                linha = br.readLine();
            }
            br.close();

            return sb.toString().replace("@usuario", usuario).replace("@codusuario", codCliente.toString())
                    .replace("@validadeinicio", DataUtil.toString(dtInicioContrato, DataUtil.DATA_DIA_MES_ANO))
                    .replace("@validadefim", DataUtil.toString(dtFimContrato, DataUtil.DATA_DIA_MES_ANO));
        } catch (Exception e) {
            throw new EmailException("Erro ao montar conteudo do email de boas vindas");
        }
    }
}
