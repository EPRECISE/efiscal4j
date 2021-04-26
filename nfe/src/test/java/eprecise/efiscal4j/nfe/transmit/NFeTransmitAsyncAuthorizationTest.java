
package eprecise.efiscal4j.nfe.transmit;

import java.io.FileInputStream;

import org.junit.Assume;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocument.ReceiptedAsync;
import eprecise.efiscal4j.nfe.FiscalDocument.TransmissionResult;
import eprecise.efiscal4j.nfe.FiscalDocumentSearch;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.domain.NFeDomainTest;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;


public class NFeTransmitAsyncAuthorizationTest {

    private final NFe nfe = new NFeDomainTest().buildNFe();

    private final Certificate keyCertificate = new Certificate(() -> new FileInputStream(NFeTestParams.getCertificatePath().get()), NFeTestParams.getCertificatePin().get());

    // @Test
    public void transmitAsyncAuthorization() throws Exception {
     // @formatter:off
        Assume.assumeFalse(!NFeTestParams.getCertificatePath().isPresent()
                || !NFeTestParams.getCertificatePin().isPresent()
                || !NFeTestParams.getEmitterCnpj().isPresent()
                || !NFeTestParams.getEmitterIe().isPresent()
                || !NFeTestParams.getReceiverCnpj().isPresent());
     // @formatter:true

        final TransmissionResult result = this.nfe.transmit(FiscalDocumentSupportedVersion.VERSION_4_00, this.keyCertificate);

        System.out.println(result.getStatus().getStatusCode() + " - "+result.getStatus().getStatusDescription());

        //this.transmitFiscalDocumentSearchTest(result.getReceiptedAsync(this.nfe));
    }


    public void transmitFiscalDocumentSearchTest(final ReceiptedAsync receiptAsync) {
        final FiscalDocumentSearch search = FiscalDocumentSearch.builder()
                .receiptedAsync(receiptAsync)
                .build();

        final eprecise.efiscal4j.nfe.FiscalDocumentSearch.TransmissionResult result = search.transmit(this.keyCertificate);

        System.out.println(result.getProcessed().getXml());
    }

}
