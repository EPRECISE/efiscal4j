/**
 *
 */

package eprecise.efiscal4j.nfe.payment;

import java.io.Serializable;


/**
 * Forma de Pagamento
 *
 * @author Fernando Glizt
 *
 *         Para as notas onde a finalidade é uma NFe de Ajuste ou a Devolução de uma NFe (campo finNFe = 3 ou 4), a forma de pagamento deve ser preenchida com o valor 90=Sem Pagamento
 *
 */
public enum PaymentMethod implements Serializable {

                                                   DINHEIRO("01", "Dinheiro"),
                                                   CHEQUE("02", "Cheque"),
                                                   CARTAO_CREDITO("03", "Cartão de Crédito"),
                                                   CARTAO_DEBITO("04", "Cartão de Débito"),
                                                   CREDITO_LOJA("05", "Crédito Loja"),
                                                   VALE_ALIMENTACAO("10", "Vale Alimentação"),
                                                   VALE_REFEICAO("11", "Vale Refeição"),
                                                   VALE_PRESENTE("12", "Vale Presente"),
                                                   VALE_COMBUSTIVEL("13", "Vale Combustível"),
                                                   DUPLICATA_MERCANTIL("14", "Duplicata Mercantil"),
                                                   BOLETO_BANCARIO("15", "Boleto Bancário"),
                                                   DEPOSITO_BANCARIO("16", "Depósito Bancário"),
                                                   PIX("17", "Pagamento Instantâneo (PIX)"),
                                                   CARTEIRA_DIGITAL("18", "Transferência bancária, Carteira Digital"),
                                                   CASHBACK("19", "Programa de fidelidade, Cashback, Crédito Virtual"),
                                                   SEM_PAGAMENTO("90", "Sem Pagamento"),
                                                   OUTROS("99", "Outros");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private PaymentMethod(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static PaymentMethod findByCode(String code) {
        for (final PaymentMethod paymentMethod : values()) {
            if (paymentMethod.getValue().equals(code)) {
                return paymentMethod;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
