package br.com.odontofight.entidade;

import java.io.Serializable;

public abstract class EntidadeGenerica implements Serializable {

    private static final long serialVersionUID = -9159448032768256460L;

    public abstract Number getId();
}
