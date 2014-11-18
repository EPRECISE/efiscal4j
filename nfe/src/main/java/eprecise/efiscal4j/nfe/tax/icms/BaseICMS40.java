
package eprecise.efiscal4j.nfe.tax.icms;

abstract class BaseICMS40 extends BaseICMS {

    static abstract class Builder extends BaseICMS.Builder {

        @Override
        abstract BaseICMS40 build();
    }

    protected BaseICMS40(String cst) {
        super(cst);
    }

}
