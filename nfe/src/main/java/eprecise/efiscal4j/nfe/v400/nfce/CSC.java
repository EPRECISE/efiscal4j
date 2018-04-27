
package eprecise.efiscal4j.nfe.v400.nfce;

import java.io.Serializable;


/**
 * Código de Segurança do Contribuinte
 *
 * @author Fernando Glizt
 *
 */
public class CSC implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String cldToken;

    private final String cscValue;

    public CSC() {
        this.cldToken = null;
        this.cscValue = null;
    }

    public CSC(String cldToken, String cscValue) {
        this.cldToken = cldToken;
        this.cscValue = cscValue;
    }

    public String getCscValue() {
        return this.cscValue;
    }

    public String getCldToken() {
        return this.cldToken;
    }

}
