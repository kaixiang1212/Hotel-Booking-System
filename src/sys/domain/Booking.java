package sys.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author kxy12
 *
 */
public class Booking {
	private String customerName;
	private Room room;
	private int days;
	private LocalDate startDate;
	private LocalDate endDate;

	protected Booking(String name, Room rm, int days, LocalDate startDate) {
		this.customerName = name;
		this.room = rm;
		this.days = days;
		this.startDate = startDate;
		this.endDate = this.startDate.plusDays(days);
	}

	private LocalDate computeEndDate(LocalDate date, int period) {
		return date.plusDays(period);
	}

	protected boolean hasOverlaps(LocalDate starts, int period) {
		LocalDate ends = computeEndDate(starts, period);
		if (this.startDate.isBefore(starts) && this.endDate.isBefore(ends)) {
			return false;
		} else if (this.endDate.isAfter(starts) && this.endDate.isAfter(ends)) {
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
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getRoom().getHotelName() + " " + startDate.format(DateTimeFormatter.ofPattern("MMM d"));
	}
	

}
