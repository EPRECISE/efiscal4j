
package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(Integer.class)
public enum SpecialTaxRegime {
	// TODO: Não há descrição dos regimes no XML da NF-e. Falta descobrir quais são os tipos de regime e preencher nessa classe
	@XmlEnumValue("1") REGIME_1(1, ""),
	@XmlEnumValue("2") REGIME_2(2, ""),
	@XmlEnumValue("3") REGIME_3(3, ""),
	@XmlEnumValue("4") REGIME_4(4, ""),
	@XmlEnumValue("5") REGIME_5(5, ""),
	@XmlEnumValue("6") REGIME_6(6, "");

	private static final long serialVersionUID = 1L;

	private final int value;

	private final String description;

	private SpecialTaxRegime(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
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
