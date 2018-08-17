package assn1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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
		if (quantity == 0) return true;
		int quantityAvailable = 0;
		for(Room e : rooms) {
			if (e.getRoomType() == type && !e.isBooked(date, days)) {
				quantityAvailable++;
			}
		}
		if (quantityAvailable >= quantity) return true;
		return false;
	}

	/**
	 * Get the rooms available for booking
	 * @param date
	 * @param days
	 * @param type
	 * @return
	 */
	public ArrayList<Room> getAvailableRoom(LocalDate date, int days, int[] type) {
		ArrayList<Room> rooms = new ArrayList<Room>();

		int[] typeLeft = Arrays.copyOf(type, 3);

		for (Room e : this.rooms) {
			if (!e.isBooked(date, days) && typeLeft[e.getRoomType()-1] != 0) {
				rooms.add(e);
				typeLeft[e.getRoomType()-1]--;
			}
		}
		return rooms;
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
