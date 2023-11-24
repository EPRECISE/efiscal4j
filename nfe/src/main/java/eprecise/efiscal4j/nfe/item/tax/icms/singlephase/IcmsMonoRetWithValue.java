package eprecise.efiscal4j.nfe.item.tax.icms.singlephase;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class IcmsMonoRetWithValue {

    private final BigDecimal qBcValue;

    private final BigDecimal aliquot;

    private final BigDecimal icmsValue;

    public interface IcmsMonoRetWithValueHolder {

        IcmsMonoRetWithValue getIcmsMonoRetWithValue();

    }
}