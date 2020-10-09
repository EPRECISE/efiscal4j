
package eprecise.efiscal4j.nfse.tc.curitiba.cancel;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeDateTimeUTC;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "signature", "confirmation" })
public class CuritibaNfseCancelResponse extends DefaultAssignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Confirmacao") @NotNull Confirmation confirmation;


    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @Getter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Confirmation {

        private final @XmlAttribute(name = "id") String id = UUID.randomUUID().toString().replaceAll("-", "");

        private final @XmlElement(name = "Pedido") @NotNull CuritibaNfseCancelRequest request;

        private final @XmlElement(name = "DataHoraCancelamento") @NFSeDateTimeUTC @NotNull String date;

    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, CuritibaNfseCancelResponse.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "CancelarNfseEnvio";
    }

    @Override
    public String getAssignableTagName() {
        return "InfPedidoCancelamento";
    }

    @Override
    public String getIdAttributeTagName() {
        return "id";
    }

}
