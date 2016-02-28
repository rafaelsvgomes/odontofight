package br.com.odontofight.enums;

public enum TipoPessoa {

    J("PJ"),
    F("PF");

    private String descTipoPessoa;

    private TipoPessoa(String descTipoPessoa) {
        this.descTipoPessoa = descTipoPessoa;
    }

    public String getDescTipoPessoa() {
        return descTipoPessoa;
    }

}
