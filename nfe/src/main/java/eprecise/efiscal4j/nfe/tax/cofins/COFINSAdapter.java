
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * 
 * @author Felipe Bueno
 * 
 */
public class COFINSAdapter extends XmlAdapter<COFINSAdapter.AdaptedCOFINS, COFINS> {

    @Override
    public COFINS unmarshal(AdaptedCOFINS v) throws Exception {
        return v.getCofins();
    }

    @Override
    public AdaptedCOFINS marshal(COFINS v) throws Exception {
        return new AdaptedCOFINS(v);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedCOFINS {

        // @formatter:off
        @XmlElements(value = { 
                @XmlElement(name = "COFINSAliq", type = COFINS01.class),
                @XmlElement(name = "COFINSAliq", type = COFINS02.class),
                @XmlElement(name = "COFINSQtde", type = COFINS03.class),
                @XmlElement(name = "COFINSNT", type = COFINS04.class),
                @XmlElement(name = "COFINSNT", type = COFINS05.class),
                @XmlElement(name = "COFINSNT", type = COFINS06.class),
                @XmlElement(name = "COFINSNT", type = COFINS07.class),
                @XmlElement(name = "COFINSNT", type = COFINS08.class),
                @XmlElement(name = "COFINSNT", type = COFINS09.class),
                @XmlElement(name = "COFINSOutr", type = COFINS49.class),
                @XmlElement(name = "COFINSOutr", type = COFINS50.class),
                @XmlElement(name = "COFINSOutr", type = COFINS51.class),
                @XmlElement(name = "COFINSOutr", type = COFINS52.class),
                @XmlElement(name = "COFINSOutr", type = COFINS53.class),
                @XmlElement(name = "COFINSOutr", type = COFINS54.class),
                @XmlElement(name = "COFINSOutr", type = COFINS55.class),
                @XmlElement(name = "COFINSOutr", type = COFINS56.class),
                @XmlElement(name = "COFINSOutr", type = COFINS60.class),
                @XmlElement(name = "COFINSOutr", type = COFINS61.class),
                @XmlElement(name = "COFINSOutr", type = COFINS62.class),
                @XmlElement(name = "COFINSOutr", type = COFINS63.class),
                @XmlElement(name = "COFINSOutr", type = COFINS64.class),
                @XmlElement(name = "COFINSOutr", type = COFINS65.class),
                @XmlElement(name = "COFINSOutr", type = COFINS66.class),
                @XmlElement(name = "COFINSOutr", type = COFINS67.class),
                @XmlElement(name = "COFINSOutr", type = COFINS70.class),
                @XmlElement(name = "COFINSOutr", type = COFINS71.class),
                @XmlElement(name = "COFINSOutr", type = COFINS72.class),
                @XmlElement(name = "COFINSOutr", type = COFINS73.class),
                @XmlElement(name = "COFINSOutr", type = COFINS74.class),
                @XmlElement(name = "COFINSOutr", type = COFINS75.class),
                @XmlElement(name = "COFINSOutr", type = COFINS98.class),
                @XmlElement(name = "COFINSOutr", type = COFINS99.class),
                }) 
        // @formatter:on
        private final COFINS cofins;

        public AdaptedCOFINS(COFINS cofins) {
            this.cofins = cofins;
        }

        public COFINS getCofins() {
            return this.cofins;
        }

    }
}
