package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.CancelablePrintJob;


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
	 * @param hotelName Name of the hotel
	 * @return Newly created hotel object with name = hotelName
	 */
	public Hotel newHotel(String hotelName) {
		Hotel hotel = new Hotel(hotelName);
		hotels.add(hotel);
		return hotel;
	}

	/**
	 * return hotel with matching name
	 * @param hotelName Name of the hotel
	 * @return Hotel object with matching name from the system
	 */
	public Hotel getHotel(String hotelName) {
		for (Hotel e : hotels) {
			// Compare hotel name
			if (hotelName.equals(e.getHotelName())) return e;
		}
		return null;
	}

	/**
	 * get the hotel with available vacancy
	 * @param date date to check vacancy
	 * @param days staying period
	 * @param type roomType int[0]:single,int[1]:double,int[3]:triple
	 * @return null if no hotel has vacancy, return the hotel otherwise
	 */
	public Hotel vacancy(LocalDate date, int days, int[] type) {

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
	 * 
	 * @param name Name under the booking
	 * @param date Start date to book
	 * @param days Number of days to book
	 * @param type Types of room and quantity requested
	 * @return The booking with give details
	 */
	public Booking newBooking(String name, LocalDate date, int days, int[] type) {
		Hotel hotel = vacancy(date, days, type);
		if (hotel == null) return null;
		Booking booking = new Booking(name, date, days);
		ArrayList<Room> roomsToBook = hotel.getAvailableRoom(date, days, type);
		for (Room e : roomsToBook) {
			e.addBooking(booking);
			booking.addRoom(e);
		}

		this.bookings.add(booking);
		return booking;
	}

	/**
	 * Get the booking under the name
	 * @param name Customer Name
	 * @return Booking under the name "name"
	 */
	public Booking getBooking(String name) {
		for (Booking b : this.bookings) {
			if (b.checkName(name)) return b;
		}
		return null;
	}

	/**
	 * Suspend the booking
	 * @param booking booking request to suspend
	 */
	public void suspendBooking(Booking booking) {
		if (booking == null) {
			System.out.println("Booking not Found");
		}
		this.bookings.remove(booking);
		for (Room e : booking.getRooms()) {
			e.removeBooking(booking);
		}
	}

	/**
	 * Reverted the suspended booking
	 * @param booking Suspended booking to revert
	 */
	public void revertBooking(Booking booking) {
		this.bookings.add(booking);
		for (Room e : booking.getRooms()) {
			e.addBooking(booking);
		}
	}

	/**
	 * create LocalDate object with extract from input
	 * @param input input seperated by " "
	 * @return LocalDate object 
	 */
	private LocalDate extractDate(String[] input) {
		String dateString = input[3]+input[2]+"2018";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dMMMyyyy");
		LocalDate date = LocalDate.parse(dateString, formatter);
		return date;
	}

	/**
	 * extract type of room requested from raw input
	 * @param input input seperated by " "
	 * @return array of quantity requested for 3 types of rooms
	 */
	private int[] extractType(String[] input) {
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
	 * @param args Arguments
	 */
	public static void main (String[] args) {
		HotelBookingSystem sys = new HotelBookingSystem();
		Scanner sc = null;
		
		Hotel hotel;
		LocalDate date;
		String name;
		int days;
		int[] type;
		Booking booking;

		try {
			sc = new Scanner(new File(args[0]));    // args[0] is the first command line argument
			// Read input from the scanner here
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] input = str.split(" ");


				switch (input[0]) {
				
				case "Hotel":
					hotel = sys.getHotel(input[1]);
					if (hotel == null) hotel = sys.newHotel(input[1]);
					hotel.newRoom(input[2], Integer.parseInt(input[3]));
					break;

				case "Booking":
					name = input[1];
					date = sys.extractDate(input);
					days = Integer.parseInt(input[4]);
					type = sys.extractType(input);

					booking = sys.newBooking(name, date, days, type);
					//  Make Booking;
					if (booking == null) {
						System.out.println("Booking rejected");
						break;
					}
					System.out.println("Booking " + booking);
					break;

				case "Change":
					name = input[1];
					date = sys.extractDate(input);
					days = Integer.parseInt(input[4]);
					type = sys.extractType(input);
					Booking oldBooking = sys.getBooking(name);
					if (oldBooking == null) {
						System.out.println("Change rejected");
						break;
					}
					sys.suspendBooking(oldBooking);
					booking = sys.newBooking(name, date, days, type);
					if (booking == null) {
						// revert old booking
						sys.revertBooking(oldBooking);
						System.out.println("Change rejected");
						break;
					}
					System.out.println("Change " + booking);
					break;

				case "Cancel":
					// remove all bookings with name
					name = input[1];
					booking = sys.getBooking(name);
					if (booking == null) {
						System.out.println("Cancel rejected");
						break;
					}
					sys.suspendBooking(booking);
					System.out.println("Cancel " + name);
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
		catch (FileNotFoundException e)
	      {
	          System.out.println(e.getMessage());
	      }
		finally
		{
			if (sc != null) sc.close();
		}
	}
}
