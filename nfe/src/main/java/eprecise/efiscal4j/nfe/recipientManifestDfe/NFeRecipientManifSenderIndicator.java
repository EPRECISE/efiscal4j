
package eprecise.efiscal4j.nfe.recipientManifestDfe;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum NFeRecipientManifSenderIndicator implements Serializable {
                                                                      @XmlEnumValue("0")
                                                                      ALL_NFE(0, "Todos os Emitentes"),
                                                                      @XmlEnumValue("1")
                                                                      ALL_BUT_OWN(1, "Somente as NF-e emitidas por emissores que não tenham a mesma raiz do CNPJ");

    private final int value;

    private final String description;

    NFeRecipientManifSenderIndicator(int value, String description) {
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
