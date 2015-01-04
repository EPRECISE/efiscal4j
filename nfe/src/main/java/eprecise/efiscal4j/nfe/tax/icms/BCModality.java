
package eprecise.efiscal4j.nfe.tax.icms;

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

	@XmlEnumValue("0") MARGEM_VALOR_AGREGADO("Margem Valor Agregado (%)"),
	@XmlEnumValue("1") PAUTA("Pauta (valor)"),
	@XmlEnumValue("2") PRECO_TABELADO_MAX("Preço Tabelado Máximo (valor)"),
	@XmlEnumValue("3") VALOR_OPERACAO("Valor da Operação");

	private static final long serialVersionUID = 1L;

	private final String description;

	private BCModality(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}
