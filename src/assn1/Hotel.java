package assn1;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author kxy12
 * 
 */
public class Hotel {
	private String hotelName;
	private ArrayList<Room> rooms;

	public Hotel(String hotelName) {
		this.hotelName = hotelName;
		rooms = new ArrayList<Room>();
	}

	public void newRoom(String roomNo, String roomType) {
		rooms.add(new Room(roomNo, roomType));
	}

	/**
	 * 
	 * @param date
	 * @param days
	 * @param type
	 * @param quantity
	 * @return true if this hotel has vacancy
	 */
	public boolean hasVacancy(LocalDate date, int days, int type, int quantity) {
		int quantityAvailable = 0;
		for(Room e : rooms) {
			if (e.getRoomType() == days && !e.isBooked(date, days)) {
				quantityAvailable++;
			}
		}
		if (quantityAvailable >= quantity) return true;
		return false;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Room e : rooms) {
			sb.append(getHotelName() + " " + e + "\n");
		}
		return sb.toString();
	}

}
