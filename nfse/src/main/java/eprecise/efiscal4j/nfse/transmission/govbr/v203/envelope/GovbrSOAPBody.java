
package eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancel;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrSOAPBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id", namespace = OasisNamespacesPrefixMapper.WSU_URI) String id;
    
    //@formatter:off
      @XmlElementRefs({
          @XmlElementRef(name = "EnviarLoteRpsSincronoEnvio", type = GovbrLotRpsDispatchSync.class),
          @XmlElementRef(name = "CancelarNfseEnvio", type = GovbrNFSeDispatchCancel.class)
      })
      private @Getter final TransmissibleBodyImpl transmissibleBody;

}
