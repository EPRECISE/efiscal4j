
package eprecise.efiscal4j.nfe.transport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.commons.domain.adress.UF;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados dos veículos da NF-e
 *
 * @author Fernando Glizt
 *
 */
@Builder
@Getter
public class Vehicle {

    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.vehicle.licensePlate.isNotNull}") @Pattern(
            regexp = "[A-Z]{2,3}[0-9]{4}|[A-Z]{3,4}[0-9]{3}", message = "{eprecise.efiscal4j.nfe.transport.vehicle.licensePlate.isNotPlate}") final String licensePlate;

    private @NotNull(message = "{eprecise.efiscal4j.nfe.transport.vehicle.uf.isNotNull}") final UF uf;

    private @Size(min = 1, max = 20, message = "{eprecise.efiscal4j.nfe.transport.vehicle.rntc.isSize}") final String rntc;

}
