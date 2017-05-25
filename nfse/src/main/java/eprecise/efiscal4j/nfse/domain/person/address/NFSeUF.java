
package eprecise.efiscal4j.nfse.domain.person.address;

import java.io.Serializable;


public enum NFSeUF implements Serializable {
    AC("12", "Acre"),
    AL("27", "Alagoas"),
    AM("13", "Amazonas"),
    AP("16", "Amapa"),
    BA("29", "Bahia"),
    CE("23", "Ceara"),
    DF("53", "Distrito Federal"),
    ES("32", "Espirito Santo"),
    GO("52", "Goias"),
    MA("21", "Maranhao"),
    MG("31", "Minas Gerais"),
    MT("51", "Mato Grosso"),
    MS("50", "Mato Grosso do Sul"),
    PA("15", "Para"),
    PB("25", "Paraiba"),
    PE("26", "Pernambuco"),
    PI("22", "Piaui"),
    PR("41", "Parana"),
    RJ("33", "Rio de Janeiro"),
    RN("24", "Rio Grande do Norte"),
    RO("11", "Rondonia"),
    RR("14", "Roraima"),
    RS("43", "Rio Grande do Sul"),
    SC("42", "Santa Catarina"),
    SE("28", "Sergipe"),
    SP("35", "Sao Paulo"),
    TO("17", "Tocantins");

    private static final long serialVersionUID = 1L;

    private String ibgeCode;

    private String description;

    private NFSeUF(String ibgeCode, String description) {
        this.ibgeCode = ibgeCode;
        this.description = description;
    }

    public String getIbgeCode() {
        return this.ibgeCode;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAcronym() {
        return this.toString();
    }

    public static NFSeUF findByAcronym(String acronym) {
        for (final NFSeUF uf : values()) {
            if (uf.getAcronym().equalsIgnoreCase(acronym)) {
                return uf;
            }
        }
        return null;
    }

}
