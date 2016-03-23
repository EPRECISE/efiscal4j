
package eprecise.efiscal4j.nfe.tax.icms.desoneration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Motivo da desoneração do ICMS
 *
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(String.class)
public enum ICMSDesonerationReason implements Serializable {
    //@formatter:off
    @XmlEnumValue("1") TAXI("1", "Táxi"),
    @XmlEnumValue("3") PRODUTOR_AGROPECUARIO("3", "Produtor agropecuário"),
    @XmlEnumValue("4") FROTISTA_LOCADORA("4", "Frotista/Locadora"),
    @XmlEnumValue("5") DIPLOMATA_CONSULAR("5", "Diplomático/Consular"),
    @XmlEnumValue("6") UTILITARIO_MOTOCICLETAS_AREA_LIVRE_COMERCIO("6",
            "Utilitários e Motocicletas da Amazônia Ocidental e Áreas de Livre Comércio (Resolução 714/88 e 790/94 – CONTRAN e suas alterações)"),
    @XmlEnumValue("7") SUFRAMA("7", "SUFRAMA"),
    @XmlEnumValue("8") VENDA_A_ORGAO_PUBLICO("8", "Venda a órgão Público"),
    @XmlEnumValue("9") OUTROS("9", "Outros"),
    @XmlEnumValue("10") DEFICIENTE_CONDUTOR("10", "Deficiente Condutor"),
    @XmlEnumValue("11") DEFICIENTE_NAO_CONDUTOR("11", "Deficiente não condutor"),
    @XmlEnumValue("12") FOMENTO_AGROPECUARIO("12", "Fomento agropecuário"),
    @XmlEnumValue("16") OLIMPIADAS_RIO_2016("16", "Olimpíadas Rio 2016");
    //@formatter:on
    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private ICMSDesonerationReason(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getICMSDesonerationReasonWithDescription() {
        return this.value + " - " + this.description;
    }

    public static ICMSDesonerationReason findByCode(String code) {
        for (final ICMSDesonerationReason icmsDesonerationReason : ICMSDesonerationReason.values()) {
            if (icmsDesonerationReason.getValue().equals(code)) {
                return icmsDesonerationReason;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
