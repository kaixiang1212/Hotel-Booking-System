package ass1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author kxy12
 *
 */
public class Room {
	private Hotel hotel;
	private String roomNo;
	private ArrayList<Booking> bookings;
	private int roomType;

	public Room(Hotel hotel, String roomNo, int roomType) {
		this.roomNo = roomNo;
		this.bookings = new ArrayList<Booking>();
		this.roomType = roomType;
		this.hotel = hotel;
	}

	/**
	 * @return return 1:single, 2:double, 3:triple
	 */
	public int getRoomType() {
		return roomType;
	}

	/**
	 * @return Room Number
	 */
	public String getRoomNo() {
		return roomNo;
	}


	public String getHotelName() {
		return hotel.getHotelName();
	}

	/**
	 * @return List of bookings
	 */
	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @param booking Booking to add to the list
	 */
	public void addBooking(Booking booking) {
		bookings.add(booking);
		bookings.sort(new Comparator<Booking>() {
			public int compare(Booking b1, Booking b2) {
				return b1.getStartDate().compareTo(b2.getStartDate());
			}
		});
	}

	/**
	 * Remove booking from the list
	 * @param booking Booking to remove from the list
	 */
	public void removeBooking(Booking booking) {
		bookings.remove(booking);
	}

	/**
	 * 
	 * @param startDate Date to check availability
	 * @param days Days to check availability
	 * @return True if room is booked within a period, false otherwise
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
		sb.append(getRoomNo());
		for (Booking b : getBookings()) {
			sb.append(" " + b.getMonthDay());
		}
		return sb.toString();
	}

	

}
