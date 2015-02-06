package eprecise.efiscal4j.cte.serviceTaker;

public class ReceiverServiceTaker extends ServiceTaker {
    
    private static final long serialVersionUID = 1L;
    
    public static class Builder extends ServiceTaker.Builder {
	
	public ReceiverServiceTaker build() {
	    return new ReceiverServiceTaker();
	}
	
    }
    
    public ReceiverServiceTaker() {
	super("2");
    }
}
