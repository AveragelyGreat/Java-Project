package tbs.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import tbs.collections.ActMap;
import tbs.collections.ArtistMap;
import tbs.collections.PerformanceMap;
import tbs.collections.TheatreMap;
import tbs.objects.Act;
import tbs.objects.Artist;
import tbs.objects.Performance;
import tbs.objects.Theatre;
import tbs.objects.Ticket;

public class TBSServerImpl implements TBSServer {

	//Initialise maps used to store objects
	TheatreMap theatres = new TheatreMap();
	ArtistMap artists = new ArtistMap();
	ActMap acts = new ActMap();
	PerformanceMap performances = new PerformanceMap();

	public String initialise(String path) {
		if(path != null && !path.isEmpty()) {
			//Get the file from the path
			File file = new File(path);
			//Used to check if the file is found.
			try {
				//Gets the input from the file and loops through it line by line
				Scanner inputStream = new Scanner(file);
				while (inputStream.hasNext()) {
					String data = inputStream.nextLine();
					//Checks the format of the data
					if (data.matches("THEATRE\t.*\t[0-9]+\t[0-9]+")) {
						//Separates the data then constructs the theatre and adds it to the server
						String[] separatedData = data.split("\t");
						Theatre theatre = new Theatre(separatedData[1], Integer.parseInt(separatedData[2]),
							Integer.parseInt(separatedData[3]));
						theatres.addTheatreToMap(theatre);
					} else {
						inputStream.close();
						return "ERROR Incorrect format";
					}
				}
				inputStream.close();
			} catch (FileNotFoundException e) {
				return "ERROR File not found";
			}
		} else {
			return "ERROR path is empty";
		}
		return "";
	}

	public List<String> getTheatreIDs() {
		//Takes the IDs from the theatres collection then sorts it
		List<String> outputIDs = theatres.getTheatreIDs();
		Collections.sort(outputIDs);
		return outputIDs;
	}

	public List<String> getArtistIDs() {
		//Takes the IDs from the artists collection then sorts it
		List<String> outputIDs = artists.getArtistIDs();
		Collections.sort(outputIDs);
		return outputIDs;
	}

	public List<String> getArtistNames() {
		//Takes the Names from the artists collection then sorts it
		List<String> outputIDs = artists.getArtistNames();
		Collections.sort(outputIDs);
		return outputIDs;
	}

	public List<String> getActIDsForArtist(String artistID) {
		//Checks the artist exists
		if (artists.containsArtist(artistID)) {
			//Gets the artist then takes the act IDs from it and sorts them
			Artist artist = artists.getArtist(artistID);
			List<String> outputIDs = artist.get_actIDs();
			Collections.sort(outputIDs);
			return outputIDs;
		} else {
			List<String> Error = Arrays.asList("ERROR No artist with specified ID");
			return Error;
		}
	}

	public List<String> getPeformanceIDsForAct(String actID) {
		//Checks the act exists
		if (acts.containsAct(actID)) {
			//Gets the act then takes the performance IDs from it and sorts them
			Act act = acts.getAct(actID);
			List<String> outputIDs = act.get_performanceIDs();
			Collections.sort(outputIDs);
			return outputIDs;
		} else {
			List<String> Error = Arrays.asList("ERROR No act with specified ID");
			return Error;
		}
	}

	public List<String> getTicketIDsForPerformance(String performanceID) {
		//Checks the performance exists
		if (performances.containsPerformance(performanceID)) {
			//Gets the performance then takes the tickedIDs from it and sorts them
			Performance performance = performances.getPerformance(performanceID);
			List<String> outputIDs = performance.getTicketIDs();
			Collections.sort(outputIDs);
			return outputIDs;
		} else {
			List<String> Error = Arrays.asList("ERROR No performance with specified ID");
			return Error;
		}
	}

	public String addArtist(String name) {
		//Checks the name isn't empty and that no one with that name already exists
		if (name != null && !name.isEmpty()) {
			if (artists.containsName(name)) {
				return "ERROR Artist already exists";
			}
			//Creates the ID using AR (for artist) and the next number in the count of artists
			String ID = "AR" + Integer.toString(artists.getSize() + 1);
			//Creates the artist and adds it to the server
			Artist artist = new Artist(name, ID);
			artists.addArtistToMap(artist);
			return ID;
		} else {
			return "ERROR Name is empty";
		}
	}

