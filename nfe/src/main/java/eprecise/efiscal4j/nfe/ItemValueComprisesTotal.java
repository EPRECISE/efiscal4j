package eprecise.efiscal4j.nfe;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indica se o valor do item ir� compor ou n�o o total da NF-e
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum ItemValueComprisesTotal {

	@XmlEnumValue("0") NAO_COMPOE_TOTAL(0, "O valor do item (vProd) n�o comp�e o valor total da NF-e"),
	@XmlEnumValue("1") COMPOE_TOTAL(1, "O valor do item (vProd) comp�e o valor total da NF-e");
	
    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private ItemValueComprisesTotal(int value, String description) {
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
