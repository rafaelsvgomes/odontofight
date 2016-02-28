package br.com.odontofight.entidade.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.odontofight.util.CpfCnpjUtil;
import br.com.odontofight.util.MensagemUtil;

public class CpfCnpjValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object valorTela) throws ValidatorException {
        String cpfCnpj = CpfCnpjUtil.getCpfCnpjLimpo(String.valueOf(valorTela));

        if (cpfCnpj.length() == 11 && !validaCPF(cpfCnpj)) {
            throw new ValidatorException(getMessage("cpf.invalido", component));
        } else if (cpfCnpj.length() == 14 && !validaCNPJ(cpfCnpj)) {
            throw new ValidatorException(getMessage("cnpj.invalido", component));
        }
    }

    private FacesMessage getMessage(String mensagem, UIComponent component) {
        String label = (String) component.getAttributes().get("label");
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, MensagemUtil.getMessageFromValidationMessages(mensagem, label), null);
    }

    /**
     * Valida CPF do usuário. Não aceita CPF's padrões como 11111111111 ou 22222222222
     *
     * @param cpf String valor com 11 dígitos
     */
    private static boolean validaCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
            return false;

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    /**
     *
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
    private static boolean isCPFPadrao(String cpf) {
        if (cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {

            return true;
        }

        return false;
    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        if (soma % 11 == 0 | soma % 11 == 1)
            primDig = new Integer(0);
        else
            primDig = new Integer(11 - (soma % 11));

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1)
            segDig = new Integer(0);
        else
            segDig = new Integer(11 - (soma % 11));

        return primDig.toString() + segDig.toString();
    }

    /**
     * Valida CNPJ do usuário.
     *
     * @param cnpj String valor com 14 dígitos
     */
    public static boolean validaCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14)
            return false;

        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) { // CNPJ não possui somente números
            return false;
        }

        int soma = 0;
        String cnpj_calc = cnpj.substring(0, 12);

        char chr_cnpj[] = cnpj.toCharArray();
        for (int i = 0; i < 4; i++)
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));

        for (int i = 0; i < 8; i++)
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));

        int dig = 11 - soma % 11;
        cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();
        soma = 0;
        for (int i = 0; i < 5; i++)
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));

        for (int i = 0; i < 8; i++)
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));

        dig = 11 - soma % 11;
        cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();

        return cnpj.equals(cnpj_calc);
    }
}
