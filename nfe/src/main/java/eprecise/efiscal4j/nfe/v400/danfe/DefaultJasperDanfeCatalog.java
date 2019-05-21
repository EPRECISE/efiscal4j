
package eprecise.efiscal4j.nfe.v400.danfe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import eprecise.efiscal4j.nfe.danfe.JasperDanfeCatalog;
import eprecise.efiscal4j.nfe.v400.DANFEPrintFormat;
import eprecise.efiscal4j.nfe.version.DanfePrintFormatVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


public class DefaultJasperDanfeCatalog implements JasperDanfeCatalog {

    private final Map<DANFEPrintFormat, JasperFiles> danfeMap = new HashMap<DANFEPrintFormat, JasperFiles>() {

        // @formatter:off
        private static final long serialVersionUID = 1L;
        {
            this.put(DANFEPrintFormat.SEM_DANFE, this.buildDanfePaisagem());
            this.put(DANFEPrintFormat.DANFE_SIMPLIFICADO, this.buildDanfePaisagem());
            this.put(DANFEPrintFormat.DANFE_RETRATO, this.buildDanfeRetrato());
            this.put(DANFEPrintFormat.DANFE_PAISAGEM, this.buildDanfePaisagem());
            this.put(DANFEPrintFormat.DANFE_NFCE, this.buildDanfeNfce());
            this.put(DANFEPrintFormat.DANFE_NFCE_MENSAGEM_ELETRONICA, this.buildDanfeNfce());
        }

        private JasperFiles buildDanfeRetrato() {
            return JasperFiles.builder()
                    .master("eprecise/efiscal4j/nfe/v400/danfe/retrato/danfe_retrato.jasper")
                    .details(Arrays.asList(
                                JasperDetail.builder().paramName("danfe_retrato_detalhes").filePath("eprecise/efiscal4j/nfe/v400/danfe/retrato/danfe_retrato_detalhes.jasper").build(),
                                JasperDetail.builder().paramName("danfe_retrato_obscont").filePath("eprecise/efiscal4j/nfe/v400/danfe/retrato/danfe_retrato_obscont.jasper").build()
                            ))
                .build();
        }

        private JasperFiles buildDanfePaisagem() {
            return JasperFiles.builder()
                    .master("eprecise/efiscal4j/nfe/v400/danfe/paisagem/danfe_paisagem.jasper")
                    .details(Arrays.asList(
                                JasperDetail.builder().paramName("danfe_paisagem_detalhes").filePath("eprecise/efiscal4j/nfe/v400/danfe/paisagem/danfe_paisagem_detalhes.jasper").build(),
                                JasperDetail.builder().paramName("danfe_paisagem_duplicatas").filePath("eprecise/efiscal4j/nfe/v400/danfe/paisagem/danfe_paisagem_duplicatas.jasper").build(),
                                JasperDetail.builder().paramName("danfe_paisagem_obscont").filePath("eprecise/efiscal4j/nfe/v400/danfe/paisagem/danfe_paisagem_obscont.jasper").build()
                            ))
                .build();
        }

        private JasperFiles buildDanfeNfce() {
            return JasperFiles.builder()
                    .master("eprecise/efiscal4j/nfe/v400/nfce/danfe/danfe_nfce.jasper")
                    .details(Arrays.asList(
                                JasperDetail.builder().paramName("danfe_nfce_detalhes").filePath("eprecise/efiscal4j/nfe/v400/nfce/danfe/danfe_nfce_detalhes.jasper").build(),
                                JasperDetail.builder().paramName("danfe_nfce_pagamentos").filePath("eprecise/efiscal4j/nfe/v400/nfce/danfe/danfe_nfce_pagamentos.jasper").build()
                            ))
                .build();
        }

        // @formatter:on
    };

    @Override
    public InputStream get(final DanfePrintFormatVersion printFormat) {
        if (!this.danfeMap.containsKey(printFormat)) {
            throw new IllegalArgumentException("Não existe formato padrão para " + printFormat);
        }
        return this.danfeMap.get(printFormat == DANFEPrintFormat.DANFE_RETRATO ? DANFEPrintFormat.DANFE_PAISAGEM : printFormat).getMaster();
    }

    @Override
    public Map<String, InputStream> getDetails(final DanfePrintFormatVersion printFormat) {
        if (!this.danfeMap.containsKey(printFormat)) {
            throw new IllegalArgumentException("Não existe formato padrão para " + printFormat);
        }
        return this.danfeMap.get(printFormat == DANFEPrintFormat.DANFE_RETRATO ? DANFEPrintFormat.DANFE_PAISAGEM : printFormat).getDetails();
    }

    @Builder
    @AllArgsConstructor
    public static class JasperFiles {

        private final String master;

        private @Builder.Default final Collection<JasperDetail> details = new HashSet<>();

        public InputStream getMaster() {
            return getInputStream(this.master);
        }

        public Map<String, InputStream> getDetails() {
            final Map<String, InputStream> params = new HashMap<>();
            this.details.forEach(detail -> {
                params.put(detail.getParamName(), getInputStream(detail.getFilePath()));
            });
            return params;
        }

    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class JasperDetail {

        private final String paramName;

        private final String filePath;
    }

    public static InputStream getInputStream(final String file) {
        try {
            final URL resource = DefaultJasperDanfeCatalog.class.getClassLoader().getResource(file);
            if (resource != null) {
                return resource.openStream();
            } else {
                throw new FileNotFoundException(String.format("Arquivo %s de DefaultJasperDanfeCatalog não foi encontrado", file));
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
