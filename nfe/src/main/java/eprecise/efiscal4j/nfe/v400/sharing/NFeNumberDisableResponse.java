
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Retorno do Pedido de Inutilização de Numeração da Nota Fiscal Eletrônica
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = ObjectFactory.RET_INUT_NFE)
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeNumberDisableResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/retInutNFe_v3.10.xsd";

    private @XmlElement(name = "infInut") @NotNull @Valid NFeNumberDisableResponseInfo info;

    private @XmlElement(name = "Signature") SignatureType signature;

    private @XmlAttribute(name = "versao") FiscalDocumentVersion version;

    public SignatureType getSignature() {
        return this.signature;
    }

    public void setSignature(final SignatureType signature) {
        this.signature = signature;
    }

    public NFeNumberDisableResponseInfo getInfo() {
        return this.info;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

}
