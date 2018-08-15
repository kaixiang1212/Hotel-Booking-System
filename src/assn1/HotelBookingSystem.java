package assn1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author kxy12
 *
 */
public class HotelBookingSystem {
	private ArrayList<Booking> bookings;
	private ArrayList<Hotel> hotels;

	public HotelBookingSystem() {
		bookings = new ArrayList<Booking>();
		hotels = new ArrayList<Hotel>();
	}

	/**
	 * Instantiate New Hotel
	 * @param hotelName
	 * @return
	 */
	public Hotel newHotel(String hotelName) {
		Hotel hotel = new Hotel(hotelName);
		hotels.add(hotel);
		return hotel;
	}
	
	/**
	 * return hotel with matching name
	 * @param hotelName
	 * @return Hotel object
	 */
	public Hotel getHotel(String hotelName) {
		for (Hotel e : hotels) {
			// Compare hotel name
			if (hotelName.equals(e.getHotelName())) return e;
		}
		return null;
	}
	
	/**
	 * Check if the hotel is already created
	 * @param hotelName
	 * @return
	 */
	public boolean inHotels(String hotelName) {
		for (Hotel hotel : hotels) {
			if (hotelName.equals(hotel.getHotelName())) return true;
		}
		return false;
	}


	/**
	 * 
	 * Main Function
	 */

	public static void main (String[] args) {
		HotelBookingSystem sys = new HotelBookingSystem();
		Scanner sc = null;
		String date = "2016-08-16";
		//default, ISO_LOCAL_DATE
        LocalDate localDate = LocalDate.parse(date);
		System.out.println(localDate.format(DateTimeFormatter.ofPattern("MMM d")));

		try {
			sc = new Scanner(System.in);    // args[0] is the first command line argument
			// Read input from the scanner here
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] input = str.split(" ");
				Hotel hotel;
				switch (input[0]) {
				case "Hotel":
					if (!sys.inHotels(input[1])) hotel = sys.newHotel(input[1]);
					else hotel = sys.getHotel(input[1]);

					hotel.newRoom(input[2], input[3]);
					break;
				case "Booking":
					// check possible
					
					// book
					// System.out
					break;
				case "Change":
					// if possible change Sys.out
					// else Sys.out reject
					break;
				case "Cancel":
					// remove all bookings with name
					break;
				case "Print":
					hotel = sys.getHotel(input[1]);
					if (hotel != null) System.out.println(hotel);
					break;
				default:
					break;
				}
			}
		}
		finally
		{
			System.out.println("\n");
			if (sc != null) sc.close();
		}
	}
}
