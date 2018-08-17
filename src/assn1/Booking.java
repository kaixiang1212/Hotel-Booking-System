/**
 * 
 */
package assn1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author kxy12
 *
 */
public class Booking {
	private String customerName;
	private LocalDate startDate;
	private int days;
	private LocalDate endDate;
	private ArrayList<Room> rooms;

	public Booking(String customerName, LocalDate startDate, int days) {
		this.customerName = customerName;
		this.startDate = startDate;
		this.days = days;
		this.endDate = startDate.plusDays(days);
		this.rooms = new ArrayList<Room>();
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * @return the days
	 */
	int getDays() {
		return days;
	}
	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	public String getMonthDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL d");
		return startDate.format(formatter);
	}

	public boolean checkName(String name) {
		return name.equals(customerName);
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		this.rooms.remove(room);
	}
	
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}
	
	public boolean inBetweenDates(LocalDate starts, int days) {
		LocalDate ends = starts.plusDays(days);
		if (startDate.isAfter(ends) || endDate.isBefore(starts) || endDate.equals(starts) || ends.equals(startDate)) return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getMonthDate() + " " + getDays();
	}


}
