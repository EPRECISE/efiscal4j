
package eprecise.efiscal4j.nfe.total.tax;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import eprecise.efiscal4j.nfe.item.tax.ItemTax;
import eprecise.efiscal4j.nfe.item.tax.cofins.COFINSTrib;
import eprecise.efiscal4j.nfe.item.tax.cofins.aliquot.value.CofinsValue;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration;
import eprecise.efiscal4j.nfe.item.tax.icms.desoneration.IcmsDesoneration.IcmsDesonerationHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue;
import eprecise.efiscal4j.nfe.item.tax.icms.fcp.value.FcpValue.IcmsWithFcpValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.FcpStValue.IcmsWithFcpStValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.fcp.value.retained.FcpStRetainedValue.IcmsWithFcpStRetainedValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.IcmsStWithBcValue.IcmsStWithBcValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.st.value.bc.IcmsStBc;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue;
import eprecise.efiscal4j.nfe.item.tax.icms.value.IcmsWithBcValue.IcmsWithBcValueHolder;
import eprecise.efiscal4j.nfe.item.tax.icms.value.bc.IcmsBc;
import eprecise.efiscal4j.nfe.item.tax.ii.II;
import eprecise.efiscal4j.nfe.item.tax.ipi.value.IpiValue;
import eprecise.efiscal4j.nfe.item.tax.ipi.value.IpiValue.IpiValueHolder;
import eprecise.efiscal4j.nfe.item.tax.pis.PISTrib;
import eprecise.efiscal4j.nfe.item.tax.pis.aliquot.value.PisValue;
import eprecise.efiscal4j.nfe.item.tax.ufReceiverIcms.ICMSUFReceiver;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class TotalTaxes {

    private final Supplier<Collection<ItemTax>> taxes;

    public BigDecimal getTotalIcmsBcValue() {
        return this.getTaxes().stream().filter(IcmsWithBcValueHolder.class::isInstance).map(IcmsWithBcValueHolder.class::cast).map(IcmsWithBcValueHolder::getIcmsWithBcValue).filter(Objects::nonNull)
                .map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsValue() {
        return this.getTaxes().stream().filter(IcmsWithBcValueHolder.class::isInstance).map(IcmsWithBcValueHolder.class::cast).map(IcmsWithBcValueHolder::getIcmsWithBcValue).filter(Objects::nonNull)
                .map(IcmsWithBcValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalICMSUFReceiverFcpValue() {
        return this.getTaxes().stream().filter(ICMSUFReceiver.class::isInstance).map(ICMSUFReceiver.class::cast).map(ICMSUFReceiver::getFcpValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsUfReceiverShareValue() {
        return this.getTaxes().stream().filter(ICMSUFReceiver.class::isInstance).map(ICMSUFReceiver.class::cast).map(ICMSUFReceiver::getShareValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsUfReceiverEmitterShareValue() {
        return this.getTaxes().stream().filter(ICMSUFReceiver.class::isInstance).map(ICMSUFReceiver.class::cast).map(ICMSUFReceiver::getEmitterShareValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsDesonerationValue() {
        return this.getTaxes().stream().filter(IcmsDesonerationHolder.class::isInstance).map(IcmsDesonerationHolder.class::cast).map(IcmsDesonerationHolder::getDesoneration).filter(Objects::nonNull)
                .map(IcmsDesoneration::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsStBcValue() {
        return this.getTaxes().stream().filter(IcmsStWithBcValueHolder.class::isInstance).map(IcmsStWithBcValueHolder.class::cast).map(IcmsStWithBcValueHolder::getIcmsStWithBcValue)
                .filter(Objects::nonNull).map(IcmsStWithBcValue::getCalculationBasis).filter(Objects::nonNull).map(IcmsStBc::getCalculationBasis).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsStValue() {
        return this.getTaxes().stream().filter(IcmsStWithBcValueHolder.class::isInstance).map(IcmsStWithBcValueHolder.class::cast).map(IcmsStWithBcValueHolder::getIcmsStWithBcValue)
                .filter(Objects::nonNull).map(IcmsStWithBcValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIIValue() {
        return this.getTaxes().stream().filter(II.class::isInstance).map(II.class::cast).map(II::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIPIValue() {
        return this.getTaxes().stream().filter(IpiValueHolder.class::isInstance).map(IpiValueHolder.class::cast).map(IpiValueHolder::getValue).filter(Objects::nonNull).map(IpiValue::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPisValue() {
        return this.getTaxes().stream().filter(PISTrib.class::isInstance).map(PISTrib.class::cast).map(PISTrib::getPis).filter(Objects::nonNull).map(PisValue::getValue).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

    public BigDecimal getTotalCofinsValue() {
        return this.getTaxes().stream().filter(COFINSTrib.class::isInstance).map(COFINSTrib.class::cast).map(COFINSTrib::getCofins).filter(Objects::nonNull).map(CofinsValue::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalFcpValue() {
        return this.getTaxes().stream().filter(IcmsWithFcpValueHolder.class::isInstance).map(IcmsWithFcpValueHolder.class::cast).map(IcmsWithFcpValueHolder::getFcpValue).filter(Objects::nonNull)
                .map(FcpValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalFcpStValue() {
        return this.getTaxes().stream().filter(IcmsWithFcpStValueHolder.class::isInstance).map(IcmsWithFcpStValueHolder.class::cast).map(IcmsWithFcpStValueHolder::getFcpStValue)
                .filter(Objects::nonNull).map(FcpStValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalFcpStRetainedValue() {
        return this.getTaxes().stream().filter(IcmsWithFcpStRetainedValueHolder.class::isInstance).map(IcmsWithFcpStRetainedValueHolder.class::cast)
                .map(IcmsWithFcpStRetainedValueHolder::getFcpStRetainedValue).filter(Objects::nonNull).map(FcpStRetainedValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalReturnedIpiValue() {
        return BigDecimal.ZERO; // TODO
    }

    private Collection<ItemTax> getTaxes() {
        return this.taxes.get().stream().collect(Collectors.toSet());
    }

}
