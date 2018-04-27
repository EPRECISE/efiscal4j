
package eprecise.efiscal4j.nfe.v310;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * Identificação do Ambiente: 1 - Produção 2 - Homologação
 * 
 * @author Felipe Bueno
 *
 */
@XmlType
@XmlEnum(Integer.class)
public enum TransmissionEnvironment implements Serializable {

                                                             @XmlEnumValue("1")
                                                             PRODUCAO(1, "Produção"),
                                                             @XmlEnumValue("2")
                                                             HOMOLOGACAO(2, "Homologação");

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private TransmissionEnvironment(int value, String description) {
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

    public static Optional<TransmissionEnvironment> findBy(int value) {
        return Stream.of(TransmissionEnvironment.values()).filter(te -> te.value == value).findFirst();
    }

}
