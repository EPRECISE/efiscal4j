
package eprecise.efiscal4j.nfse.tc.commons.person.address;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum CommonsNFSeUF implements Serializable {
    @XmlEnumValue("AC") AC("12", "Acre"),
    @XmlEnumValue("AL") AL("27", "Alagoas"),
    @XmlEnumValue("AM") AM("13", "Amazonas"),
    @XmlEnumValue("AP") AP("16", "Amapa"),
    @XmlEnumValue("BA") BA("29", "Bahia"),
    @XmlEnumValue("CE") CE("23", "Ceara"),
    @XmlEnumValue("DF") DF("53", "Distrito Federal"),
    @XmlEnumValue("ES") ES("32", "Espirito Santo"),
    @XmlEnumValue("GO") GO("52", "Goias"),
    @XmlEnumValue("MA") MA("21", "Maranhao"),
    @XmlEnumValue("MG") MG("31", "Minas Gerais"),
    @XmlEnumValue("MT") MT("51", "Mato Grosso"),
    @XmlEnumValue("MS") MS("50", "Mato Grosso do Sul"),
    @XmlEnumValue("PA") PA("15", "Para"),
    @XmlEnumValue("PB") PB("25", "Paraiba"),
    @XmlEnumValue("PE") PE("26", "Pernambuco"),
    @XmlEnumValue("PI") PI("22", "Piaui"),
    @XmlEnumValue("PR") PR("41", "Parana"),
    @XmlEnumValue("RJ") RJ("33", "Rio de Janeiro"),
    @XmlEnumValue("RN") RN("24", "Rio Grande do Norte"),
    @XmlEnumValue("RO") RO("11", "Rondonia"),
    @XmlEnumValue("RR") RR("14", "Roraima"),
    @XmlEnumValue("RS") RS("43", "Rio Grande do Sul"),
    @XmlEnumValue("SC") SC("42", "Santa Catarina"),
    @XmlEnumValue("SE") SE("28", "Sergipe"),
    @XmlEnumValue("SP") SP("35", "Sao Paulo"),
    @XmlEnumValue("TO") TO("17", "Tocantins"),
    @XmlEnumValue("EX") EX("99", "Exterior");

    private static final long serialVersionUID = 1L;

    private String ibgeCode;

    private String description;

    private CommonsNFSeUF(String ibgeCode, String description) {
        this.ibgeCode = ibgeCode;
        this.description = description;
    }

    public String getIbgeCode() {
        return this.ibgeCode;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAcronym() {
        return this.toString();
    }

    public static CommonsNFSeUF findByAcronym(String acronym) {
        for (final CommonsNFSeUF uf : values()) {
            if (uf.getAcronym().equals(acronym)) {
                return uf;
            }
        }
        return null;
    }

}
