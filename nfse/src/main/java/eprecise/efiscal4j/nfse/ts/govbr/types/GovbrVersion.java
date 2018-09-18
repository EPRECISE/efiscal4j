
package eprecise.efiscal4j.nfse.ts.govbr.types;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@XmlType
@XmlEnum(String.class)
@RequiredArgsConstructor
public enum GovbrVersion implements Serializable {
                                                  @XmlEnumValue("1.00")
                                                  VERSION_1_00("1.00"),
                                                  @XmlEnumValue("2.03")
                                                  VERSION_2_03("2.03");

    private static final long serialVersionUID = 1L;

    private @Getter final String version;

}
