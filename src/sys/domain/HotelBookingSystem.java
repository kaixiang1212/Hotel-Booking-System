package sys.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.AttributedString;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

/**
 * @author KaiXiang
 * Created 11/8/2018
 */
public class HotelBookingSystem {
	private ArrayList<Hotel> Hotels;
	private ArrayList<Booking> Bookings;
	
	public HotelBookingSystem(){
		Hotels = new ArrayList<Hotel>();
		Bookings = new ArrayList<Booking>();
	}
	
	public Hotel newHotel(String hotelName) {
		Hotel hotel = new Hotel(hotelName);
		this.Hotels.add(hotel);
		return hotel;
	}
	
	public void newBooking(String customerName, LocalDate date, Period days, int roomType) {
		//Booking booking = new Booking(customerName, room, days, date);
		//this.Bookings.add(booking);
	}
	
	/**
	 * get hotel via hotelName
	 */
	public Hotel getHotel(String hotelName){
		for (Hotel hotel : Hotels) {
			if (hotel.getHotelName().equals(hotelName)) return hotel;
		}
		return null;
	}

	/**
	 * check if the hotel name is used
	 */
	public boolean isUsedHotelName(String hotelName) {
		if (getHotel(hotelName) == null) return false;
		return true;
	}
	
	@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Hotel Booking System:\n\n" + "Hotel:\n");
			for (Hotel hotel : this.Hotels) {
				sb.append(hotel + "\n");
			}
			sb.append("\n");
			for (Booking booking : this.Bookings) {
				sb.append(booking + "\n");
			}
			return new String(sb);
		}
	
	public static void main (String[] args) {
		HotelBookingSystem sys = new HotelBookingSystem();
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);    // args[0] is the first command line argument
			// Read input from the scanner here
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] input = str.split(" ");
				Hotel hotel;
				switch (input[0]) {
				case "Hotel":
					if (!sys.isUsedHotelName(input[1])) sys.newHotel(input[1]);
					hotel = sys.getHotel(input[1]);
					hotel.addRoom(input[2], Integer.parseInt(input[3]));
					
					System.out.println(hotel);
					break;
				case "Booking":
					break;
				case "Change":
					break;
				case "Cancel":
					break;
				case "Print":
					hotel = sys.getHotel(input[1]);
					System.out.println(hotel);
					break;
				default:
					break;
				}
			}
		}
		finally
		{
			if (sc != null) sc.close();
		}
	}
}
