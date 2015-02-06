/**
 * 
 */

package eprecise.efiscal4j.cte.serviceTaker;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Tomador do Serviço Preencher com:
 * 
 * 0-Remetente;
 * 
 * 1-Expedidor;
 * 
 * 2-Recebedor;
 * 
 * 3-Destinatário;
 * 
 * 4 - Outros. Obs: Informar os dados cadastrais do tomador do serviço
 * 
 * Serão utilizadas as informações contidas no respectivo grupo, conforme
 * indicado pelo conteúdo deste campo
 * 
 * 
 * @author Carlos Gomes
 * 
 */
@XmlType
@XmlEnum(String.class)
public abstract class ServiceTaker implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final Class<SenderServiceTaker.Builder> SENDER = SenderServiceTaker.Builder.class;
    
    public static final Class<ShipperServiceTaker.Builder> SHIPPER = ShipperServiceTaker.Builder.class;
    
    public static final Class<ReceiverServiceTaker.Builder> RECEIVER = ReceiverServiceTaker.Builder.class;
    
    public static final Class<RemitteeServiceTaker.Builder> REMITTEE = RemitteeServiceTaker.Builder.class;
    
    public static final Class<OthersServiceTaker.Builder> OTHERS = OthersServiceTaker.Builder.class;
    
    public final @XmlElement(name = "toma") String value;
    
    public static class Builder {
	
	public <T extends ServiceTaker.Builder> T fromType(Class<T> type) {
	    try {
		return type.newInstance();
	    } catch (InstantiationException | IllegalAccessException e) {
		throw new RuntimeException(e);
	    }
	}
	
    }
    
    public ServiceTaker(String value) {
	this.value = value;
    }
    
    public String getValue() {
	return this.value;
    }
    
}
