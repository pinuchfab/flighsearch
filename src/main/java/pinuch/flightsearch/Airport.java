package pinuch.flightsearch;

/**
 * Airport class.
 * 
 * @author ukedfabien
 *
 */
public class Airport 
{
	private final String IATACode;
	private final String name;
	
	public Airport(String IATACode, String name)
	{
		this.IATACode = IATACode;
		this.name = name;
	}

	public String getIATACode()
	{
		return IATACode;
	}
	
	public String getName()
	{
		return name;
	}
}
