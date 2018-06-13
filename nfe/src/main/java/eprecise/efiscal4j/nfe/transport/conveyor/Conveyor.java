
package eprecise.efiscal4j.nfe.transport.conveyor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.transport.conveyor.cnp.ConveyorCnp;
import lombok.Builder;
import lombok.Getter;


/**
 * Dados da Transportadora
 * 
 * @author Fernando Glizt
 * 
 */
@Builder
@Getter
public class Conveyor {

    /**
     * @see ConveyorCnp
     * @param cnp
     */
    private @Valid final ConveyorCnp cnp;

    /**
     * Razão Social ou nome do transportador
     * 
     * @param name
     */
    private @Size(min = 2, max = 60, message = "{eprecise.efiscal4j.nfe.transport.conveyor.name.isSize}") final String name;

    /**
     * Inscrição Estadual do transportador ou informar ISENTO
     * 
     * @param ie
     */
    private @Pattern(regexp = "ISENTO|[0-9]{2,14}", message = "{eprecise.efiscal4j.nfe.transport.conveyor.ie.isPattern}") final String ie;

    /**
     * Endereço completo do transportador
     * 
     * @param fullAddress
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.conveyor.fullAddress.isSize}") final String fullAddress;

    /**
     * Nome do munícipio do endereço do transportador
     * 
     * @param cityName
     */
    private @Size(min = 1, max = 60, message = "{eprecise.efiscal4j.nfe.transport.conveyor.cityName.isSize}") final String cityName;

    /**
     * UF do endereço do transportador
     * 
     * @param uf
     */
    private @Valid final UF uf;

}
