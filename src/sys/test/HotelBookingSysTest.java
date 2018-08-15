/**
 * 
 */
package sys.test;

import assn1.Booking;
import assn1.CapacityType;
import assn1.Hotel;
import assn1.HotelBookingSystem;

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
