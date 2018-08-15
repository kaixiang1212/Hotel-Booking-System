package assn1;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author kxy12
 *
 */
public class Room {
	private String roomNo;
	private ArrayList<Booking> bookings;
	private int roomType;

	public Room(String roomNo, String roomType) {
		this.roomNo = roomNo;
		this.bookings = new ArrayList<Booking>();
		this.roomType = Integer.parseInt(roomType);
	}

	/**
	 * @return the roomType
	 */
	public int getRoomType() {
		return roomType;
	}

	/**
	 * @return the roomNo
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * @return the list of bookings
	 */
	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @param Booking
	 */
	public void addBooking(Booking booking) {
		bookings.add(booking);
	}

	/**
	 * remove booking from the list
	 * @param booking
	 */
	public void removeBooking(Booking booking) {
		bookings.remove(booking);
	}

	/**
	 * @return true if room is booked within a period
	 */
	public boolean isBooked(LocalDate startDate, int days) {
		for (Booking e : bookings) {
			if (e.inBetweenDates(startDate, days)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getRoomNo() + " ");
		for (Booking b : bookings) {
			sb.append(b + "\n");
		}
		return sb.toString();
	}

	

}
