
package eprecise.efiscal4j.nfe.tax.icms;

abstract class BaseICMSSN extends ICMS {

    static abstract class Builder {

        abstract BaseICMSSN build();
    }

    private final String csosn;

    protected BaseICMSSN(String csosn) {
        this.csosn = csosn;
    }

    public String getCSOSN() {
        return this.csosn;
    }

}
