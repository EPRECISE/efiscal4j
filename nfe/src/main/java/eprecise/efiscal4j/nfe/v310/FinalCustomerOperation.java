package eprecise.efiscal4j.nfe.v310;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(Integer.class)
public enum FinalCustomerOperation {
	
	@XmlEnumValue("0") NAO(0, "Não"),
	@XmlEnumValue("1") CONSUMIDOR_FINAL(1, "Consumidor Final");
	

    private static final long serialVersionUID = 1L;

    private final int value;

    private final String description;

    private FinalCustomerOperation(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
    
    public boolean isFinal() {
        return this.equals(CONSUMIDOR_FINAL);
    }


}
