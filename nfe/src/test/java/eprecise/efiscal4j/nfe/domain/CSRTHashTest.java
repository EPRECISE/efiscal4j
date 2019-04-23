package eprecise.efiscal4j.nfe.domain;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.nfe.technicalManager.CSRT;

public class CSRTHashTest {
    
    @Test
    public void testCsrtHash() {
        
        final CSRT csrt = CSRT.builder()
                .id("01")
                .key("G8063VRTNDMO886SFNK5LDUDEI24XJ22YIPO")
                .build();
        final String csrtHash = csrt.getHash("41180678393592000146558900000006041028190697");
        
        Assert.assertEquals("aWv6LeEM4X6u4+qBI2OYZ8grigw=", csrtHash);
    }

}
