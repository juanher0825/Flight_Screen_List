package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Flights {
	//Relations
	
	private Flight first;
	
	//Attributes

	private String[] cities;
	private String[] airlines;
	private final static String CITIES_FILE = "resources/cities.txt";
	private final static String AIRLINES_FILE = "resources/airlines.txt";
	
	public Flights (Flight first) throws IOException {
		this.first = first; 
		cities = new String[10];
		airlines = new String[10];
		
		loadCities(CITIES_FILE);
		loadAirlines(AIRLINES_FILE);
		
		

	
	}
	
	public Flight getFlights() {
		return first;
	}
	
	public Flight generateFlightsList(int value) throws NumberFormatException {

		for (int I = 0; I < value; I++) {
			int month = (int) (Math.random() * 12 + 1);
			int day = (int) (Math.random() * 30 + 1);
			String date = "2019";
			if (month < 10) {
				date += "-0" + month;
			} else {
				date += "-" + month;
			}
			if (day < 10) {
				date += "-0" + day;
			} else {
				date += "-" + day;
			}

			String departureTime = "";
			int hour = (int) (Math.random() * 12 + 1);
			int minutes = (int) (Math.random() * 59);
			int momentOfTheDay = (int) (Math.random() * 2);

			if (hour < 10) {
				departureTime += "0" + hour + ":";
			} else {
				departureTime += hour + ":";
			}
			if (minutes < 10) {
				departureTime += "0" + minutes;
			} else {
				departureTime += minutes;
			}
			if (momentOfTheDay == 1) {
				departureTime += " AM";
			} else {
				departureTime += " PM";
			}

			int air = (int) (Math.random()*8);
			String airline = airlines[air];

			int temp2 = (int) (Math.random() * 9999 + 1000);
			if (temp2 > 9999) {
				temp2 = (int) (Math.random() * 2000 + 1000);
			}
			String id = temp2 + "";

			int temp1 = (int) (Math.random()*9);
			String destinationCity = cities[temp1];
			if (destinationCity == null) {
				destinationCity = cities[0];
			}

			int boardingGate = (int) (Math.random() * 20);

			Flight temp = new Flight(date, departureTime, airline, id, destinationCity, boardingGate);
			addFly(temp);
		}
		return first;
	}
	
	public void sortByDate() {
		//inteface 
		//Collections.sort(flights,new DateAndTimeComparator());
	}
	
	public void sortByDeparture() {
		for (int K = 0; K < getSize(); K++) {
			String minorHour = getFlight(K).getDepartureTime();
			int minorPosition = K;
			
			String[] separateMoment = minorHour.split(" ");
			String[] separateHour = minorHour.split(":");
			String momentOfTheDay = separateMoment[1];
			
			String[] halfHour = separateHour[1].split(" ");
			String minutes = halfHour[0];
			String hourComplete = separateHour[0] + minutes;
			int minorTime = Integer.parseInt(hourComplete);
			
			if(momentOfTheDay.equals("PM")) {
				minorTime = minorTime+1200;
			}
			
			for (int J = K + 1; J < getSize(); J++) {
				String currentFlight = getFlight(J).getDepartureTime();
				
				String[] separateMoment2 = currentFlight.split(" ");
				String[] separateHour2 = currentFlight.split(":");
				String momentOfTheDay2 = separateMoment2[1];
				
				String[] halfHour2 = separateHour2[1].split(" ");
				String minutes2 = halfHour2[0];
				String hourComplete2 = separateHour2[0] + minutes2;
				int currentHour = Integer.parseInt(hourComplete2);
				
				if(momentOfTheDay2.equals("PM")) {
					currentHour = currentHour+1200;
				}		
				
				if (first != null) {
					boolean changed = true;
					while (changed) {
						Flight current = first;
						changed = false;
						while (current.getNext() != null) {
							Flight next = current.getNext();
							if (currentHour < minorTime) {
								if (current.getPrevious() != null) {
									current.getPrevious().setNext(next);
								}
								if (next.getNext() != null) {
									next.getNext().setPrevious(current);
								}

								current.setNext(next.getNext());
								next.setPrevious(current.getPrevious());
								current.setPrevious(next);
								next.setNext(current);

								if (current == first) {
									first = next;
								}
								changed = true;
							} else {
								current = current.getNext();
							}
						}
					}
				}
			}
		}		
	}

	public void SortByAirline() {
		if (first != null) {
			boolean changed = true;
			while (changed) {
				Flight current = first;
				changed = false;
				while (current.getNext() != null) {
					Flight next = current.getNext();
					if (current.getAirline().compareTo(next.getAirline()) > 0) {
						if (current.getPrevious() != null) {
							current.getPrevious().setNext(next);
						}
						if (next.getNext() != null) {
							next.getNext().setPrevious(current);
						}

						current.setNext(next.getNext());
						next.setPrevious(current.getPrevious());
						current.setPrevious(next);
						next.setNext(current);

						if (current == first) {
							first = next;
						}
						changed = true;
					} else {
						current = current.getNext();
					}
				}
			}
		}
	}

	/*
	 * Ordena los vuelos segun ciudad de destino, ordenamiento por seleccion
	 */
	public void SortByDesnitationCity() {
		if (first != null) {
			boolean changed = true;
			while (changed) {
				Flight current = first;
				changed = false;
				while (current.getNext() != null) {
					Flight next = current.getNext();
					if (current.getDestinationCity().compareTo(next.getDestinationCity()) > 0) {
						if (current.getPrevious() != null) {
							current.getPrevious().setNext(next);
						}
						if (next.getNext() != null) {
							next.getNext().setPrevious(current);
						}

						current.setNext(next.getNext());
						next.setPrevious(current.getPrevious());
						current.setPrevious(next);
						next.setNext(current);

						if (current == first) {
							first = next;
						}
						changed = true;
					} else {
						current = current.getNext();
					}
				}
			}
		}
	}
	/*
	 * Organiza los vuelos por puerta embarque, utiliza el metodo burubuja de
	 * ordenamiento
	 */
	public void sortByBoardingGate() {
		if (first != null) {
			boolean changed = true;
			while (changed) {
				Flight current = first;
				changed = false;
				while (current.getNext() != null) {
					Flight next = current.getNext();
					if (current.getBoardingGate() > (next.getBoardingGate())) {
						if (current.getPrevious() != null) {
							current.getPrevious().setNext(next);
						}
						if (next.getNext() != null) {
							next.getNext().setPrevious(current);
						}

						current.setNext(next.getNext());
						next.setPrevious(current.getPrevious());
						current.setPrevious(next);
						next.setNext(current);

						if (current == first) {
							first = next;
						}
						changed = true;
					} else {
						current = current.getNext();
					}
				}
			}
		}
	}

	/*
	 * Organiza los vuelos por identificador, utiliza el metodo de seleccion
	 */
	public void sortByFlightId() {
		if (first != null) {
			boolean changed = true;
			while (changed) {
				Flight current = first;
				changed = false;
				while (current.getNext() != null) {
					Flight next = current.getNext();
					if (current.getId().compareTo(next.getId()) > 0) {
						if (current.getPrevious() != null) {
							current.getPrevious().setNext(next);
						}
						if (next.getNext() != null) {
							next.getNext().setPrevious(current);
						}

						current.setNext(next.getNext());
						next.setPrevious(current.getPrevious());
						current.setPrevious(next);
						next.setNext(current);

						if (current == first) {
							first = next;
						}
						changed = true;
					} else {
						current = current.getNext();
					}
				}
			}
		}
	}
	
	/*
	 * El metodo calcula el tiempo que tardó en realizar una búsqueda u ordenamiento
	 */
	public long calculateTime(long delayedTime) {
		long timeRelease = System.currentTimeMillis();
		long timeElapsed = timeRelease - delayedTime;
		return timeElapsed;
	}

	/*
	 * El metodo busca en un arreglo un vuelo return el primer Flight encontrado
	 */
	public Flight searchFly(String characteristic) {
		Flight temp = null;
		return temp;
	}
	
	 // The method loads a list of cities from a text file
	public void loadCities(String path) throws IOException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while (line != null) {
			cities = line.split(" ");
			line = br.readLine();
		}
		br.close();
		fr.close();
	}

	 // The method loads a list of airlines from a text file
	public void loadAirlines(String path) throws IOException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while (line != null) {
			airlines = line.split(" ");
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
	
	public Flight getFirst() {

		return first;
	}

	public void setFirst(Flight first) {
		this.first = first;
	}
	
	public void addFly(Flight toAdd) {
		if (first == null) {
			first = toAdd;
			first.setNext(null);
		} else {
			Flight current = first;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(toAdd);
			toAdd.setNext(null);
		}
	}

	public Flight getFlight(int position) {
		if(position==0) {
			return first;
		}else {
			Flight current = first;
			for (int i = 0; i < position; i++) {
                current = current.getNext();
            }
			return current;
		}
	}
	
	public int getSize() {
		int amountFlights = 1;
		if (first == null) {
			return 0;
		} else {
			Flight current = first;
			while (current.getNext() != null) {
				current = current.getNext();
				amountFlights++;
			}
			return amountFlights;
		}
	}
	
	
}
