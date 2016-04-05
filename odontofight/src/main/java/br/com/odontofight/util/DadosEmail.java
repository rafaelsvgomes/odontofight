package br.com.odontofight.util;

public class DadosEmail {

    public DadosEmail() {
    }

    public DadosEmail(String destino, String titulo, String mensagem) {
        this.destino = destino;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    private String destino;
    private String titulo;
    private String mensagem;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}