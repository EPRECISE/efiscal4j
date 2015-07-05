
package eprecise.efiscal4j.nfe;

import static eprecise.efiscal4j.commons.domain.FiscalDocumentModel.NFCE;
import static eprecise.efiscal4j.commons.domain.FiscalDocumentModel.NFE;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;


@XmlType
@XmlEnum(String.class)
public enum DANFEPrintFormat {
    @XmlEnumValue("0") SEM_DANFE("0", "Sem DANFe", NFE),
    @XmlEnumValue("1") DANFE_RETRATO("1", "DANFe Retrato", NFE),
    @XmlEnumValue("2") DANFE_PAISAGEM("2", "DANFe Paisagem", NFE),
    @XmlEnumValue("3") DANFE_SIMPLIFICADO("3", "DANFe Simplificado", NFE),
    @XmlEnumValue("4") DANFE_NFCE("4", "DANFe NFC-e", NFCE),
    @XmlEnumValue("5") DANFE_NFCE_MENSAGEM_ELETRONICA("5", "DANFe NFC-e em mensagem eletrônica", NFCE);

    private static final long serialVersionUID = 1L;

    private final String value;

    private final String description;

    private final Collection<FiscalDocumentModel> models;

    private DANFEPrintFormat(String value, String description, FiscalDocumentModel... models) {
        this.value = value;
        this.description = description;
        this.models = Arrays.asList(models);
    }

    public boolean isAppliableTo(FiscalDocumentModel model) {
        return this.models.contains(model);
    }

    public Collection<FiscalDocumentModel> getModels() {
        return this.models;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static DANFEPrintFormat[] valuesBy(FiscalDocumentModel model) {
        final List<DANFEPrintFormat> collect = Stream.of(values()).filter(e -> e.isAppliableTo(model)).collect(Collectors.toList());
        return collect.toArray(new DANFEPrintFormat[collect.size()]);
    }

}
