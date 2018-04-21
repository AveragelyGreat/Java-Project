package tbs.objects;

import java.util.List;

import tbs.collections.TicketList;

public class Performance {
	private String _performanceID;
	private Theatre _theatre;
	private String _startTime;
	private String _premiumPrice;
	private String _cheapPrice;
	//Used to store all tickets sold for this performance
	private TicketList _tickets;
	
	//Performance constructor
	public Performance(String performanceID, Theatre theatre, String startTime, String premiumPrice, 
			String cheapPrice) {
		_performanceID = performanceID;
		_theatre = theatre;
		_startTime = startTime;
		_premiumPrice = premiumPrice;
		_cheapPrice = cheapPrice;
		_tickets = new TicketList();
	}

	public String get_performanceID() {
		return _performanceID;
	}

	public void addTicketToPerformance(Ticket ticket) {
		_tickets.addTicketToTicketList(ticket);
	}
	
	public List<String> getTicketIDs() {
		return _tickets.getTicketIDsFromTicketList();
	}
	
	public int ticketListSize() {
		return _tickets.getTicketListSize();
	}
	
	public Boolean containsSeat(int rowNumber, int seatNumber) {
		return _theatre.containsSeat(rowNumber, seatNumber);
	}
	
	public int getPrice(int rowNumber) {
		//Checks if the seat is premium or cheap then outputs the price by first converting it to an integer
		if (rowNumber <= Math.floor(_theatre.get_seatDimension()/2)) {
			String outputPrice = _premiumPrice.replace("$", "");
			return Integer.parseInt(outputPrice);
		} else {
			String outputPrice = _cheapPrice.replace("$", "");
			return Integer.parseInt(outputPrice);
		}
	}
	
	public List<String> availableSeats() {
		//Creates a list with all seats in the theatre
		List<String> availableSeats = _theatre.getSeats();
		//Removes seats which are taken
		List<String> takenSeats = _tickets.getTakenSeats();
		availableSeats.removeAll(takenSeats);
		return availableSeats;
	}

	//Creates the sales report for the performance
	public String performanceSalesReport() {
		String ticketsSold = Integer.toString(_tickets.getTicketListSize());
		//Gets the combined price of all tickets sold and converts it into a string of the correct format
		int totalPrice = _tickets.getTotalPrice();
		String totalPriceStr = "$" + Integer.toString(totalPrice);
		return _performanceID + "\t" + _startTime + "\t" + ticketsSold + "\t" + totalPriceStr;
	}
}
