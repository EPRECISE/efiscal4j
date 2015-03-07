
package eprecise.efiscal4j.nfe.tax.icms;

/**
 * Classe base para os ICMS com CSOSN 102, 103, 300 e 400
 * 
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
abstract class BaseICMSSN102 extends BaseICMSSN {

    private static final long serialVersionUID = 1L;

    static abstract class Builder extends BaseICMSSN.Builder {

        @Override
        abstract BaseICMSSN102 build();
    }

    protected BaseICMSSN102() {
        super(null, null);
    }

    protected BaseICMSSN102(Builder builder, String csosn) {
        super(builder.origin, csosn);
    }

}
