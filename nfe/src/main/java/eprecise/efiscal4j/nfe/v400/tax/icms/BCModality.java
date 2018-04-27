
package eprecise.efiscal4j.nfe.v400.tax.icms;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Modalidade de determinação da BC do ICMS:
 *
 * 0 - Margem Valor Agregado (%);
 *
 * 1 - Pauta (valor);
 *
 * 2 - Preço Tabelado Máximo (valor);
 *
 * 3 - Valor da Operação.
 *
 * @author Clécius J. Martinkoski
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum BCModality implements Serializable {

    @XmlEnumValue("0") MARGEM_VALOR_AGREGADO("0", "Margem Valor Agregado (%)"),
    @XmlEnumValue("1") PAUTA("1", "Pauta (valor)"),
    @XmlEnumValue("2") PRECO_TABELADO_MAX("2", "Preço Tabelado Máximo (valor)"),
    @XmlEnumValue("3") VALOR_OPERACAO("3", "Valor da Operação");

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private BCModality(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getBCModalityWithDescription() {
        return value + " - " + description;
    }

    public static BCModality findByCode(String code) {
        for (final BCModality bcModality : values()) {
            if (bcModality.getValue().equals(code)) {
                return bcModality;
            }
        }
        return null;
    }

}
