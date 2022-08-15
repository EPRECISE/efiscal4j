
package eprecise.efiscal4j.nfse.transmission.goiania;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSync;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaSOAPBody implements Serializable {

    private static final long serialVersionUID = 1L;

    //@formatter:off
      @XmlElementRefs({
          @XmlElementRef(name = "EnviarLoteRpsSincronoEnvio", type = GoianiaLotRpsDispatchSync.class)
      })
      private @Getter final TransmissibleBodyImpl transmissibleBody;

}
