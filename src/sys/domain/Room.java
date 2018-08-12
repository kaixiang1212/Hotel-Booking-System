package sys.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 * @author kxy12
 *
 */
public class Room {

	private Hotel hotel;
	private String roomNo;
	private int roomType;
	private ArrayList<Booking> bookings;

	protected Room(Hotel h, String roomNo, int roomType) {
		this.hotel = h;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.bookings = new ArrayList<Booking>();
	}

	public boolean isBooked(LocalDate date, Period period) {
		for(Booking b : this.bookings) {
			if (b.hasOverlaps(date, period))
				return true;
		}
		return false;
	}

	/**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}
	
	public String getHotelName() {
		return hotel.getHotelName();
	}

	/**
	 * @return the roomNo
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * @return the capacity
	 */
	public int getType() {
		return roomType;
	}

	/**
	 * @param roomNo the roomNo to set
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int roomType) {
		this.roomType = roomType;
	}

	/**
	 * @param add booking to bookings
	 */
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}

	/**
	 * @param remove a booking from bookings
	 */
	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Room Number: " + getRoomNo() + " Capacity: " + getType();
	}
	
}
