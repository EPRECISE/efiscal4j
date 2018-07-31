
package eprecise.efiscal4j.nfe.total.tax;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Supplier;

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
        return this.taxes.get().stream().filter(IcmsWithBcValueHolder.class::isInstance).map(IcmsWithBcValueHolder.class::cast).map(IcmsWithBcValueHolder::getIcmsWithBcValue)
                .map(IcmsWithBcValue::getCalculationBasis).map(IcmsBc::getCalculationBasis).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsValue() {
        return this.taxes.get().stream().filter(IcmsWithBcValueHolder.class::isInstance).map(IcmsWithBcValueHolder.class::cast).map(IcmsWithBcValueHolder::getIcmsWithBcValue)
                .map(IcmsWithBcValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalICMSUFReceiverFcpValue() {
        return this.taxes.get().stream().filter(ICMSUFReceiver.class::isInstance).map(ICMSUFReceiver.class::cast).map(ICMSUFReceiver::getFcpValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsUfReceiverShareValue() {
        return this.taxes.get().stream().filter(ICMSUFReceiver.class::isInstance).map(ICMSUFReceiver.class::cast).map(ICMSUFReceiver::getShareValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsUfReceiverEmitterShareValue() {
        return this.taxes.get().stream().filter(ICMSUFReceiver.class::isInstance).map(ICMSUFReceiver.class::cast).map(ICMSUFReceiver::getEmitterShareValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsDesonerationValue() {
        return this.taxes.get().stream().filter(IcmsDesonerationHolder.class::isInstance).map(IcmsDesonerationHolder.class::cast).map(IcmsDesonerationHolder::getDesoneration)
                .map(IcmsDesoneration::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsStBcValue() {
        return this.taxes.get().stream().filter(IcmsStWithBcValueHolder.class::isInstance).map(IcmsStWithBcValueHolder.class::cast).map(IcmsStWithBcValueHolder::getIcmsStWithBcValue)
                .map(IcmsStWithBcValue::getCalculationBasis).map(IcmsStBc::getCalculationBasis).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIcmsStValue() {
        return this.taxes.get().stream().filter(IcmsStWithBcValueHolder.class::isInstance).map(IcmsStWithBcValueHolder.class::cast).map(IcmsStWithBcValueHolder::getIcmsStWithBcValue)
                .map(IcmsStWithBcValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIIValue() {
        return this.taxes.get().stream().filter(II.class::isInstance).map(II.class::cast).map(II::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIPIValue() {
        return this.taxes.get().stream().filter(IpiValueHolder.class::isInstance).map(IpiValueHolder.class::cast).map(IpiValueHolder::getValue).map(IpiValue::getValue).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

    public BigDecimal getTotalPisValue() {
        return this.taxes.get().stream().filter(PISTrib.class::isInstance).map(PISTrib.class::cast).map(PISTrib::getPis).map(PisValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCofinsValue() {
        return this.taxes.get().stream().filter(COFINSTrib.class::isInstance).map(COFINSTrib.class::cast).map(COFINSTrib::getCofins).map(CofinsValue::getValue).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

    public BigDecimal getTotalFcpValue() {
        return this.taxes.get().stream().filter(IcmsWithFcpValueHolder.class::isInstance).map(IcmsWithFcpValueHolder.class::cast).map(IcmsWithFcpValueHolder::getFcpValue).map(FcpValue::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalFcpStValue() {
        return this.taxes.get().stream().filter(IcmsWithFcpStValueHolder.class::isInstance).map(IcmsWithFcpStValueHolder.class::cast).map(IcmsWithFcpStValueHolder::getFcpStValue)
                .map(FcpStValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalFcpStRetainedValue() {
        return this.taxes.get().stream().filter(IcmsWithFcpStRetainedValueHolder.class::isInstance).map(IcmsWithFcpStRetainedValueHolder.class::cast)
                .map(IcmsWithFcpStRetainedValueHolder::getFcpStRetainedValue).map(FcpStRetainedValue::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalReturnedIpiValue() {
        return BigDecimal.ZERO; // TODO
    }

}
