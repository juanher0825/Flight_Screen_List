package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightTest {

	private Flight flight;
	
	public void setupScenary3() {
		flight = new Flight("2019-02-19", "01:00 PM", "DELTA", "1642", "Cali", 5);
	}
	
	@Test
	public void getBoardingGateTest() {
		setupScenary3();
		int boardingGate = 5;
		assertTrue(boardingGate == flight.getBoardingGate());
	}

	@Test
	public void getDestinationCityTest() {
		setupScenary3();
		String destinationCity = "Cali";
		assertTrue(destinationCity.equalsIgnoreCase(flight.getDestinationCity()));
	}

	@Test
	public void getIdTest() {
		setupScenary3();
		String id = "1642";
		assertTrue(id.equalsIgnoreCase(flight.getId()));
	}

	@Test
	public void getAirlineTest() {
		setupScenary3();
		String airline = "DELTA";
		assertTrue(airline.equalsIgnoreCase(flight.getAirline()));
	}

	@Test
	public void getDepartureTimeTest() {
		setupScenary3();
		String departureTime = "01:00 PM";
		assertTrue(departureTime.equalsIgnoreCase(flight.getDepartureTime()));
	}

	@Test
	public void getDateTest() {
		setupScenary3();
		String date = "2019-02-19";
		assertTrue(date.equalsIgnoreCase(flight.getDate()));
	}
	

}
