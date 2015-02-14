package eprecise.efiscal4j.cte.person;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractDocuments implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected abstract static class Builder<T extends Builder<?, ?>, J extends Person.Builder<?>> {
	protected final J personBuilder;
	
	public Builder(J personBuilder) {
	    this.personBuilder = personBuilder;
	}
	
	public abstract J ok();
	
    }
    
    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
