package tbs.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tbs.objects.Theatre;

public class TheatreMap {
	private Map<String, Theatre> _theatresMap;
	
	//Map of theatres constructor
	public TheatreMap() {
		_theatresMap = new HashMap<String, Theatre>();
	}
	
	public void addTheatreToMap(Theatre theatre) {
		//TheatreID is used as the key for the theatre
		_theatresMap.put(theatre.get_theatreID(), theatre);
	}
	
	public Theatre getTheatre(String theatreID) {
		return _theatresMap.get(theatreID);
	}
	
	public List<String> getTheatreIDs() {
		List<String> theatreIDs = new ArrayList<String>(_theatresMap.keySet());
		return theatreIDs;
	}
	
	public Boolean containsTheatre(String theatreID) {
		return _theatresMap.containsKey(theatreID);
	}
}
