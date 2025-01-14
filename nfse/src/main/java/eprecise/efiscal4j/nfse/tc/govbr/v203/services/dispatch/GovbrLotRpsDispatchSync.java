
package eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.v203.lot.GovbrLotRps;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "EnviarLoteRpsSincronoEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "rpsLot", "signature" })
public class GovbrLotRpsDispatchSync extends DefaultAssignable implements NFSeRequest, TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/v203/nfse_v2_03.xsd";

    private @Getter final @NotNull @XmlElement(name = "LoteRps") GovbrLotRps rpsLot;

    private @Getter @Setter @Builder.Default @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoEnvio");

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }
    
    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, GovbrLotRpsDispatchSync.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "EnviarLoteRpsSincronoEnvio";
    }

    @Override
    public String getAssignableTagName() {
        return "LoteRps";
    }

    @Override
    public String getIdAttributeTagName() {
        return "id";
    }

}
