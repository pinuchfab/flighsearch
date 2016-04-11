package pinuch.flightssearch;

/**
 * SearchResult class.
 * 
 * Contains a flight and its price.
 * 
 * @author ukedfabien
 *
 */
public class SearchResult 
{
	private final Flight flight;
	private final Price price;
	
	SearchResult(Flight flight, Price price)
	{
		this.flight = flight;
		this.price = price;
	}
	
	public Price getPrice()
	{
		return price;
	}
	
	public Flight getFlight()
	{
		return flight;
	}
	
	// e.g: "*	TK2372, 157.6 Euro (€)".
	public String toString()
	{
		return "*\t" + flight.toString() + ", " + price.toString();
	}
	
	@Override 
	public boolean equals(Object o) 
	{
		// Well, ideally we should override getHash().
		// But for now just compare the strings.
	    return 	(o instanceof SearchResult) && (this.toString().equals(((SearchResult)o).toString()));
	}
}
