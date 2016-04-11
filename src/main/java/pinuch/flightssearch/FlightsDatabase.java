package pinuch.flightssearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * FlightsDatabase class.
 * 
 * Generate a fake database containing flights, airLines and airports.
 * Provide indexes to search for flights fast.
 * 
 * Airports map built programatically.
 * AirLines map built programatically.
 * Flights maps are built from a file: ./resources/flights
 * 
 * @author ukedfabien
 *
 */
public final class FlightsDatabase 
{
	// HashMap of airports, <IATACode, Airport>.
	public static final Map<String, Airport> airports = new HashMap<String, Airport>();
	
	// HashMap of airlines, <IATACode, AirLine>.
	public static final Map<String, AirLine> airLines = new HashMap<String, AirLine>();

	// Flights.
	//public static final Set<Flight> flights = new HashSet<Flight>();
	public static final Map<String, Set<Flight>> flightsIndexedByFromToAirportIATACodes = new HashMap<String, Set<Flight>>();
	public static final Map<String, Flight> flightsIndexedByFligthCode = new HashMap<String, Flight>();
	
	// Create the database.
	static 
	{
		// Create the air lines.
		airLines.put("IB", 	new AirLine(	"IB", 	"Iberia", 				new Price(10.0, 	Currency.Euro)));
		airLines.put("BA", 	new AirLine(	"BA", 	"British Airways", 		new Price(15.0, 	Currency.Euro)));
		airLines.put("LH", 	new AirLine(	"LH", 	"Lufthansa", 			new Price(7.0, 		Currency.Euro)));
		airLines.put("FR", 	new AirLine(	"FR", 	"Ryanair", 				new Price(20.0, 	Currency.Euro)));
		airLines.put("VY", 	new AirLine(	"VY", 	"Vueling", 				new Price(10.0, 	Currency.Euro)));
		airLines.put("TK", 	new AirLine(	"TK", 	"Turkish Airlines", 	new Price(5.0, 		Currency.Euro)));
		airLines.put("U2", 	new AirLine(	"U2", 	"EasyJet", 				new Price(19.9, 	Currency.Euro)));
		
		// Create the airports.
		airports.put("MAD", new Airport(	"IB", 	"Madrid"));
		airports.put("BCN", new Airport(	"BA", 	"Barcelona"));
		airports.put("LHR", new Airport(	"LH", 	"London"));
		airports.put("CDG", new Airport(	"FR", 	"Paris"));
		airports.put("FRA", new Airport(	"VY", 	"Frankfurt"));
		airports.put("IST", new Airport(	"TK", 	"Istanbul"));
		airports.put("AMS", new Airport(	"AMS", 	"Amsterdam"));
		airports.put("FCO", new Airport(	"U2", 	"Rome"));
		airports.put("CPH", new Airport(	"CPH", 	"Copenhagen"));
		
		// Create the flights from a csv file.
		FileInputStream fstream = null;
		BufferedReader br = null;
		
		try 
		{
			fstream = new FileInputStream("./resources/flights");
		
			br = new BufferedReader(new InputStreamReader(fstream));

			String flightCodeLine;
	
			// Line By Line
			while((flightCodeLine = br.readLine()) != null)   
			{
				String[] parts = flightCodeLine.split(",");
				
				// Creates flight.
				addFlightToDatabase(
					findAirportFromIATACode(parts[0]), 					// From airport.
					findAirportFromIATACode(parts[1]), 					// To airport.
					findAirLineFromFlightCode(parts[2]),				// Airline. 
					parts[2], 											// Flight code.
					new Price(Double.valueOf(parts[3]), Currency.Euro)	// Price in Euro.
				);
			}

		} 
		catch (IOException|NoSuchAlgorithmException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (br != null)
					br.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private FlightsDatabase()
	{
	}
	
	private static void addFlightToDatabase(Airport from, Airport to, AirLine airLine, String flightCode, Price price) throws NoSuchAlgorithmException
	{
		Flight flight = new Flight(from, to, airLine, flightCode, price);
		//flights.add(new Flight(from, to, airLine, flightCode, price));
		flightsIndexedByFligthCode.put(flightCode, flight);
		
		String key = from.getIATACode() + to.getIATACode();
		if (flightsIndexedByFromToAirportIATACodes.containsKey(key))
		{
			Set<Flight> existingSet = flightsIndexedByFromToAirportIATACodes.get(key);
			existingSet.add(flight);
		}
		else
		{
			Set<Flight> newSet = new HashSet<Flight>();
			newSet.add(flight);
			flightsIndexedByFromToAirportIATACodes.put(key, newSet);
		}
	}
	
	/**
	 * Given a flight code, find the matching air line.
	 * 
	 * @param flightCode
	 * @return AirLine
	 */
	public static AirLine findAirLineFromFlightCode(String flightCode)
	{
		// Grab first 2 character of the flight code that represents the air line IATACode.
		return airLines.get(flightCode.substring(0, 2));
	}
	
	/**
	 * Given an IATACode, find the matching airport.
	 * 
	 * @param IATACode
	 * @return Airport
	 */
	public static Airport findAirportFromIATACode(String IATACode)
	{
		return airports.get(IATACode);
	}
	
	/**
	 * Given both 'from' and 'to' airports, find the matching flights.
	 * 
	 * @param from
	 * @param to
	 * @return Set<Flight>
	 */
	public static Set<Flight> findFlights(Airport from, Airport to)
	{
		// The hash is the concatenation of the 'from' and 'to' airports.
		Set<Flight> results = flightsIndexedByFromToAirportIATACodes.get(from.getIATACode() + to.getIATACode());
		
		// If null, prefer rerturning an empty container.
		if (results == null)
			results = new HashSet<Flight>();
		
		return results;
	}
	
	/**
	 * Given a flight code, find the matching flight.
	 * 
	 * @param flightCode
	 * @return Flight
	 */
	public static Flight findFlight(String flightCode)
	{
		return flightsIndexedByFligthCode.get(flightCode);
	}
}
