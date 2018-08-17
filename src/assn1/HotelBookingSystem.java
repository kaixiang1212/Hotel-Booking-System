package assn1;

import java.lang.reflect.Array;
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
	 * return the hotel that has vacancy
	 * @param input
	 * @return
	 */
	public Hotel hasVacancy(LocalDate date, int days, int[] type) {

		for (Hotel e : hotels) {
			int typeMet = 0;
			if (e.hasVacancy(date, days,1 , type[0])) typeMet++;
			if (e.hasVacancy(date, days, 2, type[1])) typeMet++;
			if (e.hasVacancy(date, days, 3, type[2])) typeMet++;
			if (typeMet == 3) return e;
		}
		return null;
	}
	
	/**
	 * Create new booking with the inputs
	 * @param hotel
	 * @param name
	 * @param date
	 * @param days
	 * @param roomsToBook
	 * @return Booking Created
	 */
	public Booking newBooking(Hotel hotel,String name, LocalDate date, int days, ArrayList<Room> roomsToBook) {
		StringBuilder sb = new StringBuilder();
		sb.append("Booking " + name + " " + hotel.getHotelName() + " ");
		Booking booking = new Booking(name, date, days);
		for (Room e : roomsToBook) {
			e.addBooking(booking);
			booking.addRoom(e);
			sb.append(e.getRoomNo() + " ");
		}
		
		this.bookings.add(booking);
		System.out.println(sb.toString());
		return booking;
	}

	/**
	 * get the booking under the name
	 * @param name
	 * @return booking under the name 
	 */
	public Booking getBookingWithName(String name) {
		for (Booking b : this.bookings) {
			if (name.equals(b.getCustomerName())) return b;
		}
		return null;
	}
	
	public void cancelBooking(String name) {
		Booking booking = getBookingWithName(name);
		if (booking == null) {
			System.out.println("Booking not Found");
		}
		this.bookings.remove(booking);
		for (Room e : booking.getRooms()) {
			e.removeBooking(booking);
		}
	}

	
	
	/**
	 * determine request types
	 * @param input
	 * @return number of type of room requested
	 */
	public int requestType(String[] input) {
		if (input.length == 7) return 1;
		if (input.length == 9) return 2;
		if (input.length == 11) return 3;
		return 0;
	}
	/**
	 * create LocalDate object with extract from input
	 * @param input
	 * @return
	 */
	public LocalDate extractDate(String[] input) {
		String dateString = input[3]+input[2]+"2018";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dMMMyyyy");
		LocalDate date = LocalDate.parse(dateString, formatter);
		return date;
	}
	
	/**
	 * extract days from raw input
	 * @param input
	 * @return
	 */
	public int extractDays(String[] input) {
		return Integer.parseInt(input[4]);
	}

	/**
	 * extract type of room requested from raw input
	 * @param input
	 * @return
	 */
	public int[] extractType(String[] input) {
		int[] type = new int[3];
		for (int i=0;i<input.length;i++) {
			if (input[i].equals("single")) {
				type[0] = Integer.parseInt(input[i+1]);
			} else if (input[i].equals("double")) {
				type[1] = Integer.parseInt(input[i+1]);
			} else if (input[i].equals("triple")) {
				type[2] = Integer.parseInt(input[i+1]);
			}
		}
		return type;
	}

	/**
	 * 
	 * Main Function
	 */

	public static void main (String[] args) {
		HotelBookingSystem sys = new HotelBookingSystem();
		Scanner sc = null;
		
		Hotel hotel;
		LocalDate date;
		String name;
		int days;
		int[] type;

		try {
			sc = new Scanner(System.in);    // args[0] is the first command line argument
			// Read input from the scanner here
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] input = str.split(" ");


				switch (input[0]) {
				
				case "Hotel":
					if (!sys.inHotels(input[1])) hotel = sys.newHotel(input[1]);
					else hotel = sys.getHotel(input[1]);

					hotel.newRoom(input[2], input[3]);
					break;

				case "Booking":
					name = input[1];
					date = sys.extractDate(input);
					days = sys.extractDays(input);
					type = sys.extractType(input);

					// check possible
					hotel = sys.hasVacancy(date, days, type);
					//  Make Booking;
					if (hotel == null) {
						System.out.println("Booking rejected");
						break;
					}
					ArrayList<Room> roomToBook = hotel.getAvailableRoom(date, days, type);
					sys.newBooking(hotel, name, date, days, roomToBook);
					break;

				case "Change":
					// if possible change then Sys.out
					// else Sys.out reject
					break;

				case "Cancel":
					// remove all bookings with name
					name = input[1];
					sys.cancelBooking(name);
					break;

				case "Print":
					hotel = sys.getHotel(input[1]);
					if (hotel != null) System.out.print(hotel);
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
