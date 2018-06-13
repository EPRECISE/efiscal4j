
package eprecise.efiscal4j.nfe.serie;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * Identificação do Ambiente: 1 - Produção 2 - Homologação
 * 
 * @author Fernando Glizt
 *
 */
public enum TransmissionEnvironment implements Serializable {

                                                             PRODUCTION(1, "Produção"),
                                                             HOMOLOGATION(2, "Homologação");

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
