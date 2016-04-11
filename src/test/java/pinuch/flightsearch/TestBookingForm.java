package pinuch.flightsearch;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TestBookingForm 
{
	private static final long DAY_IN_MILLSEC = (long) 8.64e+7;
	
	@Test
	public void testPriceRatioBasedOnDepartureTime() 
	{
		// For test only, add 20 second to departure time to compensate the latency,
		// Otherwize 30 days will become 29 days.
		
		// > 30 days, expect 80%.
		Date departureDate = new Date((new Date()).getTime() + (31 * DAY_IN_MILLSEC) + 20000);
		BookingForm form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double).80 == form.getPriceRatio());
		
		// == 30 days, expect 100%.
		departureDate = new Date((new Date()).getTime() + (30 * DAY_IN_MILLSEC) + 20000);
		form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double)1.0 == form.getPriceRatio());
		
		// < 30 & > 16 days, expect 100%.
		departureDate = new Date((new Date()).getTime() + (20 * DAY_IN_MILLSEC) + 20000);
		form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double)1.0 == form.getPriceRatio());
		
		// == 16 days, expect 100%.
		departureDate = new Date((new Date()).getTime() + (16 * DAY_IN_MILLSEC) + 20000);
		form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double)1.0 == form.getPriceRatio());
		
		// > 3 & < 16 days, expect 120%.
		departureDate = new Date((new Date()).getTime() + (5 * DAY_IN_MILLSEC) + 20000);
		form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double)1.2 == form.getPriceRatio());
		
		// == 3, expect 120%.
		departureDate = new Date((new Date()).getTime() + (3 * DAY_IN_MILLSEC) + 20000);
		form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double)1.2 == form.getPriceRatio());
		
		// < 3 days, expect 150%.
		departureDate = new Date((new Date()).getTime() + (2 * DAY_IN_MILLSEC) + 20000);
		form = new BookingForm(departureDate, "AMS", "FRA", 1, 0, 0);
		
		assertTrue((double)1.5 == form.getPriceRatio());
	}

}
