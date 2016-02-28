package br.com.odontofight.util;

import br.com.odontofight.entidade.Cliente;

public class SenhaUtil {

    public static String gerarSenhaUsuario(Cliente cliente) {
        return criptografarSenha(getSenhaPadrao(cliente));
    }

    public static String getSenhaPadrao(Cliente cliente) {
        return DataUtil.toString(cliente.getDataNascimento(), DataUtil.DATA_DIA_MES_ANO_SEM_BARRA) + getParteCpfCnpjSenha(cliente.getNumCpfCnpj());
    }

    private static String getParteCpfCnpjSenha(String cpfCnpj) {
        return cpfCnpj.substring(0, 3);
    }

    public static String criptografarSenha(String senha) {
        return CriptografiaUtil.toMD5(senha);
    }
}
