
package eprecise.efiscal4j.nfe.item;

import java.math.BigDecimal;

import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
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

    public static class ItemBuilder {

        public ItemBuilder globalTradeItemNumber(ItemEan globalTradeItemNumber) {
            this.globalTradeItemNumber = globalTradeItemNumber;
            return this;
        }

        public ItemBuilder globalTradeItemNumber(String globalTradeItemNumber) {
            this.globalTradeItemNumber = ItemEan.builder().globalTradeItemNumber(globalTradeItemNumber).taxableGlobalTradeItemNumber(globalTradeItemNumber).build();
            return this;
        }

        public ItemBuilder unity(ItemUnity unity) {
            this.unity = unity;
            return this;
        }

        public ItemBuilder unity(Unity unity) {
            this.unity = ItemUnity.builder().comercialUnity(unity).taxableUnity(unity).build();
            return this;
        }

        public ItemBuilder quantity(ItemQuantity quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder quantity(BigDecimal quantity) {
            this.quantity = ItemQuantity.builder().comercialQuantity(quantity).taxableQuantity(quantity).build();
            return this;
        }

        public ItemBuilder unitaryValue(ItemUnitaryValue unitaryValue) {
            this.unitaryValue = unitaryValue;
            return this;
        }

        public ItemBuilder unitaryValue(BigDecimal unitaryValue) {
            this.unitaryValue = ItemUnitaryValue.builder().comercialUnitaryValue(unitaryValue).taxableUnitaryValue(unitaryValue).build();
            return this;
        }
    }

    public ItemGrossValue getGrossValue() {
        return ItemGrossValue.builder().comercialGrossValue(this.unitaryValue.comercialUnitaryValue.multiply(this.quantity.comercialQuantity))
                .taxableGrossValue(this.unitaryValue.taxableUnitaryValue.multiply(this.quantity.taxableQuantity)).build();
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
    public static class ItemUnitaryValue {

        private final BigDecimal comercialUnitaryValue;

        private final BigDecimal taxableUnitaryValue;

    }

    @Builder
    @Getter
    public static class ItemGrossValue {

        private final BigDecimal comercialGrossValue;

        private final BigDecimal taxableGrossValue;

    }
}
