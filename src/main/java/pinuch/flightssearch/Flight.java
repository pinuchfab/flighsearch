package pinuch.flightssearch;

import java.security.NoSuchAlgorithmException;

/**
 * Flight class.
 * 
 * @author ukedfabien
 *
 */
public class Flight 
{
	private final Airport from;
	private final Airport to;
	private final String flightCode;
	private final AirLine airLine;
	private final Price price;
	
	public Flight(Airport from, Airport to, AirLine airline, String flightCode, Price price) throws NoSuchAlgorithmException
	{
		this.from = from;
		this.to = to;
		this.airLine = airline;
		this.price = price;
		
		this.flightCode = flightCode;
	}
	
	public String toString()
	{
		return flightCode;
	}
	
	public String getCode()
	{
		return flightCode;
	}
	
	public AirLine getAirLine()
	{
		return airLine;
	}
	
	public Price getPrice()
	{
		return price;
	}
	
	public Airport getFromAirport()
	{
		return from;
	}
	
	public Airport getToAirport()
	{
		return to;
	}
}
