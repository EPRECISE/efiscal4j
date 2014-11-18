
package eprecise.efiscal4j.nfe.tax.icms;

abstract class BaseICMSSN202 extends BaseICMSSN {

    static abstract class Builder extends BaseICMSSN.Builder {

        @Override
        abstract BaseICMSSN202 build();
    }

    protected BaseICMSSN202(String cst) {
        super(cst);
    }

}
