package model;

import java.util.Comparator;

public class DateAndTimeComparator implements Comparator<Flight> {
	@Override
	public int compare(Flight flight1, Flight flight2) {
		int comparator;
		String date1 = flight1.getDate();
		String[] tmp1 = date1.split("-");
		String month1 = tmp1[1];
		String day1 = tmp1[2];
		
		String date2 = flight2.getDate();
		String[] tmp2 = date2.split("-");
		String month2 = tmp2[1];
		String day2 = tmp2[2];
		
		
		if (month1.compareTo(month2) < 0) {
			comparator = -1;
		} else if (month1.compareTo(month2) > 0) {
			comparator = 1;
		} else {
			if (day1.compareTo(day2) < 0) {
				comparator = -1;
			} else if (day1.compareTo(day2) > 0) {
				comparator = 1;
			} else {
				comparator = 0;
			}
		}
		return comparator;
	}

}
