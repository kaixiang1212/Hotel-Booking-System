/**
 * 
 */
package sys.test;

import sys.domain.Booking;
import sys.domain.CapacityType;
import sys.domain.Hotel;
import sys.domain.HotelBookingSystem;

/**
 * @author kxy12
 *
 */
public class HotelBookingSysTest {
	
	public static void main (String[] args) {
		HotelBookingSystem sys= new HotelBookingSystem();
		Hotel hotel = new Hotel("Surfer");
		sys.addHotel(hotel);
		hotel.addRoom("404", CapacityType.Triple);
		sys.addBooking(booking);
		System.out.println(sys);
	}
}
