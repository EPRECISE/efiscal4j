package eprecise.efiscal4j.cte.serviceTaker;

public class RemitteeServiceTaker extends ServiceTaker {
    
    private static final long serialVersionUID = 1L;
    
    public static class Builder extends ServiceTaker.Builder {
	
	@Override
	public RemitteeServiceTaker build() {
	    return new RemitteeServiceTaker();
	}
	
    }
    
    public RemitteeServiceTaker() {
	super("3");
    }
}
