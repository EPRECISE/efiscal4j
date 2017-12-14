
package eprecise.efiscal4j.nfe.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;


public class ByteArrayDecompressor {

    public String decompress(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        final GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        final BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        final StringBuilder outputBuilder = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            outputBuilder.append(line);
        }
        return outputBuilder.toString();
    }

}
