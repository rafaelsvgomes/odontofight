package br.com.odontofight.enums;

public enum LadoArvore {

    E("ESQ"),
    D("DIR");

    private String descCodLado;

    private LadoArvore(String descCodLado) {
        this.descCodLado = descCodLado;
    }

    public String getDescCodLado() {
        return descCodLado;
    }

}
