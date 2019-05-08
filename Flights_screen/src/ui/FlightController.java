package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;
import model.Flight;
import model.Flights;

public class FlightController {
	
	private Flights flights;
	private String label1="";
	private String label2="";
	private String label3="";
	private long delayedTime;
	private int currentPage = 1;

    @FXML
    private TextField flightsField;

    @FXML
    private TextField searchField;

    @FXML
    private Label dateColumn;

    @FXML
    private Label airlineColumn;

    @FXML
    private Label flightColumn;

    @FXML
    private Label currentTime;

    @FXML
    void buttomAirline(ActionEvent event) {
    	try {
    		delayedTime = System.currentTimeMillis();
			flights.SortByAirline();
			currentTime.setText("Time: "+flights.calculateTime(delayedTime));
			showTable();
			
		}catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please create some flights first", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
        
    	}

    }

    @FXML
    void buttomDate(ActionEvent event) {
    	
    	try{
    		delayedTime = System.currentTimeMillis();
			flights.sortByDate();
			currentTime.setText("Time: "+flights.calculateTime(delayedTime));
			showTable();
		}catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please create some flights first", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
        	
    	} 

    }

    @FXML
    void buttomFlight(ActionEvent event) {
    	try{
    		delayedTime = System.currentTimeMillis();
			flights.sortByFlightId();
			currentTime.setText("Time: "+flights.calculateTime(delayedTime));
			showTable();
		}catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please create some flights first", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
    	} 

    }

    @FXML
    void buttomGate(ActionEvent event) {
    	try{
    		delayedTime = System.currentTimeMillis();
			flights.sortByBoardingGate();
			currentTime.setText("Time: "+flights.calculateTime(delayedTime));
			showTable();
		}catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please create some flights first", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
    	}

    }

    @FXML
    void buttomTime(ActionEvent event) {
    	try{
    		delayedTime = System.currentTimeMillis();
			flights.sortByDeparture();
			currentTime.setText("Time: "+flights.calculateTime(delayedTime));
			showTable();
		}catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please create some flights first", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
    	} 
    }

    @FXML
    void buttomTo(ActionEvent event) {
    	try {
			delayedTime = System.currentTimeMillis();
			flights.SortByDesnitationCity();
			currentTime.setText("Time: "+flights.calculateTime(delayedTime));
			showTable();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please create some flights first", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
		}
    }

    @FXML
    void generate(ActionEvent event) throws IOException {
    	int value = 0;
		try {
    		value = Integer.parseInt(flightsField.getText());
    		flights = new Flights(flights.generateFlightsList(value));
    	}catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.INFORMATION, "Please write a correct number", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
    	} 
		showTable();
    

    }

    @FXML
    void nextPage(ActionEvent event) {
    	int newPage = currentPage + 1;
		int size = 0;
		int amountPages = 0;
		Flight tmp = flights.getFirst();
		while (tmp != null) {
			tmp = tmp.getNext();
			amountPages++;
		}
		size = amountPages / 10;
		if (amountPages % 10 != 0) {
			size++;
		}
		
		if (newPage < (size + 2)) {
			currentPage = newPage;
			showTable();
		}
	}

    

    @FXML
    void previousPage(ActionEvent event) {
    	if (flights != null) {
    		showTable();
    	}
    }

    @FXML
    void search(ActionEvent event) {

    }
    
    @FXML
    
    void initialize() throws IOException {
    	flights = new Flights(null);
    }
    
		
    
    public void showTable() {
    	label1 = "";
		label2 = "";
		label3 = "";
		
		int pages = 0;
		int amountPages = 0;
		Flight tmp = flights.getFirst();
		while (tmp != null) {
			tmp = tmp.getNext();
			amountPages++;
		}
		pages = amountPages / 10;
		if (amountPages % 10 != 0) {
			pages++;
		}

		Flight currentFlight = flights.getFirst();
		int totalPages = 1;

		for (int I = 0; I < pages; I++) {
			if (totalPages % 10 == 0) {
				int forTheNextFlight = 0;
				while (forTheNextFlight < 10 && currentFlight != null) {
					currentFlight = currentFlight.getNext();
					forTheNextFlight++;
				}
			}

			totalPages = 0;
			if (I + 1 == currentPage) {
				for (int J = 0; J < 10 && currentFlight != null; J++) {
					label1 += currentFlight.getDate() + "\t\t\t" + currentFlight.getDepartureTime()
							+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + currentFlight.getBoardingGate() + "\n";
					
					dateColumn.setText(label1);
					
					label2 += currentFlight.getId() + "\t\t\t\t\t" + currentFlight.getDestinationCity() + "\n";
					flightColumn.setText(label2);

					label3 += currentFlight.getAirline() + "\n";
					airlineColumn.setText(label3);

					currentFlight = currentFlight.getNext();
					totalPages++;
				}
			}
		}
	}

}

