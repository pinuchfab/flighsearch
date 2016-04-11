package pinuch.flightsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * SearchEngine class.
 * 
 * This is the flights search engine, it takes a BookingForm to search for flights and prices.
 * 
 * @author ukedfabien
 *
 */
public final class SearchEngine 
{
	private SearchEngine()
	{
		
	}
	
	/**
	 * Given a booking form, find the matching flights, then return their respective price.
	 * 
	 * @param form
	 * @return List<SearchResult>
	 */
	public static List<SearchResult> getSearchResults(BookingForm form)
	{
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		// Then, find the flights.
		Set<Flight> foundFlights = FlightsDatabase.findFlights(form.getFrom(), form.getTo());
		
		for (Flight flight : foundFlights)
			// Add flight price to search results.
			searchResults.add(new SearchResult(flight, PriceCalculator.getPriceFor(form, flight)));
		
		return searchResults;
	}
}
