/**
 *
 */

package eprecise.efiscal4j.nfe.v400.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Forma de Pagamento
 *
 * @author Felipe Bueno
 *
 * Para as notas onde a finalidade é uma NFe de Ajuste ou a Devolução de uma NFe 
 * (campo finNFe = 3 ou 4), a forma de pagamento deve ser preenchida com o valor 90=Sem Pagamento
 *
 */
@XmlType
@XmlEnum(String.class)
public enum PaymentMethod implements Serializable {

    @XmlEnumValue("01") DINHEIRO("01", "Dinheiro"),
    @XmlEnumValue("02") CHEQUE("02", "Cheque"),
    @XmlEnumValue("03") CARTAO_CREDITO("03", "Cartão de Crédito"),
    @XmlEnumValue("04") CARTAO_DEBITO("04", "Cartão de Débito"),
    @XmlEnumValue("05") CREDITO_LOJA("05", "Cartão da Loja (Private Label), Crediário Digital, Outros Crediários"),

    @XmlEnumValue("10") VALE_ALIMENTACAO("10", "Vale Alimentação"),
    @XmlEnumValue("11") VALE_REFEICAO("11", "Vale Refeição"),
    @XmlEnumValue("12") VALE_PRESENTE("12", "Vale Presente"),
    @XmlEnumValue("13") VALE_COMBUSTIVEL("13", "Vale Combustível"),
    @XmlEnumValue("14") DUPLICATA_MERCANTIL("14", "Duplicata Mercantil"),
    @XmlEnumValue("15") BOLETO_BANCARIO("15", "Boleto Bancário"),
    @XmlEnumValue("16") DEPOSITO_BANCARIO("16", "Depósito Bancário"),
    @XmlEnumValue("17") PIX("17", "Pagamento Instantâneo (PIX) - Dinâmico"),
    @XmlEnumValue("18") CARTEIRA_DIGITAL("18", "Transferência bancária, Carteira Digital"),
    @XmlEnumValue("19") CASHBACK("19", "Programa de fidelidade, Cashback, Crédito Virtual"),
    @XmlEnumValue("20") PIX_ESTATICO("20", "Pagamento Instantâneo (PIX) - Estático"),
    @XmlEnumValue("21") NOVO_CREDITO_LOJA("21", "Crédito em Loja"),
    @XmlEnumValue("22") PAGAMENTO_ELETRONICO_NAO_INFORMADO("22", "Pagamento Eletrônico não Informado - falha de hardware do sistema emissor"),

    @XmlEnumValue("90") SEM_PAGAMENTO("90", "Sem Pagamento"),
    
    @XmlEnumValue("99") OUTROS("99", "Outros");

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
