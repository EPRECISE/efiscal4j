
package eprecise.efiscal4j.signer;

public interface Assignable {

    String getAsXml();

    Assignable getAsEntity(String xml);

}
