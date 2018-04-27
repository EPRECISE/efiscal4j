
package eprecise.efiscal4j.nfe.v310.address;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlEnum(String.class)
public enum IBGEOrgan implements Serializable {
    @XmlEnumValue("12") AC("12", "Acre"),
    @XmlEnumValue("27") AL("27", "Alagoas"),
    @XmlEnumValue("13") AM("13", "Amazonas"),
    @XmlEnumValue("16") AP("16", "Amapa"),
    @XmlEnumValue("29") BA("29", "Bahia"),
    @XmlEnumValue("23") CE("23", "Ceara"),
    @XmlEnumValue("53") DF("53", "Distrito Federal"),
    @XmlEnumValue("32") ES("32", "Espirito Santo"),
    @XmlEnumValue("52") GO("52", "Goias"),
    @XmlEnumValue("21") MA("21", "Maranhao"),
    @XmlEnumValue("31") MG("31", "Minas Gerais"),
    @XmlEnumValue("51") MT("51", "Mato Grosso"),
    @XmlEnumValue("50") MS("50", "Mato Grosso do Sul"),
    @XmlEnumValue("15") PA("15", "Para"),
    @XmlEnumValue("25") PB("25", "Paraiba"),
    @XmlEnumValue("26") PE("26", "Pernambuco"),
    @XmlEnumValue("22") PI("22", "Piaui"),
    @XmlEnumValue("41") PR("41", "Parana"),
    @XmlEnumValue("33") RJ("33", "Rio de Janeiro"),
    @XmlEnumValue("24") RN("24", "Rio Grande do Norte"),
    @XmlEnumValue("11") RO("11", "Rondonia"),
    @XmlEnumValue("14") RR("14", "Roraima"),
    @XmlEnumValue("43") RS("43", "Rio Grande do Sul"),
    @XmlEnumValue("42") SC("42", "Santa Catarina"),
    @XmlEnumValue("28") SE("28", "Sergipe"),
    @XmlEnumValue("35") SP("35", "Sao Paulo"),
    @XmlEnumValue("17") TO("17", "Tocantins"),
    @XmlEnumValue("90") AMB_NAC_90("90", "90 - Ambiente Nacional"),
    @XmlEnumValue("91") AMB_NAC_91("91", "91 - Ambiente Nacional"),
    @XmlEnumValue("92") AMB_NAC_92("92", "92 - Ambiente Nacional");

    private static final long serialVersionUID = 1L;

    private String ibgeCode;

    private String description;

    private IBGEOrgan(String ibgeCode, String description) {
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

    public static IBGEOrgan findByAcronym(String acronym) {
        for (final IBGEOrgan ibgeOrgan : values()) {
            if (ibgeOrgan.getAcronym().equals(acronym)) {
                return ibgeOrgan;
            }
        }
        return null;
    }

}
