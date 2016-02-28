package br.com.odontofight.util;

import org.jboss.crypto.CryptoUtil;

public abstract class CriptografiaUtil {

    /**
     * Metodo responsavel por criptografar a String com algoritmo MD5
     * 
     * @param valor
     * @return String
     * 
     */
    public static String toMD5(String valor) {
        return CryptoUtil.createPasswordHash("MD5", "hex", "UTF-8", null, valor);
    }

}
