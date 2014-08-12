
package eprecise.efiscal4j.nfe;

import java.io.Serializable;


public enum UF implements Serializable {
    AC("Acre"),
    AL("Alagoas"),
    AM("Amazonas"),
    AP("Amapa"),
    BA("Bahia"),
    CE("Ceara"),
    DF("Distrito Federal"),
    ES("Espirito Santo"),
    GO("Goias"),
    MA("Maranhao"),
    MG("Minas Gerais"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    PA("Para"),
    PB("Paraiba"),
    PE("Pernambuco"),
    PI("Piaui"),
    PR("Parana"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RO("Rondonia"),
    RR("Roraima"),
    RS("Rio Grande do Sul"),
    SC("Santa Catarina"),
    SE("Sergipe"),
    SP("Sao Paulo"),
    TO("Tocantins");

    private static final long serialVersionUID = 1L;

    private String description;

    private UF(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
