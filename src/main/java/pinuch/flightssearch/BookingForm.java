package pinuch.flightssearch;

import java.util.Date;

/**
 * BookingForm class.
 * 
 * This is the flight booking form.
 * 
 * @author ukedfabien
 *
 */
public class BookingForm
{
	private final Date departureTime;
	private final Airport from;
	private final Airport to;
	private final int adultCount;
	private final int childCount;
	private final int infantCount;
	
	// Ratio to apply to price based on date of departure and date of booking.
	private final double priceRatio;
	
	private static final long DAY_IN_MILLSEC = (long)8.64e+7;
	
	public BookingForm(Date depatureTime, String fromIATACode, String toIATACode, int adultCount, int childCount, int infantCount)
	{
		this.departureTime = depatureTime;
		this.from = FlightsDatabase.findAirportFromIATACode(fromIATACode);
		this.to = FlightsDatabase.findAirportFromIATACode(toIATACode);
		this.adultCount = adultCount;
		this.childCount = childCount;
		this.infantCount = infantCount;
		this.priceRatio = calculatePriceRatio();
	}
	
	public BookingForm(Date depatureTime, Airport from, Airport to, int adultCount, int childCount, int infantCount)
	{
		this.departureTime = depatureTime;
		this.from = from;
		this.to = to;
		this.adultCount = adultCount;
		this.childCount = childCount;
		this.infantCount = infantCount;
		this.priceRatio = calculatePriceRatio();
	}
	
	private double calculatePriceRatio()
	{
		Date now = new Date();
		
		// Currently using local machine time to calculate difference in days,
		// this might imply time issue if the client and server are not synchronised perfectly.
		// Also we are not taking into account network latency which will have an impact in time calculation.
		long diffInDays = (long) Math.floor((double)(departureTime.getTime() - now.getTime()) / (double)DAY_IN_MILLSEC);
		
		if (diffInDays > 30)
			return .80;
		if (diffInDays >= 16)
			return 1.0;
		if (diffInDays >= 3)
			return 1.2;
		
		return 1.5;
	}
	
	public Date getDepartureTime()
	{
		return departureTime;
	}
	
	public int getAdultCount()
	{
		return adultCount;
	}
	
	public int getChildCount()
	{
		return childCount;
	}
	
	public int getInfantCount()
	{
		return infantCount;
	}
	
	public Airport getFrom()
	{
		return from;
	}
	
	public Airport getTo()
	{
		return to;
	}
	
	public double getPriceRatio()
	{
		return priceRatio;
	}
}
