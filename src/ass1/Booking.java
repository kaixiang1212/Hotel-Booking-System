/**
 * 
 */
package ass1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
 * @author kxy12
 *
 */
public class Booking {
	private String customerName;
	private LocalDate startDate;
	private int days;
	private ArrayList<Room> rooms;

	public Booking(String customerName, LocalDate startDate, int days) {
		this.customerName = customerName;
		this.startDate = startDate;
		this.days = days;
		this.rooms = new ArrayList<Room>();
	}

	/**
	 * @return Name of customer under the booking
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return Start date of the booking
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return Length of days on this booking
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @return Month Date Days as a formatted string
	 */
	public String getMonthDay() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL d");
		return startDate.format(formatter) + " " + getDays();
	}

	/**
	 * 
	 * @param name Customer name
	 * @return True if the given name matches the booking's name
	 */
	public boolean checkName(String name) {
		return name.equals(customerName);
	}

	/**
	 * Add room to the list of booking
	 * @param room to add to the booking
	 */
	public void addRoom(Room room) {
		this.rooms.add(room);
	}

	/**
	 * 
	 * @return a list of rooms under this booking
	 */
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}
	
	/**
	 * Check if the given period crashes the booking's period
	 * @param starts Start date to check
	 * @param days Length of days to check
	 * @return True if the given date crashes with the booking's date, false otherwise
	 */
	public boolean inBetweenDates(LocalDate compareStart, int days) {
		LocalDate endDate = startDate.plusDays(this.days);
		LocalDate compareEnd = compareStart.plusDays(days);
		if (compareEnd.isBefore(startDate) || compareStart.isAfter(endDate)) return false;
		if (compareEnd.isEqual(startDate) || compareStart.isEqual(endDate)) return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCustomerName() + " " + rooms.get(0).getHotelName());
		for (Room e : rooms) {
			sb.append(" " + e.getRoomNo());
		}
		return sb.toString();
	}


}
