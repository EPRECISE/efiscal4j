
package eprecise.efiscal4j.nfe.recipientManifestDfe;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum NFeRecipientManifService implements Serializable {
                                                              @XmlEnumValue("DOWNLOAD NFE")
                                                              DOWNLOAD_NFE("DOWNLOAD NFE"),
                                                              @XmlEnumValue("CONSULTAR NFE DEST")
                                                              NFE_RECIPIENT_QUERY("CONSULTAR NFE DEST");

    private final String value;

    NFeRecipientManifService(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
