package pinuch.flightssearch;

/**
 * PriceCalculator class.
 * 
 * Given a form and a flight, return the price based on passengers and airport/airline rules.
 * 
 * @author ukedfabien
 *
 */
public final class PriceCalculator 
{
	private PriceCalculator()
	{
		
	}
	
	/**
	 * Given a form and a flight, return the price based on passengers and airport/airline rules.
	 * 
	 * @param form
	 * @param flight
	 * @return Price
	 */
	public static Price getPriceFor(BookingForm form, Flight flight)
	{
		// Calculate the total price based on passengers.
		double priceAsDouble =
			getPriceForAdults(form, flight, form.getPriceRatio()) 		+ 	
			getPriceForChildren(form, flight, form.getPriceRatio()) 	+ 	
			getPriceForInfants(form, flight);
		
		// Price is always in Euro.
		return new Price(priceAsDouble, Currency.Euro);
	}

	private static double getPriceForAdults(BookingForm form, Flight flight, double ratio)
	{
		return form.getAdultCount() * (flight.getPrice().getValue() * ratio);
	}
	
	private static double getPriceForInfants(BookingForm form, Flight flight)
	{
		return form.getInfantCount() * (flight.getAirLine().getInfantPrice().getValue());
	}
	
	private static double getPriceForChildren(BookingForm form, Flight flight, double ratio)
	{
		return form.getChildCount() * 0.67 * (flight.getPrice().getValue() * ratio);
	}
}
