package br.com.odontofight.exception;

public class CEPProxyException extends Exception {

    private static final long serialVersionUID = 1L;

    public CEPProxyException() {
        super();
    }

    public CEPProxyException(String message) {
        super(message);
    }

    public CEPProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CEPProxyException(Throwable cause) {
        super(cause);
    }

}
