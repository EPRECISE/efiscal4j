
package eprecise.efiscal4j.nfe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;


public class ByteArrayCompressor {

    public byte[] compress(byte[] content) throws IOException {
        if (content == null || content.length == 0) {
            return null;
        }
        final ByteArrayOutputStream obj = new ByteArrayOutputStream();
        final GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(content);
        gzip.close();
        return obj.toByteArray();

    }

}
