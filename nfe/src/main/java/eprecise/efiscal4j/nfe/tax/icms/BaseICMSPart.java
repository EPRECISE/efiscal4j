
package eprecise.efiscal4j.nfe.tax.icms;

abstract class BaseICMSPart extends BaseICMS {

    static abstract class Builder extends BaseICMS.Builder {

        @Override
        abstract BaseICMSPart build();
    }

    protected BaseICMSPart(String cst) {
        super(cst);
    }

}
