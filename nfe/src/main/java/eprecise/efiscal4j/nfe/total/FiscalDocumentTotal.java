
package eprecise.efiscal4j.nfe.total;

import java.math.BigDecimal;
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
        return items.get().stream().map(Item::getGrossValue).map(ItemGrossValue::getComercialGrossValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTaxableGrossTotalValue() {
        return items.get().stream().map(Item::getGrossValue).map(ItemGrossValue::getTaxableGrossValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getShippingTotalValue() {
        return items.get().stream().map(Item::getFreight).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getInsuranceTotalValue() {
        return items.get().stream().map(Item::getInsurance).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscountTotalValue() {
        return items.get().stream().map(Item::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getOthersTotalValue() {
        return items.get().stream().map(Item::getOthersValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public TotalTaxes getTotalTaxes() {
        return new TotalTaxes(() -> items.get().stream().map(Item::getTaxStructure).flatMap(ts -> ts.getTaxes().stream()).collect(Collectors.toSet()));
    }

    public BigDecimal getFiscalDocumentTotalValue() {
        return this.getComercialGrossTotalValue().add(this.getTotalTaxes().getTotalIcmsStValue()).add(this.getShippingTotalValue()).add(this.getInsuranceTotalValue()).add(this.getOthersTotalValue())
                .add(this.getTotalTaxes().getTotalIIValue()).add(this.getTotalTaxes().getTotalIPIValue()).subtract(this.getDiscountTotalValue())
                .subtract(this.getTotalTaxes().getTotalIcmsDesonerationValue());
    }

    public ApproximateTax getApproximateTaxTotalValue() {
        final BigDecimal nationalTax = items.get().stream().map(Item::getTaxStructure).map(TaxStructure::getApproximateTax).map(ApproximateTax::getNationalTax).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        final BigDecimal stateTax = items.get().stream().map(Item::getTaxStructure).map(TaxStructure::getApproximateTax).map(ApproximateTax::getStateTax).reduce(BigDecimal.ZERO, BigDecimal::add);
        final BigDecimal importTax = items.get().stream().map(Item::getTaxStructure).map(TaxStructure::getApproximateTax).map(ApproximateTax::getImportTax).reduce(BigDecimal.ZERO, BigDecimal::add);
        final BigDecimal cityTax = items.get().stream().map(Item::getTaxStructure).map(TaxStructure::getApproximateTax).map(ApproximateTax::getCityTax).reduce(BigDecimal.ZERO, BigDecimal::add);
        return ApproximateTax.builder().nationalTax(nationalTax).stateTax(stateTax).importTax(importTax).cityTax(cityTax).build();
    }

}
