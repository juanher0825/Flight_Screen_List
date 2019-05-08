package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

class FlightsTest {

	private Flights flights;
	
	public void setupScenary1() throws IOException {
		flights = new Flights(null);
	}
	
	public void setupScenary2() {
		
	}
	@Test
	
	public void generateFlightListTest1() throws IOException {
		setupScenary1();
		try {
			ArrayList<Flight> a = flights.generateFlightsList(2);
			if(a != null) {
				assertTrue(true);
			}
		}catch(NullPointerException e) {
			fail("Produjo una null pointer exception");
		}
	}
	
	@Test
	 public void generateFlightListTest2() {
		setupScenary2();
		try {
			ArrayList<Flight> a = flights.generateFlightsList(2);
			if(a != null) {
				fail("Deberia producir una NullPointerException");
			}
		}catch(NullPointerException e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	
	public void sortByDepartureTest1() throws IOException {
		setupScenary1();
		try {
			ArrayList<Flight> a = flights.generateFlightsList(2);
			flights.sortByDeparture();
			assertTrue(true);
		}catch (NullPointerException e) {
			fail("No deberia producir excepcion");
		}
	}
	
	@Test
	
	public void sortByAirlineTest1() throws IOException {
		setupScenary1();
		try {
			ArrayList<Flight> a = flights.generateFlightsList(2);
			flights.SortByAirline();
			assertTrue(true);
		}catch (NullPointerException e) {
			fail("No deberia producir excepcion");
			
		}
	}
	
	@Test
	
	public void sortByFlightId() throws IOException {
		setupScenary1();
		try {
			ArrayList<Flight> a = flights.generateFlightsList(2);
			flights.sortByFlightId();
			assertTrue(true);
		}catch (NullPointerException e) {
			fail ("No deberia producir error");
		}
	}
}
