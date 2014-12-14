
package eprecise.efiscal4j.nfe.additionalinfo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Origem do processo referenciado
 * 
 * @author Felipe Bueno
 * 
 */

@XmlType
@XmlEnum(String.class)
public enum ProcessOrigin implements Serializable {

	@XmlEnumValue("0") SEFAZ("0", "SEFAZ"),
	@XmlEnumValue("1") JUSTICA_FEDERAL("1", "Justiça Federal"),
	@XmlEnumValue("2") JUSTICA_ESTADUAL("2", "Justiça Estadual"),
	@XmlEnumValue("3") SECEX_RFB("3", "Secex/RFB"),
	@XmlEnumValue("9") OUTROS("9", "Outros");

	private static final long serialVersionUID = 1L;

	private final String value;

	private final String description;

	private ProcessOrigin(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}

}
