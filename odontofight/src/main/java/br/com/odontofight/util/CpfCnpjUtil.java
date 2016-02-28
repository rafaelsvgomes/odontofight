package br.com.odontofight.util;

public class CpfCnpjUtil {
    public static String getCpfCnpjLimpo(String cpf) {
        return cpf.replace("-", "").replace(".", "").replace("/", "");
    }
}
