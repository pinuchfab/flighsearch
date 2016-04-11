package pinuch.flightsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPrice 
{
	@Test
	public void testToString() 
	{
		Price priceEuro = new Price(123.566, Currency.Euro);
		Price priceDollar = new Price(123.566, Currency.Dollar);
		Price priceBritishPound = new Price(123.566, Currency.BritishPound);
		
		assertEquals("123.6 Euro (€)", priceEuro.toString());
		assertEquals("123.6 Dollar ($)", priceDollar.toString());
		assertEquals("123.6 Pound (£)", priceBritishPound.toString());
	}
	
	@Test
	public void testValueToString() 
	{
		Price price = new Price(123.56, Currency.Euro);
		assertEquals("123.6", price.valueToString());
		
		price = new Price(123.99, Currency.Euro);
		assertEquals("124.0", price.valueToString());
		
		price = new Price(123.12, Currency.Euro);
		assertEquals("123.1", price.valueToString());
		
		price = new Price(123.01, Currency.Euro);
		assertEquals("123.0", price.valueToString());
		
		price = new Price(123.09, Currency.Euro);
		assertEquals("123.1", price.valueToString());
		
		// ... More to do.
	}
}
