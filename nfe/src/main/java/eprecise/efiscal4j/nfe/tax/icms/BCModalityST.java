
package eprecise.efiscal4j.nfe.tax.icms;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Modalidade de determinação da BC do ICMS ST:
 * 
 * 0 – Preço tabelado ou máximo sugerido;
 * 
 * 1 - Lista Negativa (valor);
 * 
 * 2 - Lista Positiva (valor);
 * 
 * 3 - Lista Neutra (valor);
 * 
 * 4 - Margem Valor Agregado (%);
 * 
 * 5 - Pauta (valor);
 * 
 * @author Clécius J. Martinkoski
 * 
 */
@XmlType
@XmlEnum(Integer.class)
public enum BCModalityST implements Serializable {
	@XmlEnumValue("0") PRECO_TABELADO_OU_MAX_SUGERIDO("Preço tabelado ou máximo sugerido"),
	@XmlEnumValue("1") LISTA_NEGATIVA("Lista Negativa"),
	@XmlEnumValue("2") LISTA_POSITIVA("Lista Positiva"),
	@XmlEnumValue("3") LISTA_NEUTRA("Lista Neutra "),
	@XmlEnumValue("4") MARGEM_VALOR_AGREGADO("Margem Valor Agregado"),
	@XmlEnumValue("5") PAUTA("Pauta (valor)");

	private static final long serialVersionUID = 1L;

	private final String description;

	private BCModalityST(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
