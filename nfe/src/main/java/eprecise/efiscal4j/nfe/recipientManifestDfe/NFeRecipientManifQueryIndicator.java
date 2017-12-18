
package eprecise.efiscal4j.nfe.recipientManifestDfe;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum NFeRecipientManifQueryIndicator implements Serializable {
                                                                     @XmlEnumValue("0")
                                                                     ALL_NFE(0, "Todas as NF-e"),
                                                                     @XmlEnumValue("1")
                                                                     ONLY_NOT_CONFIRMED(1, "Somente as NF-e ainda não confirmadas"),
                                                                     @XmlEnumValue("2")
                                                                     ONLY_NOT_CONFIRMED_INCLUDING_AWERENESS(2, "Somente as NF-e ainda não confirmadas (inclusive Ciência)");

    private final int value;

    private final String description;

    NFeRecipientManifQueryIndicator(int value, String description) {
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
        return this.description;
    }

}
