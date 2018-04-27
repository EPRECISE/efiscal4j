
package eprecise.efiscal4j.nfe.v400;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Processo de emissão de NF-e
 * 
 * @author Felipe Bueno
 *
 */

@XmlType
@XmlEnum(Integer.class)
public enum NFeTransmissionProcess {

    @XmlEnumValue("0") APLICATIVO_CONTRIBUINTE(0, "Emissão de NF-e com aplicativo do contribuinte"),
    @XmlEnumValue("1") AVULSA_FISCO(1, "Emissão de NF-e avulsa pelo Fisco"),
    @XmlEnumValue("2") AVULSA_CONTRIBUINTE_CERTIFICADO_DIGITAL_SITE_FISCO(2, "Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, atrav�s do site do Fisco"),
    @XmlEnumValue("3") CONTRIBUINTE_APLICATIVO_FISCO(3, "Emissão de NF-e pelo contribuinte com aplicativo fornecido pelo Fisco");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private NFeTransmissionProcess(int value, String description) {
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
