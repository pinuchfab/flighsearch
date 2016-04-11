package pinuch.flightsearch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TestSearchEngine 
{
	private static final long DAY_IN_MILLSEC = (long) 8.64e+7;
	
	private void searchAndVerify(BookingForm form, List<SearchResult> expected)
	{
		// Find flights.
		List<SearchResult> results = SearchEngine.getSearchResults(form);
				
		assertEquals(expected.size(), results.size());
		for(SearchResult expectedResult : expected)
			assertTrue(results.contains(expectedResult));
	}
	
	@Test
	public void testAMS_To_FRA_30d_OneAdult() 
	{
		// For test only, add 20 second to departure time to compensate the latency,
		// Otherwize 30 days will become 29 days.
						
		// 1 adult, 31 days (not 30 as per example in test document which is wrong) to the departure date, flying AMS -> FRA
		// Expect:
		//	* TK2372, 157.6 €
		//	* TK2659, 198.4 €
		//	* LH5909, 90.4 €
		//
		
		Date moreThanThirtyDays = new Date((new Date()).getTime() + (31 * DAY_IN_MILLSEC) + 20000);
		BookingForm form = new BookingForm(moreThanThirtyDays, "AMS", "FRA", 1, 0, 0);
		
		// Build expected results.
		List<SearchResult> expected = new ArrayList<SearchResult>();
		expected.add(new SearchResult(FlightsDatabase.findFlight("TK2372"), new Price(157.6, Currency.Euro)));
		expected.add(new SearchResult(FlightsDatabase.findFlight("TK2659"), new Price(198.4, Currency.Euro)));
		expected.add(new SearchResult(FlightsDatabase.findFlight("LH5909"), new Price(90.4, Currency.Euro)));
		
		searchAndVerify(form, expected);
	}
	
	@Test
	public void testLHR_To_IST_15d_TwoAdults_OneChild_OneInfant() 
	{
		// For test only, add 20 second to departure time to compensate the latency,
		// Otherwize 30 days will become 29 days.
		
		// 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
		// Expect:
		//	* TK8891, 806 € (2 * (120% of 250) + 67% of (120% of 250) + 5)
		//  * LH1085, 481.19 € (2 * (120% of 148) + 67% of (120% of 148) + 7)
		//
		
		Date fiftenDays = new Date((new Date()).getTime() + (15 * DAY_IN_MILLSEC) + 20000);
		BookingForm form = new BookingForm(fiftenDays, "LHR", "IST", 2, 1, 1);
		
		// Build expected results.
		List<SearchResult> expected = new ArrayList<SearchResult>();
		expected.add(new SearchResult(FlightsDatabase.findFlight("TK8891"), new Price(806.0, Currency.Euro)));
		expected.add(new SearchResult(FlightsDatabase.findFlight("LH1085"), new Price(481.2, Currency.Euro)));
		
		searchAndVerify(form, expected);
	}
	
	@Test
	public void testBCN_To_MAD_2d_OneAdult_TwoChildren() 
	{
		// For test only, add 20 second to departure time to compensate the latency,
		// Otherwize 30 days will become 29 days.
				
		// 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
		// Expect:
		//	* IB2171, 909.09 € (150% of 259 + 2 * 67% of (150% of 259))
		//  * LH5496, 1028.43 € (150% of 293 + 2 * 67% of (150% of 293))
		//
		
		Date LessThanThreeDay = new Date((new Date()).getTime() + (2 * DAY_IN_MILLSEC) + 20000);
		BookingForm form = new BookingForm(LessThanThreeDay, "BCN", "MAD", 1, 2, 0);
		
		// Build expected results.
		List<SearchResult> expected = new ArrayList<SearchResult>();
		expected.add(new SearchResult(FlightsDatabase.findFlight("IB2171"), new Price(909.1, Currency.Euro)));
		expected.add(new SearchResult(FlightsDatabase.findFlight("LH5496"), new Price(1028.4, Currency.Euro)));
		
		searchAndVerify(form, expected);
	}
	
	@Test
	public void testCDG_To_FRA() 
	{
		// For test only, add 20 second to departure time to compensate the latency,
		// Otherwize 30 days will become 29 days.
				
		// Expect no result.

		Date LessThanThreeDay = new Date((new Date()).getTime() + (2 * DAY_IN_MILLSEC) + 20000);
		BookingForm form = new BookingForm(LessThanThreeDay, "CDG", "FRA", 1, 2, 0);
		
		// Build expected results.
		List<SearchResult> expected = new ArrayList<SearchResult>();
		
		searchAndVerify(form, expected);
	}
}
