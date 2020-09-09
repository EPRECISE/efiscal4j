
package eprecise.efiscal4j.nfe.v400.item.export;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Detalhe da exportação
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeItemExportDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nDraw") @NotNull @Pattern(regexp = "[0-9]{0,11}") final String drawbackNumber;

    private @XmlElement(name = "exportInd") final NFeItemIndirectExport indirectExport;

    public static class Builder {

        private String drawbackNumber;

        private NFeItemIndirectExport indirectExport;

        /**
         * Número do ato concessório de Drawback
         * 
         * @param drawbackNumber
         * @return
         */
        public Builder withDrawbackNumber(String drawbackNumber) {
            this.drawbackNumber = drawbackNumber;
            return this;
        }

        /**
         * @param indirectExport
         * @see NFeItemIndirectExport
         * @return
         */
        public Builder withIndirectExport(NFeItemIndirectExport indirectExport) {
            this.indirectExport = indirectExport;
            return this;
        }

        public NFeItemExportDetail build() {
            final NFeItemExportDetail entity = new NFeItemExportDetail(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeItemExportDetail() {
        this.drawbackNumber = null;
        this.indirectExport = null;
    }

    public NFeItemExportDetail(final Builder builder) {
        this.drawbackNumber = builder.drawbackNumber;
        this.indirectExport = builder.indirectExport;
    }

    public String getDrawbackNumber() {
        return drawbackNumber;
    }

    public NFeItemIndirectExport getIndirectExport() {
        return indirectExport;
    }

}
