
package eprecise.efiscal4j.nfe.total;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.item.Item.ItemGrossValue;
import eprecise.efiscal4j.nfe.item.tax.ApproximateTax;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.total.tax.TotalTaxes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;


@Builder
@AllArgsConstructor
public class FiscalDocumentTotal {

    private final Supplier<Collection<Item>> items;

    private final AddsValue addsValue;

    public BigDecimal getComercialGrossTotalValue() {
        return this.scale(
                this.items.get().stream().map(Item::getGrossValue).map(ItemGrossValue::getComercialGrossValue).filter(Objects::nonNull).map(this::scale).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public BigDecimal getTaxableGrossTotalValue() {
        return this
                .scale(this.items.get().stream().map(Item::getGrossValue).map(ItemGrossValue::getTaxableGrossValue).filter(Objects::nonNull).map(this::scale).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public BigDecimal getShippingTotalValue() {
        return this.scale(this.items.get().stream().map(Item::getFreight).filter(Objects::nonNull).map(this::scale).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public BigDecimal getInsuranceTotalValue() {
        return this.scale(this.items.get().stream().map(Item::getInsurance).filter(Objects::nonNull).map(this::scale).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public BigDecimal getDiscountTotalValue() {
        return this.scale(this.items.get().stream().map(Item::getDiscount).filter(Objects::nonNull).map(this::scale).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public BigDecimal getOthersTotalValue() {
        return this.scale(this.items.get().stream().map(Item::getOthersValue).filter(Objects::nonNull).map(this::scale).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public TotalTaxes getTotalTaxes() {
        return new TotalTaxes(() -> this.items.get().stream().map(Item::getTaxStructure).flatMap(ts -> ts.getTaxes().stream()).collect(Collectors.toSet()));
    }

    public BigDecimal getFiscalDocumentTotalValue() {
     // @formatter:off
        return this.getComercialGrossTotalValue()
                .add(this.getTotalTaxes().getTotalIcmsStValue())
                .add(this.getShippingTotalValue())
                .add(this.getInsuranceTotalValue())
                .add(this.getOthersTotalValue())
                .add(this.getTotalTaxes().getTotalIIValue())
                .add(this.getTotalTaxes().getTotalIPIValue())
                .subtract(this.getDiscountTotalValue())
                .subtract(Optional.ofNullable(this.addsValue).filter(AddsValue::isTotalIcmsDesonerationValue).map(it -> this.getTotalTaxes().getTotalIcmsDesonerationValue()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(this.addsValue).filter(AddsValue::isTotalIcmsValue).map(it -> this.getTotalTaxes().getTotalIcmsValue()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(this.addsValue).filter(AddsValue::isTotalPisValue).map(it -> this.getTotalTaxes().getTotalPisValue()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(this.addsValue).filter(AddsValue::isTotalCofinsValue).map(it -> this.getTotalTaxes().getTotalCofinsValue()).orElse(BigDecimal.ZERO));
     // @formatter:on
    }

    public ApproximateTax getApproximateTaxTotalValue() {
        final BigDecimal nationalTax = this.scale(this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getNationalTax).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        final BigDecimal stateTax = this.scale(this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getStateTax).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        final BigDecimal importTax = this.scale(this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getImportTax).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        final BigDecimal cityTax = this.scale(this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getCityTax).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        return ApproximateTax.builder().nationalTax(nationalTax).stateTax(stateTax).importTax(importTax).cityTax(cityTax).build();
    }

    private BigDecimal scale(final BigDecimal value) {
        return Optional.ofNullable(value).map(v -> v.setScale(2, RoundingMode.HALF_UP)).orElse(null);
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class AddsValue implements Serializable {

        private static final long serialVersionUID = 1L;

        private @Default final boolean totalIcmsValue = false;

        private @Default final boolean totalPisValue = false;

        private @Default final boolean totalCofinsValue = false;

        private @Default final boolean totalIcmsDesonerationValue = true;

    }

}
