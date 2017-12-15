
package eprecise.efiscal4j.nfe.deliveryDFe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


public class NFeDeliveryDFeDocumentLots implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Informação resumida ou documento fiscal eletrônico de interesse da pessoa ou empresa. O conteúdo desta tag estará compactado no padrão gZip. O tipo do campo é base64Binary.
     */
    private @XmlElement(name = "docZip") @NotNull final Collection<NFeDeliveryDfeDocument> lot;

    public NFeDeliveryDFeDocumentLots() {
        this.lot = new ArrayList<>();
    }

    public Collection<NFeDeliveryDfeDocument> getLot() {
        return this.lot;
    }

    public void addDocument(NFeDeliveryDfeDocument doc) {
        this.lot.add(doc);
    }

}
