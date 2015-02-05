package eprecise.efiscal4j.cte.serviceTaker;

public class ShipperServiceTaker extends ServiceTaker {
    
    private static final long serialVersionUID = 1L;
    
    public static class Builder extends ServiceTaker.Builder {
	
	@Override
	public ShipperServiceTaker build() {
	    return new ShipperServiceTaker();
	}
	
    }
    
    public ShipperServiceTaker() {
	super("1");
    }
}
