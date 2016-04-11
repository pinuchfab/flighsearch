package pinuch.flightsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSearchResult 
{
	@Test
	public void testToString() 
	{
		SearchResult searchResult = new SearchResult(FlightsDatabase.findFlight("IB2818"), new Price(123.5, Currency.Euro));
		assertEquals("*\tIB2818, 123.5 Euro (€)", searchResult.toString());
	}
	
	@Test
	public void testEquals() 
	{
		//
		// Perfect match.
		//
		
		SearchResult searchResultOne = new SearchResult(FlightsDatabase.findFlight("IB2818"), new Price(123.5, Currency.Euro));
		SearchResult searchResultTwo = new SearchResult(FlightsDatabase.findFlight("IB2818"), new Price(123.5, Currency.Euro));
		
		assertTrue(searchResultOne.equals(searchResultTwo));
		
		//
		// Flight codes don't match.
		//
		
		searchResultOne = new SearchResult(FlightsDatabase.findFlight("TK2372"), new Price(123.5, Currency.Euro));
		searchResultTwo = new SearchResult(FlightsDatabase.findFlight("IB2818"), new Price(123.5, Currency.Euro));
		
		assertFalse(searchResultOne.equals(searchResultTwo));
		
		//
		// Price don't match.
		//
		
		searchResultOne = new SearchResult(FlightsDatabase.findFlight("IB2818"), new Price(123.5, Currency.Euro));
		searchResultTwo = new SearchResult(FlightsDatabase.findFlight("IB2818"), new Price(124.5, Currency.Euro));
		
		assertFalse(searchResultOne.equals(searchResultTwo));
	}
}
