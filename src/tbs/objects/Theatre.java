package tbs.objects;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
	private String _theatreID;
	private int _seatDimension;
	private int _floorArea;
	
	//Theatre constructor
	public Theatre(String inputID, int inputDimension, int inputFloorArea) {
		_theatreID = inputID;
		_seatDimension = inputDimension;
		_floorArea = inputFloorArea;
	}

	public String get_theatreID() {
		return _theatreID;
	}

	public int get_seatDimension() {
		return _seatDimension;
	}
	
	public Boolean containsSeat(int rowNumber, int seatNumber) {
		return (rowNumber <= _seatDimension && rowNumber >= 1) && (seatNumber <= _seatDimension && seatNumber >= 1);
	}
	
	public List<String> getSeats(){
		//Creates a list containing the positions of all seats in the theatre
		List<String> seats = new ArrayList<String>();
		for (int i = 1; i <= _seatDimension; i++) {
			for (int j = 1; j <= _seatDimension; j++) {
				seats.add(Integer.toString(i) + "\t" + Integer.toString(j));
			}
		}
		return seats;
	}
}
