/**
 * 
 */
package assn1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author kxy12
 *
 */
public class Booking {
	private String customerName;
	private LocalDate startDate;
	private int days;
	private LocalDate endDate;
	private Room room;
	
	public Booking(String customerName, LocalDate startDate, int days, Room room) {
		this.customerName = customerName;
		this.startDate = startDate;
		this.days = days;
		this.endDate = startDate.plusDays(days);
		this.room = room;
	}
	
	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	public boolean inBetweenDates(LocalDate starts, int days) {
		LocalDate ends = startDate.plusDays(days);
		if (ends.isBefore(startDate) || starts.isAfter(endDate) || ends.equals(startDate)) {
			return false;
		}
		return true;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd");
		return startDate.format(formatter);
	}
	
	public boolean checkName(String name) {
		return name.equals(customerName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getMonthDate() + " " + getDays();
	}


}
