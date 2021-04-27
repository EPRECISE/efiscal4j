
package eprecise.efiscal4j.nfe.transmit;

import java.io.FileInputStream;

import org.junit.Assume;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocument.ReceiptedAsync;
import eprecise.efiscal4j.nfe.FiscalDocument.TransmissionResult;
import eprecise.efiscal4j.nfe.FiscalDocumentCancel;
import eprecise.efiscal4j.nfe.FiscalDocumentSearchByReceipt;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NFeTestParams;
import eprecise.efiscal4j.nfe.domain.NFeDomainTest;
import eprecise.efiscal4j.nfe.event.EventStatus;
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

        final EventStatus status = result.getStatus();

        System.out.println(status.getStatusCode() + " - "+status.getStatusDescription());

        if(status.getStatusCode() == "103") {

            final ReceiptedAsync receyptedAsync = result.getReceiptedAsync(this.nfe);

            this.transmitFiscalDocumentSearchTest(receyptedAsync);
        }
    }

    //@Test
    public void transmitFiscalDocumentSearch() {
          final ReceiptedAsync receyptedAsync = ReceiptedAsync.builder()
          .document(this.nfe)
          .receiptNumber("291200213913002")
          .build();
          this.transmitFiscalDocumentSearchTest(receyptedAsync);
    }


    public void transmitFiscalDocumentSearchTest(final ReceiptedAsync receiptAsync) {
        final FiscalDocumentSearchByReceipt search = FiscalDocumentSearchByReceipt.builder()
                .receiptedAsync(receiptAsync)
                .build();

        final eprecise.efiscal4j.nfe.FiscalDocumentSearchByReceipt.TransmissionResult result = search.transmit(this.keyCertificate);
    }

    public void cancel(final FiscalDocument.Processed processed) {
        final FiscalDocumentCancel cancel = FiscalDocumentCancel.builder()
        .processedFiscalDocument(processed)
        .justification("TESTE DE EMISSÃO DE NFE. NFE SEM VALOR FISCAL")
        .build();
        final eprecise.efiscal4j.nfe.FiscalDocumentCancel.TransmissionResult result = cancel.transmit(this.keyCertificate);
        System.out.println(result.getStatus().getStatusCode() + " - "+result.getStatus().getStatusDescription());

    }

}
