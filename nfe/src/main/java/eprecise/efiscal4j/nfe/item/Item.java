
package eprecise.efiscal4j.nfe.item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.item.di.ImportDeclaration;
import eprecise.efiscal4j.nfe.item.fuel.Fuel;
import eprecise.efiscal4j.nfe.item.medications.Medications;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.item.trace.Trace;
import eprecise.efiscal4j.nfe.v400.types.NFeString;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Builder
@Getter
@EqualsAndHashCode
public class Item {

    private final String code;

    private final String name;

    private final ItemEan globalTradeItemNumber;

    private final ItemUnity unity;

    private final ItemQuantity quantity;

    private final ItemUnitaryValue unitaryValue;

    private final BigDecimal discount;

    private final BigDecimal freight;

    private final BigDecimal insurance;

    private final BigDecimal othersValue;

    private final TaxStructure taxStructure;

    private final Collection<ImportDeclaration> importDeclarations;

    private final Medications medications;
    
    private final Fuel fuel;

    private final Collection<Trace> traces;
    
    private final String purchaseOrderDescription;

    private final String purchaseOrderNumber;

    private final String fciNumber;

    private final String additionalInfo;

    public static class ItemBuilder {

        public ItemBuilder globalTradeItemNumber(final ItemEan globalTradeItemNumber) {
            this.globalTradeItemNumber = globalTradeItemNumber;
            return this;
        }

        public ItemBuilder globalTradeItemNumber(final String globalTradeItemNumber) {
            this.globalTradeItemNumber = ItemEan.builder().globalTradeItemNumber(globalTradeItemNumber).taxableGlobalTradeItemNumber(globalTradeItemNumber).build();
            return this;
        }

        public ItemBuilder unity(final ItemUnity unity) {
            this.unity = unity;
            return this;
        }

        public ItemBuilder unity(final Unity unity) {
            this.unity = ItemUnity.builder().comercialUnity(unity).taxableUnity(unity).build();
            return this;
        }

        public ItemBuilder quantity(final ItemQuantity quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder quantity(final BigDecimal quantity) {
            this.quantity = ItemQuantity.builder().comercialQuantity(quantity).taxableQuantity(quantity).build();
            return this;
        }

        public ItemBuilder unitaryValue(final ItemUnitaryValue unitaryValue) {
            this.unitaryValue = unitaryValue;
            return this;
        }

        public ItemBuilder unitaryValue(final BigDecimal unitaryValue) {
            this.unitaryValue = ItemUnitaryValue.builder().comercialUnitaryValue(unitaryValue).taxableUnitaryValue(unitaryValue).build();
            return this;
        }
    }

    public ItemGrossValue getGrossValue() {
        if(this.unitaryValue != null && this.quantity != null) {
            BigDecimal comercialGrossValue = null;
            BigDecimal taxableGrossValue = null;
            if(this.unitaryValue.comercialUnitaryValue != null && this.quantity.comercialQuantity != null) {
                comercialGrossValue = this.unitaryValue.comercialUnitaryValue.multiply(this.quantity.comercialQuantity).setScale(2, RoundingMode.HALF_UP);
            }
            if(this.unitaryValue.taxableUnitaryValue != null && this.quantity.taxableQuantity != null) {
                taxableGrossValue = this.unitaryValue.taxableUnitaryValue.multiply(this.quantity.taxableQuantity).setScale(2, RoundingMode.HALF_UP);
            }
            return ItemGrossValue.builder().comercialGrossValue(comercialGrossValue).taxableGrossValue(taxableGrossValue).build();
        }
        return null;
    }

    @Builder
    @Getter
    public static class ItemEan {

        private final String globalTradeItemNumber;

        private final String taxableGlobalTradeItemNumber;

    }

    @Builder
    @Getter
    public static class ItemUnity {

        private final Unity comercialUnity;

        private final Unity taxableUnity;

    }

    @Builder
    @Getter
    public static class ItemQuantity {

        private final BigDecimal comercialQuantity;

        private final BigDecimal taxableQuantity;

    }
    
    @Builder
    @Getter
    public static class ItemGrossValue {

        private final BigDecimal comercialGrossValue;

        private final BigDecimal taxableGrossValue;

    }

    @Builder
    @Getter
    public static class ItemUnitaryValue {

        private final BigDecimal comercialUnitaryValue;

        private final BigDecimal taxableUnitaryValue;

    }

}
