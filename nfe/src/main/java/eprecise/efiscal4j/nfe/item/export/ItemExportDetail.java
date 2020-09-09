
package eprecise.efiscal4j.nfe.item.export;

import lombok.Builder;
import lombok.Getter;

/**
 * Detalhe da exportação
 *
 */

@Builder
@Getter
public class ItemExportDetail {

	private final String drawbackNumber;
	
	private final ItemIndirectExport indirectExport;

}
