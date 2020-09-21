
package eprecise.efiscal4j.nfse.domain.service;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeDiscount {
    
    private final @Builder.Default BigDecimal  unconditionedValue = BigDecimal.ZERO;

    private final @Builder.Default BigDecimal  conditionedValue = BigDecimal.ZERO;
    
    public BigDecimal getTotal() {
        return this.unconditionedValue.add(this.conditionedValue);
    }

}
