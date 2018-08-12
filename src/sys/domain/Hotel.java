package sys.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 * @author kxy12
 *
 */
public class Hotel {
	private String hotelName;
	private ArrayList<Room> Rooms;
	
	protected Hotel(String name) {
		this.hotelName = name;
		this.Rooms = new ArrayList<Room>();
	}
	
	public Room addRoom(String roomNo, int roomType) {
		Room newRoom = new Room(this, roomNo, roomType);
		this.Rooms.add(newRoom);
		return newRoom;
	}
	
	/**
	 * check if the roomType is all available in a hotel;
	 * @param date
	 * @param days
	 * @param type1
	 * @return
	 */
	public boolean isAvailable (LocalDate date, Period days, int type1) {
		if (getAvailableRoom(date, days, type1) != null) return true;
		else return false;
	}	
	public boolean isAvailable (LocalDate date, Period days, int type1, int type2) {
		return isAvailable(date, days, type1) &&
				isAvailable(date, days, type2);
	}
	
	public boolean isAvailable (LocalDate date, Period days, int type1, int type2, int type3) {
		return isAvailable(date, days, type1, type2) &&
				isAvailable(date, days, type3);
	}
	
	public Room getAvailableRoom(LocalDate date, Period days, int type) {
		for (Room room : Rooms) {
			if (room.getType() == type && !room.isBooked(date, days)) {
				return room;
			}
		}
		return null;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return Rooms;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Hotel Name: "+ getHotelName() + "\n");
		for (Room r : Rooms) {
			sb.append(r + "\n");
		}
		return new String(sb);
	}
	
}
