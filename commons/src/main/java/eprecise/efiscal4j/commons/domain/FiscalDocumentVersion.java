
package eprecise.efiscal4j.commons.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @author Felipe Bueno
 *
 */
@XmlType
@XmlEnum(String.class)
public enum FiscalDocumentVersion implements Serializable {

    @XmlEnumValue("3.10") VERSION_3_10("3.10"),
    @XmlEnumValue("2.00") VERSION_2_00("2.00"),
    @XmlEnumValue("1.00") VERSION_1_00("1.00"),
    @XmlEnumValue("1.01") VERSION_1_01("1.01");

    private static final long serialVersionUID = 1L;

    private final String value;

    private FiscalDocumentVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getFormattedValue() {
        return this.value.replace('.', '_');
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
