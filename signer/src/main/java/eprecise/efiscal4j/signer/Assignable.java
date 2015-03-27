
package eprecise.efiscal4j.signer;

public interface Assignable {

    String getAsXml();

    String getSignedXml();

    void setSignedXml(String signedXml);

    String getRootTagName();

    String getAssignableTagName();

    String getIdAttributeTagName();

}
