
package eprecise.efiscal4j.nfe.total;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import eprecise.efiscal4j.nfe.item.Item;
import eprecise.efiscal4j.nfe.item.Item.ItemGrossValue;
import eprecise.efiscal4j.nfe.item.tax.ApproximateTax;
import eprecise.efiscal4j.nfe.item.tax.TaxStructure;
import eprecise.efiscal4j.nfe.total.tax.TotalTaxes;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FiscalDocumentTotal {

    private final Supplier<Collection<Item>> items;

    public BigDecimal getComercialGrossTotalValue() {
        return this.items.get().stream().map(Item::getGrossValue).map(ItemGrossValue::getComercialGrossValue).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTaxableGrossTotalValue() {
        return this.items.get().stream().map(Item::getGrossValue).map(ItemGrossValue::getTaxableGrossValue).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getShippingTotalValue() {
        return this.items.get().stream().map(Item::getFreight).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getInsuranceTotalValue() {
        return this.items.get().stream().map(Item::getInsurance).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountTotalValue() {
        return this.items.get().stream().map(Item::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOthersTotalValue() {
        return this.items.get().stream().map(Item::getOthersValue).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public TotalTaxes getTotalTaxes() {
        return new TotalTaxes(() -> this.items.get().stream().map(Item::getTaxStructure).flatMap(ts -> ts.getTaxes().stream()).collect(Collectors.toSet()));
    }

    public BigDecimal getFiscalDocumentTotalValue() {
        return this.getComercialGrossTotalValue().add(this.getTotalTaxes().getTotalIcmsStValue()).add(this.getShippingTotalValue()).add(this.getInsuranceTotalValue()).add(this.getOthersTotalValue())
                .add(this.getTotalTaxes().getTotalIIValue()).add(this.getTotalTaxes().getTotalIPIValue()).subtract(this.getDiscountTotalValue())
                .subtract(this.getTotalTaxes().getTotalIcmsDesonerationValue()).setScale(2, RoundingMode.HALF_UP);
    }

    public ApproximateTax getApproximateTaxTotalValue() {
        final BigDecimal nationalTax = this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getNationalTax).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal stateTax = this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getStateTax).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal importTax = this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getImportTax).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal cityTax = this.items.get().stream().map(Item::getTaxStructure).filter(ts -> ts.getApproximateTax() != null).map(TaxStructure::getApproximateTax)
                .map(ApproximateTax::getCityTax).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
        return ApproximateTax.builder().nationalTax(nationalTax).stateTax(stateTax).importTax(importTax).cityTax(cityTax).build();
    }

}
