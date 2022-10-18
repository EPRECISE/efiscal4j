
package eprecise.efiscal4j.nfse.tc.goiania.services.dispatch;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.goiania.lot.statements.GoianiaStatementProvisionService;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "GerarNfseEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "rps", "signature" })
public class GoianiaLotRpsDispatchSync extends DefaultAssignable implements NFSeRequest, TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/goiania/nfse_gyn_v02.xsd";
    private @Getter final @NotNull @XmlElement(name = "Rps") GoianiaStatementProvisionService rps;

    private @Getter @Setter @Builder.Default @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoEnvio");

    public static class GoianiaLotRpsDispatchSyncBuilder {
        public GoianiaLotRpsDispatchSync build(final Signer signer) throws Exception {
            GoianiaLotRpsDispatchSync entity = new GoianiaLotRpsDispatchSync(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (GoianiaLotRpsDispatchSync) signer.sign(entity);
            return entity;
        }
    }

    public GoianiaLotRpsDispatchSync(final GoianiaLotRpsDispatchSync.GoianiaLotRpsDispatchSyncBuilder builder) {
        rps = builder.rps;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }
    
    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, GoianiaLotRpsDispatchSync.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "GerarNfseEnvio";
    }

    @Override
    public String getAssignableTagName() {
        return "Rps";
    }

    @Override
    public String getIdAttributeTagName() {
        return "id";
    }

}
