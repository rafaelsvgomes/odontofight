package br.com.odontofight.enums;

public enum Operacao {

    I("Incluir"),
    E("Editar"),
    V("Visualizar");

    private String descTipoOperacao;

    private Operacao(String descTipoOperacao) {
        this.descTipoOperacao = descTipoOperacao;
    }

    /**
     * @return
     */
    public String getDescTipoOperacao() {
        return descTipoOperacao;
    }

}
