
package eprecise.efiscal4j.nfe.v310.domain;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.nfe.v310.transmission.NFeBody;
import eprecise.efiscal4j.nfe.v310.transmission.NFeHeader;
import eprecise.efiscal4j.nfe.v310.transmission.Receivable;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPBody;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPBodyResponse;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPEnvelope;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPEnvelopeResponse;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPHeader;
import eprecise.efiscal4j.nfe.v310.transmission.SOAPHeaderResponse;


class SoapEnvelopeDomain {

    private static SoapEnvelopeDomain internalEntity;

    private SoapEnvelopeDomain() {
    }

    public static SoapEnvelopeDomain getInstance() {
        if (SoapEnvelopeDomain.internalEntity == null) {
            SoapEnvelopeDomain.internalEntity = new SoapEnvelopeDomain();
        }
        return SoapEnvelopeDomain.internalEntity;
    }

    public SOAPEnvelope buildSoapEnvelope(SOAPHeader soapHeader, SOAPBody soapBody) {
        //@formatter:off 
        return new SOAPEnvelope.Builder()
              .withSoapHeader(soapHeader) 
              .withSoapBody(soapBody)
              .build();
        //@formatter:on                      
    }

    public SOAPHeader buildSoapHeader(NFeHeader nFeHeader) {
        //@formatter:off 
        return new SOAPHeader.Builder()
              .withNfeHeader(nFeHeader)
              .build();
        //@formatter:on                      
    }

    public SOAPBody buildSoapBody(NFeBody nFeBody) {
        //@formatter:off 
        return new SOAPBody.Builder()
              .withNfeBody(nFeBody)
              .build();
        //@formatter:on                      
    }

    public NFeHeader buildNFeHeader(String xmlns, UF uf) {
        //@formatter:off 
        return new NFeHeader.Builder()
              .withXmlns(xmlns)
              .withUf(uf)
              .build();
        //@formatter:on 
    }

    public NFeHeader buildNFeHeader(String xmlns, UF uf, FiscalDocumentVersion dataVersion) {
        //@formatter:off 
        return new NFeHeader.Builder()
              .withXmlns(xmlns)
              .withUf(uf)
              .withDataVersion(dataVersion)
              .build();
        //@formatter:on 
    }

    public NFeBody buildNFeBody(String xmlns, TransmissibleBodyImpl transmissible) {
        //@formatter:off 
        return new NFeBody.Builder()
              .withXmlns(xmlns)
              .withTransmissible(transmissible)
              .build();
        //@formatter:on 
    }

    public SOAPEnvelopeResponse buildSOAPEnvelopeResponse(SOAPHeaderResponse soapHeaderResponse, SOAPBodyResponse soapBodyResponse) {
        //@formatter:off
        return new SOAPEnvelopeResponse.Builder()
                 .withSoapHeaderResponse(soapHeaderResponse)
                 .withSoapBodyResponse(soapBodyResponse)
                 .build();        
        //@formatter:on                
    }

    public SOAPHeaderResponse buildSoapHeaderResponse(NFeHeader nFeHeader) {
        //@formatter:off
        return new SOAPHeaderResponse.Builder()
                 .withNfeHeader(nFeHeader)
                 .build();
        //@formatter:on
    }

    public SOAPBodyResponse buildSoapBodyResponse(Receivable receivable) {
        //@formatter:off
        return new SOAPBodyResponse.Builder()
                 .withReceivable(receivable)
                 .build();
        //@formatter:on
    }

}
