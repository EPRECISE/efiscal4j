
package eprecise.efiscal4j.nfse.ts.govbr.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum GovbrVersion {
                          VERSION_1_00("1.00"),
                          VERSION_2_03("2.03");

    private @Getter final String version;

}
