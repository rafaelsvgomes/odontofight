package br.com.odontofight.mb;

import java.math.BigDecimal;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.MetaDataItemKey;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

public class CreateCheckout {
    /**
     * Class with a main method to illustrate the usage of the domain class Checkout //
     */
    public static void main(String[] args) {

        try {

            Boolean onlyCheckoutCode = false;

            String checkoutURL = new CreateCheckout().getCheckout().register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);

            System.out.println(checkoutURL);

        } catch (PagSeguroServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getCheckoutCode() throws PagSeguroServiceException {
        return getCheckoutUrl().replace("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=", "");
    }

    public String getCheckoutUrl() throws PagSeguroServiceException {
        Boolean onlyCheckoutCode = false;
        String checkoutURL = getCheckout().register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);
        System.out.println(checkoutURL);

        return checkoutURL;
    }

    private Checkout getCheckout() {
        Checkout checkout = new Checkout();

        checkout.addItem("0123", "Notebook 123", Integer.valueOf(1), new BigDecimal("101.00"), new Long(0), null);

        checkout.setSender("Comprador teste", "c18795623261578832189@sandbox.pagseguro.com.br", "61", "92336666", DocumentType.CPF, "000.000.001-91");

        checkout.setCurrency(Currency.BRL);

        checkout.setReference("REF1234");

        // checkout.setNotificationURL("http://www.meusite.com.br/notification");

        // checkout.setRedirectURL("http://www.meusite.com.br/redir");

        // Another way to set checkout parameters
        checkout.addParameter("senderBornDate", "07/05/1981");

        checkout.addIndexedParameter("itemId", "0003", 3);

        checkout.addIndexedParameter("itemDescription", "Notebook Preto", 3);

        checkout.addIndexedParameter("itemQuantity", "1", 3);

        checkout.addIndexedParameter("itemAmount", "200.00", 3);

        checkout.addMetaDataItem(MetaDataItemKey.PASSENGER_CPF, "15600944276", 1);

        checkout.addMetaDataItem(MetaDataItemKey.GAME_NAME, "DOTA");

        checkout.addMetaDataItem(MetaDataItemKey.PASSENGER_PASSPORT, "23456", 1);

        return checkout;
    }

    public CreateCheckout() {

    }
}