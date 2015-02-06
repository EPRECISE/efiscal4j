package eprecise.efiscal4j.cte.serviceTaker;

public class SenderServiceTaker extends ServiceTaker {
    
    private static final long serialVersionUID = 1L;
    
    public static class Builder extends ServiceTaker.Builder {
	
	public SenderServiceTaker build() {
	    return new SenderServiceTaker();
	}
	
    }
    
    public SenderServiceTaker() {
	super("0");
    }
    
}
