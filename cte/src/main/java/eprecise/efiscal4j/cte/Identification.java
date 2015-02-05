package eprecise.efiscal4j.cte;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import eprecise.efiscal4j.cte.address.UF;
import eprecise.efiscal4j.cte.payment.PaymentMethod;
import eprecise.efiscal4j.cte.serviceTaker.OthersServiceTaker;
import eprecise.efiscal4j.cte.serviceTaker.ReceiverServiceTaker;
import eprecise.efiscal4j.cte.serviceTaker.RemitteeServiceTaker;
import eprecise.efiscal4j.cte.serviceTaker.SenderServiceTaker;
import eprecise.efiscal4j.cte.serviceTaker.ServiceTaker;
import eprecise.efiscal4j.cte.serviceTaker.ShipperServiceTaker;
import eprecise.efiscal4j.cte.types.CTeAccessKey;
import eprecise.efiscal4j.cte.types.CTeAccessKeyCheckDigit;
import eprecise.efiscal4j.cte.types.CTeCFOP;
import eprecise.efiscal4j.cte.types.CTeCode;
import eprecise.efiscal4j.cte.types.CTeDateAndTime;
import eprecise.efiscal4j.cte.types.CTeFormatDate;
import eprecise.efiscal4j.cte.types.CTeNumberDocument;
import eprecise.efiscal4j.cte.types.CTeSerie;
import eprecise.efiscal4j.cte.utils.ValidationBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class CTeIdentification {
    
    private final @XmlElement(name = "cUF") UF uf;
    
    private final @XmlElement(name = "cCT") @Pattern(regexp = "[0-9]{8}") String cTeCode;
    
    private final @XmlElement(name = "CFOP") @CTeCFOP String cfop;
    
    private final @XmlElement(name = "natOp") @Size(min = 1, max = 60) String operationNature;
    
    private final @XmlElement(name = "forPag") PaymentMethod paymentMethod;
    
    private final @XmlElement(name = "mod") String modelDocument = "57";
    
    private final @XmlElement(name = "serie") @CTeSerie String serie;
    
    private final @XmlElement(name = "nCT") @CTeNumberDocument String numberCte;
    
    private final @XmlElement(name = "dhEmi") @CTeDateAndTime String dateAndTimeOfEmission;
    
    private final @XmlElement(name = "tpImp") PrintFormatDACTE printFormatDacte;
    
    private final @XmlElement(name = "tpEmis") @NotNull CTeEmissionForm cteEmissionForm;
    
    private final @XmlElement(name = "cDV") @CTeAccessKeyCheckDigit String checkDigit;
    
    private final @XmlElement(name = "tpAmb") TypeEnvironment typeEnvironment;
    
    private final @XmlElement(name = "tpCTe") CTeType cteType;
    
    private final @XmlElement(name = "procEmi") IdentifierEmission identifierEmission;
    
    private final @XmlElement(name = "verProc") @Size(min = 1, max = 20) String emissionProcessVersion;
    
    private final @XmlElement(name = "refCTE") @CTeAccessKey String accessKey;
    
    private final @XmlElement(name = "cMunEnv") @CTeCode String cityCode;
    
    private final @XmlElement(name = "xMunEnv") @Size(min = 1, max = 60) String cityName;
    
    private final @XmlElement(name = "UFEnv") String ufSend;
    
    private final @XmlElement(name = "modal") Modal modal;
    
    private final @XmlElement(name = "tpServ") TypeService typeService;
    
    private final @XmlElement(name = "cMunIni") @CTeCode String codeCityBeginInstallment;
    
    private final @XmlElement(name = "xMunIni") @Size(min = 1, max = 60) String nameCityBeginInstallment;
    
    private final @XmlElement(name = "UFIni") String ufBeginInstallment;
    
    private final @XmlElement(name = "cMunFim") @CTeCode String codeEndInstallment;
    
    private final @XmlElement(name = "xMunFim") @Size(min = 1, max = 60) String nameCityEndInstallment;
    
    private final @XmlElement(name = "UFFim") String ufEndInstallment;
    
    private final @XmlElement(name = "retira") IndicatorWithdrawal indicatorWithdrawal;
    
    private final @XmlElement(name = "xDetRetira") @Size(min = 1, max = 160) String detailsRemoved;
    
    // @formatter:off
    @XmlElements(value = { 
            @XmlElement(name = "toma03", type = SenderServiceTaker.class),
            @XmlElement(name = "toma03", type = ShipperServiceTaker.class),
            @XmlElement(name = "toma03", type = ReceiverServiceTaker.class),
            @XmlElement(name = "toma03", type = RemitteeServiceTaker.class),
            @XmlElement(name = "toma4", type = OthersServiceTaker.class),
            }) 
    // @formatter:on
    private final ServiceTaker serviceTaker;
    
    private final @XmlElement(name = "dhCont") @CTeFormatDate String contingency;
    
    private final @XmlElement(name = "dhCont") @Size(min = 15, max = 256) String justificationContingency;
    
    public static class Builder {
	
	private UF uf;
	
	private String cTeCode;
	
	private String cfop;
	
	private String operationNature;
	
	private PaymentMethod paymentMethod;
	
	private String serie;
	
	private String numberCte;
	
	private String dateAndTimeOfEmission;
	
	private PrintFormatDACTE printFormatDACTE;
	
	private CTeEmissionForm cteEmissionForm;
	
	private String checkDigit;
	
	private TypeEnvironment typeEnvironment;
	
	private CTeType cTeType;
	
	private IdentifierEmission identifierEmission;
	
	private String emissionProcessVersion;
	
	private String accessKey;
	
	private String cityCode;
	
	private String cityName;
	
	private String ufSend;
	
	private Modal modal;
	
	private TypeService typeService;
	
	private String codeCityBeginInstallment;
	
	private String nameCityBeginInstallment;
	
	private String ufBeginInstallment;
	
	private String codeEndInstallment;
	
	private String nameCityEndInstallment;
	
	private String ufEndInstallment;
	
	private IndicatorWithdrawal indicatorWithdrawal;
	
	private String detailsRemoved;
	
	private String contingency;
	
	private String justificationContingency;
	
	private ServiceTaker serviceTaker;
	
	public Builder withUF(UF uf) {
	    this.uf = uf;
	    return this;
	}
	
	public Builder withCTeCode(String cTeCode) {
	    this.cTeCode = cTeCode;
	    return this;
	}
	
	public Builder withCFOP(String cfop) {
	    this.cfop = cfop;
	    return this;
	}
	
	public Builder withOperationNature(String operationNature) {
	    this.operationNature = operationNature;
	    return this;
	}
	
	public Builder withPaymentMethod(PaymentMethod paymentMethod) {
	    this.paymentMethod = paymentMethod;
	    return this;
	}
	
	public Builder withSerie(String serie) {
	    this.serie = serie;
	    return this;
	}
	
	public Builder withNumberCte(String numberCte) {
	    this.numberCte = numberCte;
	    return this;
	}
	
	public Builder withDateAndTimeOfEmission(String dateAndTimeOfEmission) {
	    this.dateAndTimeOfEmission = dateAndTimeOfEmission;
	    return this;
	}
	
	public Builder withPrintFormatDACTE(PrintFormatDACTE printFormatDACTE) {
	    this.printFormatDACTE = printFormatDACTE;
	    return this;
	}
	
	public Builder withCTeEmissionForm(CTeEmissionForm cteEmissionForm) {
	    this.cteEmissionForm = cteEmissionForm;
	    return this;
	}
	
	public Builder withCheckDigit(String checkDigit) {
	    this.checkDigit = checkDigit;
	    return this;
	}
	
	public Builder withTypeEnvironment(TypeEnvironment typeEnvironment) {
	    this.typeEnvironment = typeEnvironment;
	    return this;
	}
	
	public Builder withCTeType(CTeType cTeType) {
	    this.cTeType = cTeType;
	    return this;
	}
	
	public Builder withIdentifierEmission(IdentifierEmission identifierEmission) {
	    this.identifierEmission = identifierEmission;
	    return this;
	}
	
	public Builder withEmissionProcessVersion(String emissionProcessVersion) {
	    this.emissionProcessVersion = emissionProcessVersion;
	    return this;
	}
	
	public Builder withAccessKey(String accessKey) {
	    this.accessKey = accessKey;
	    return this;
	}
	
	public Builder withCityCode(String cityCode) {
	    this.cityCode = cityCode;
	    return this;
	}
	
	public Builder withCountyName(String cityName) {
	    this.cityName = cityName;
	    return this;
	}
	
	public Builder withUfSend(UF uf) {
	    this.ufSend = uf.getAcronym();
	    return this;
	}
	
	public Builder withModal(Modal modal) {
	    this.modal = modal;
	    return this;
	}
	
	public Builder withTypeService(TypeService typeService) {
	    this.typeService = typeService;
	    return this;
	}
	
	public Builder withCodeCityBeginInstallment(String codeCityBeginInstallment) {
	    this.codeCityBeginInstallment = codeCityBeginInstallment;
	    return this;
	}
	
	public Builder withNameCityBeginInstallment(String nameCityBeginInstallment) {
	    this.nameCityBeginInstallment = nameCityBeginInstallment;
	    return this;
	}
	
	public Builder withUfBeginInstallment(UF uf) {
	    this.ufBeginInstallment = uf.getAcronym();
	    return this;
	}
	
	public Builder withCodeEndInstallment(String codeEndInstallment) {
	    this.codeEndInstallment = codeEndInstallment;
	    return this;
	}
	
	public Builder withNameCityEndInstallment(String nameCityEndInstallment) {
	    this.nameCityEndInstallment = nameCityEndInstallment;
	    return this;
	}
	
	public Builder withUfEndInstallment(UF uf) {
	    this.ufEndInstallment = uf.getAcronym();
	    return this;
	}
	
	public Builder withIndicatorWithdrawal(IndicatorWithdrawal indicatorWithdrawal) {
	    this.indicatorWithdrawal = indicatorWithdrawal;
	    return this;
	}
	
	public Builder withDetailsRemoved(String detailsRemoved) {
	    this.detailsRemoved = detailsRemoved;
	    return this;
	}
	
	public Builder withContingency(String contingency) {
	    this.contingency = contingency;
	    return this;
	}
	
	public Builder withJustificationContingency(String justificationContingency) {
	    this.justificationContingency = justificationContingency;
	    return this;
	}
	
	public Builder withServiceTaker(ServiceTaker serviceTaker) {
	    this.serviceTaker = serviceTaker;
	    return this;
	}
	
	public CTeIdentification build() {
	    final CTeIdentification entity = new CTeIdentification(this);
	    ValidationBuilder.from(entity).validate().throwIfViolate();
	    return entity;
	}
    }
    
    public CTeIdentification() {
	this.uf = null;
	this.cTeCode = null;
	this.cfop = null;
	this.operationNature = null;
	this.paymentMethod = null;
	this.serie = null;
	this.numberCte = null;
	this.dateAndTimeOfEmission = null;
	this.printFormatDacte = null;
	this.cteEmissionForm = null;
	this.checkDigit = null;
	this.typeEnvironment = null;
	this.cteType = null;
	this.identifierEmission = null;
	this.emissionProcessVersion = null;
	this.accessKey = null;
	this.cityCode = null;
	this.cityName = null;
	this.ufSend = null;
	this.modal = null;
	this.typeService = null;
	this.codeCityBeginInstallment = null;
	this.nameCityBeginInstallment = null;
	this.ufBeginInstallment = null;
	this.codeEndInstallment = null;
	this.nameCityEndInstallment = null;
	this.ufEndInstallment = null;
	this.indicatorWithdrawal = null;
	this.detailsRemoved = null;
	this.contingency = null;
	this.justificationContingency = null;
	this.serviceTaker = null;
    }
    
    public CTeIdentification(Builder builder) {
	this.uf = builder.uf;
	this.cTeCode = builder.cTeCode;
	this.cfop = builder.cfop;
	this.operationNature = builder.operationNature;
	this.paymentMethod = builder.paymentMethod;
	this.serie = builder.serie;
	this.numberCte = builder.numberCte;
	this.dateAndTimeOfEmission = builder.dateAndTimeOfEmission;
	this.printFormatDacte = builder.printFormatDACTE;
	this.cteEmissionForm = builder.cteEmissionForm;
	this.checkDigit = builder.checkDigit;
	this.typeEnvironment = builder.typeEnvironment;
	this.cteType = builder.cTeType;
	this.identifierEmission = builder.identifierEmission;
	this.emissionProcessVersion = builder.emissionProcessVersion;
	this.accessKey = builder.accessKey;
	this.cityCode = builder.cityCode;
	this.cityName = builder.cityName;
	this.ufSend = builder.ufSend;
	this.modal = builder.modal;
	this.typeService = builder.typeService;
	this.codeCityBeginInstallment = builder.codeCityBeginInstallment;
	this.nameCityBeginInstallment = builder.nameCityBeginInstallment;
	this.ufBeginInstallment = builder.ufBeginInstallment;
	this.codeEndInstallment = builder.codeEndInstallment;
	this.nameCityEndInstallment = builder.nameCityEndInstallment;
	this.ufEndInstallment = builder.ufEndInstallment;
	this.indicatorWithdrawal = builder.indicatorWithdrawal;
	this.detailsRemoved = builder.detailsRemoved;
	this.contingency = builder.contingency;
	this.justificationContingency = builder.justificationContingency;
	this.serviceTaker = builder.serviceTaker;
    }
    
    public UF getUf() {
	return this.uf;
    }
    
    public String getCTeCode() {
	return this.cTeCode;
    }
    
    public String getCfop() {
	return this.cfop;
    }
    
    public String getOperationNature() {
	return this.operationNature;
    }
    
    public PaymentMethod getPaymentMethod() {
	return this.paymentMethod;
    }
    
    public String getModelDocument() {
	return this.modelDocument;
    }
    
    public String getSerie() {
	return this.serie;
    }
    
    public String getNumberCte() {
	return this.numberCte;
    }
    
    public String getDateAndTimeOfEmission() {
	return this.dateAndTimeOfEmission;
    }
    
    public PrintFormatDACTE getPrintFormatDACTE() {
	return this.printFormatDacte;
    }
    
    public CTeEmissionForm getCteEmissionForm() {
	return this.cteEmissionForm;
    }
    
    public String getCheckDigit() {
	return this.checkDigit;
    }
    
    public TypeEnvironment getTypeEnvironment() {
	return this.typeEnvironment;
    }
    
    public CTeType getCteType() {
	return this.cteType;
    }
    
    public IdentifierEmission getIdentifierEmission() {
	return this.identifierEmission;
    }
    
    public String getEmissionProcessVersion() {
	return this.emissionProcessVersion;
    }
    
    public String getAccessKey() {
	return this.accessKey;
    }
    
    public String getCityCode() {
	return this.cityCode;
    }
    
    public String getCityName() {
	return this.cityName;
    }
    
    public UF getUfSend() {
	return this.ufSend == null || this.ufSend.isEmpty() ? null : UF.valueOf(this.ufSend);
    }
    
    public Modal getModal() {
	return this.modal;
    }
    
    public TypeService getTypeService() {
	return this.typeService;
    }
    
    public String getCodeCityBeginInstallment() {
	return this.codeCityBeginInstallment;
    }
    
    public String getNameCityBeginInstallment() {
	return this.nameCityBeginInstallment;
    }
    
    public UF getUfBeginInstallment() {
	return this.ufBeginInstallment == null || this.ufBeginInstallment.isEmpty() ? null : UF.valueOf(this.ufBeginInstallment);
    }
    
    public String getCodeEndInstallment() {
	return this.codeEndInstallment;
    }
    
    public String getNameCityEndInstallment() {
	return this.nameCityEndInstallment;
    }
    
    public UF getUfEndInstallment() {
	return this.ufEndInstallment == null || this.ufEndInstallment.isEmpty() ? null : UF.valueOf(this.ufEndInstallment);
    }
    
    public IndicatorWithdrawal getIndicatorWithdrawal() {
	return this.indicatorWithdrawal;
    }
    
    public String getDetailsRemoved() {
	return this.detailsRemoved;
    }
    
    public String getContingency() {
	return this.contingency;
    }
    
    public String getJustificationContingency() {
	return this.justificationContingency;
    }
    
    public ServiceTaker getServiceTaker() {
	return this.serviceTaker;
    }
}
