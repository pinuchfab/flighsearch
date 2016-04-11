package pinuch.flightssearch;

/**
 * AirLine class.
 * 
 * @author ukedfabien
 *
 */
public class AirLine 
{
	private final String name;
	private final String IATACode;
	private final Price infantPrice;
	
	public AirLine(String name, String IATACode, Price infantPrice)
	{
		this.name = name;
		this.IATACode = IATACode;
		this.infantPrice = infantPrice;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getIATACode()
	{
		return IATACode;
	}
	
	public Price getInfantPrice()
	{
		return infantPrice;
	}
}
