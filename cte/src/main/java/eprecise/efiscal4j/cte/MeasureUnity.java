package eprecise.efiscal4j.cte;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum MeasureUnity {
    @XmlEnumValue("00")
    M3,
    @XmlEnumValue("01")
    KG,
    @XmlEnumValue("02")
    TON,
    @XmlEnumValue("03")
    UNIDADE,
    @XmlEnumValue("04")
    LITROS,
    @XmlEnumValue("05")
    MMBTU
}
