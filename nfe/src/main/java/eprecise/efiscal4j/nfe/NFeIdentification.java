
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeIdentification {

    private @XmlElement(name = "cUF") @Size(min = 1, max = 2) UF ufIbgeCode;

    private @XmlElement(name = "cNF") @Pattern(regexp = "[0-9]{8}") String nFeCode;

    private @XmlElement(name = "natOp") @NotNull @Size(min = 1, max = 60) final String operationType;

    private @XmlElement(name = "indPag") @Size(min = 1, max = 60) PaymentMethod paymentMethod;

    private @XmlElement(name = "mod") @NotNull final FiscalDocumentModel fiscalDocumentModel;

    private @XmlElement(name = "serie") @NotNull @Pattern(regexp = "0|[1-9]{1}[0-9]{0,2}") final String fiscalDocumentSeries;

    private @XmlElement(name = "nNF") @NotNull @Pattern(regexp = "[1-9]{1}[0-9]{0,8}") final String fiscalDocumentNumber;

    private @XmlElement(name = "dhEmi") @NotNull @Pattern(
            regexp = "(((20(([02468][048])|([13579][26]))-02-29))|(20[0-9][0-9])-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))T(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d[\\-,\\+](0[0-9]|10|11|12):00") final String emissionDateTime;

    private @XmlElement(name = "tpNF") @NotNull final FiscalDocumentType fiscalDocumentType;

    private @XmlElement(name = "idDest") @NotNull final DestinationOperationIdentifier destinationOperationIdentifier;

    private @XmlElement(name = "cMunFG") @NotNull @Pattern(regexp = "[0-9]{7}") final String taxableEventCityIbgeCode;

    private @XmlElement(name = "tpImp") @NotNull final String taxableEventCityIbgeCode;

}
