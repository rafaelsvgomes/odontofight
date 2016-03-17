package br.com.odontofight.util;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public final class MensagemUtil {

    private static final String SUCESSO = "Sucesso";

    /**
     * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
     * 
     * @param titulo
     * @param detalhe
     */
    private static void addMessage(Severity severity, String titulo, String detalhe) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, detalhe));
    }

    public static void addMensagemSucesso(String detalhe) {
        addMessage(FacesMessage.SEVERITY_INFO, SUCESSO, getMessageFromMessagesLabels(detalhe));
    }

    public static void addMensagemErro(String titulo, String detalhe) {
        addMessage(FacesMessage.SEVERITY_ERROR, getMessageFromValidationMessages(titulo), detalhe);
    }

    public static void addMensagemAlerta(String titulo) {
        addMessage(FacesMessage.SEVERITY_WARN, getMessageFromValidationMessages(titulo), "Alerta");
    }

    public static void addMensagemInfo(String titulo) {
        addMessage(FacesMessage.SEVERITY_INFO, getMessageFromMessagesLabels(titulo), "Info");
    }

    /**
     * @param key
     * @return Recupera a mensagem do arquivo messages_labels <code>ResourceBundle</code>.
     */
    public static String getMessageFromMessagesLabels(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }

    /**
     * @param key
     * @return Recupera a mensagem do arquivo validationmessages <code>ResourceBundle</code>.
     */
    public static String getMessageFromValidationMessages(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("validationmessages", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }

    /**
     * @param key
     * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
     */
    public static String getPropriedades(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("propriedades", getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }

    /**
     * @param key
     * @return Recupera a mensagem do arquivo validationmessages <code>ResourceBundle</code>.
     */
    public static String getMessageFromValidationMessages(String key, String prefixo) {
        ResourceBundle bundle = ResourceBundle.getBundle("validationmessages", getCurrentInstance().getViewRoot().getLocale());
        return MessageFormat.format(bundle.getString(key), prefixo, "");
    }
}
