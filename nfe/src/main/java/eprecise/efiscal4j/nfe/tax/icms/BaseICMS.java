
package eprecise.efiscal4j.nfe.tax.icms;

abstract class BaseICMS extends ICMS {

    static abstract class Builder {

        abstract BaseICMS build();
    }

    private final String cst;

    protected BaseICMS(String cst) {
        this.cst = cst;
    }

    public String getCST() {
        return this.cst;
    }

}
