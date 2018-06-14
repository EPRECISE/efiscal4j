
package eprecise.efiscal4j.nfe.item.tax.ipi.generalData;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class IPIGeneralData {

    private final String producerCnpj;

    private final String ipiSealCode;

    private final String ipiSealQuantity;

    private final String legalFramework;

}
