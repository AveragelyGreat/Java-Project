package tbs.collections;

import java.util.ArrayList;
import java.util.List;

import tbs.objects.Ticket;

public class TicketList {
	private List<Ticket> _tickets;
	
	//List of tickets constructor
	public TicketList() {
		_tickets = new ArrayList<Ticket>();
	}
	
	public void addTicketToTicketList(Ticket ticket) {
		_tickets.add(ticket);
	}
	
	public List<String> getTicketIDsFromTicketList() {
		//Loops through all tickets to get IDs
		List<String> ticketIDs = new ArrayList<String>();
		for (Ticket ticket : _tickets) {
			ticketIDs.add(ticket.get_ticketID());
		}
		return ticketIDs;
	}
	
	public int getTicketListSize() {
		return _tickets.size();
	}
	
	public List<String> getTakenSeats() {
		//Loops through all tickets to get the seat positions
		List<String> takenSeats = new ArrayList<String>();
		for (Ticket ticket : _tickets) {
			takenSeats.add(ticket.get_seatPosition());
		}
		return takenSeats;
	}

	public int getTotalPrice() {
		//Gets the total price of all tickets in the list
		int totalPrice = 0;
		for(Ticket ticket : _tickets) {
			totalPrice += ticket.get_ticketCost();
		}
		return totalPrice;
	}
}
