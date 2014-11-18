
package eprecise.efiscal4j.nfe.tax.icms;

abstract class BaseICMSSN102 extends BaseICMSSN {

    static abstract class Builder extends BaseICMSSN.Builder {

        @Override
        abstract BaseICMSSN102 build();
    }

    protected BaseICMSSN102(String cst) {
        super(cst);
    }

}
