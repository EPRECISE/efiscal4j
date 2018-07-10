
package eprecise.efiscal4j.nfe.item.di;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;


/**
 * Declaração de Importação (NT 2011/004)
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class ImportDeclaration {

    private final String number;

    private final Date date;

    private final String clearanceSpot;

    private final UF clearanceUf;

    private final Date clearanceDate;

    private final InternationalTransportPathway internationalTransportPathway;

    private final BigDecimal additValShipMerchMarineRenovation;

    private final IntermediaryImportType intermediaryImportType;

    private final String acquirerOrOrderingPartyCnpj;

    private final UF acquirerOrOrderingPartyUf;

    private final String exporterCode;

    private final List<Addition> additions;

}
