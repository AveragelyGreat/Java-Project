package tbs.objects;

public class Ticket {
	private String _ticketID;
	private String _seatPosition;
	private int _ticketCost;

	//Ticket constructor
	public Ticket(String ticketID, int rowNumber, int seatNumber, int ticketCost) {
		_ticketID = ticketID;
		//Finds the position of the seat for the ticket to store
		_seatPosition = Integer.toString(rowNumber) + "\t" + Integer.toString(seatNumber);
		_ticketCost = ticketCost;
	}
	
	public String get_ticketID() {
		return _ticketID;
	}

	public int get_ticketCost() {
		return _ticketCost;
	}

	public String get_seatPosition() {
		return _seatPosition;
	}
}