	public String addAct(String title, String artistID, int minutesDuration) {
		//Checks the title and artistID aren't empty then if the artist exists.
		if ((title != null && !title.isEmpty()) && (artistID != null && !artistID.isEmpty())) {
			if (artists.containsArtist(artistID)) {
				//Checks the duration is greater than 0
				if (minutesDuration <= 0) {
					return "ERROR Duration of act is less than or equal to zero";
				}
				//Creates the ID using AC(for act) and the next number in the count of acts
				String ID = "AC" + Integer.toString(acts.getSize() + 1);
				//Creates the act and adds it to the server
				Act act = new Act(ID, title, minutesDuration);
				acts.addActToMap(act);
				//Adds the act ID to the artist
				Artist artist = artists.getArtist(artistID);
				artist.addActToArtist(ID);
				return ID;
			} else {
				return "ERROR No artist with that ID";
			}
		} else {
			return "ERROR Title or artistID is empty";
		}
	}

	public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr,
			String cheapSeatsStr) {
		//Checks the actID and theatreID aren't empty.
		if ((actID != null && !actID.isEmpty()) || (theatreID != null && !theatreID.isEmpty())) {
			//Checks the prices aren't empty and are in the right format
			if (premiumPriceStr != null && premiumPriceStr.matches("\\$[0-9]+")
					&& cheapSeatsStr != null && cheapSeatsStr.matches("\\$[0-9]+" )) {
				//Checks the act and theatre exist
				if (acts.containsAct(actID) && theatres.containsTheatre(theatreID)) {
					//Checks for the correct time format
					if (startTimeStr != null && startTimeStr.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
						//Creates the ID using P(for performance) and the next number in the count of performances
						String ID = "P" + Integer.toString(performances.getSize() + 1);
						//Get the theatre to construct Performance with
						Theatre theatre = theatres.getTheatre(theatreID);
						//Creates the performance and adds it to the server
						Performance performance = new Performance(ID, theatre, startTimeStr, premiumPriceStr, cheapSeatsStr);
						performances.addPerformanceToMap(performance);
						//Adds the performanceID to the act
						Act act = acts.getAct(actID);
						act.addPerformanceToAct(ID);
						return ID;
					} else {
						return "ERROR Start time is invalid";
					}
				} else {
					return "ERROR No act or theatre with that ID";
				}
			} else {
				return "ERROR Prices are invalid";
			}
		} else {
			return "ERROR ActID or theatreID is empty";
		}
	}

	public String issueTicket(String performanceID, int rowNumber, int seatNumber) {
		//Checks the performanceID isn't empty and that the performance exists.
		if (performanceID != null && !performanceID.isEmpty()) {
			if (performances.containsPerformance(performanceID)) {
				//Gets the performance to check dimensions are valid
				Performance performance = performances.getPerformance(performanceID);
				if (performance.containsSeat(rowNumber, seatNumber)) {
					//Finds available seats and checks if the seat is booked
					List<String> availableSeats = performance.availableSeats();
					if(!availableSeats.contains(rowNumber + "\t" + seatNumber)) {
						return "ERROR Seat is already booked";
					}
					//Get the cost of the ticket
					int ticketCost = performance.getPrice(rowNumber);
					//Creates the ID using the performance ID plus TI for ticket and and the next number in the count
					//of tickets for the performance
					String ID = performance.get_performanceID() + "TI" + Integer.toString(performance.ticketListSize() + 1);
					//Creates the ticket and adds it to the performance
					Ticket ticket = new Ticket(ID, rowNumber, seatNumber, ticketCost);
					performance.addTicketToPerformance(ticket);
					return ID;
				} else {
					return "ERROR Invalid seat position";
				}
			} else {
				return "ERROR No performance with that ID";
			}
		} else {
			return "ERROR PerformanceID is empty";
		}
	}

	public List<String> seatsAvailable(String performanceID) {
		//Checks the performance exists
		if (performances.containsPerformance(performanceID)) {
			//Gets the performance to get available seats.
			Performance performance = performances.getPerformance(performanceID);
			return performance.availableSeats();
		} else {
			List<String> Error = Arrays.asList("ERROR No performance with specified ID");
			return Error;
		}
	}

	public List<String> salesReport(String actID) {
		//Checks the actID isn't empty and that the act exists
		if (actID != null && !actID.isEmpty()) {
			if (acts.containsAct(actID)) {
				//gets the act
				Act act = acts.getAct(actID);
				//Creates the output list and gets the performance IDs
				List<String> outputReport = new ArrayList<String>();
				List<String> performanceIDs = act.get_performanceIDs();
				//loops through the performances using the IDs to get the report from each one and add it to the output
				for (String performanceID : performanceIDs) {
					Performance performance = performances.getPerformance(performanceID);
					outputReport.add(performance.performanceSalesReport());
				}
				return outputReport;
			} else {
				List<String> Error = Arrays.asList("ERROR No act with that ID");
				return Error;
			}
		} else {
			List<String> Error = Arrays.asList("ERROR ActID is empty");
			return Error;
		}
	}

	public List<String> dump() {
		return null;
	}

}
