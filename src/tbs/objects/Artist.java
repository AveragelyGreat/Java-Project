package tbs.objects;

import java.util.ArrayList;
import java.util.List;

public class Artist {
	private String _artistName;
	private String _artistID;
	//Because nothing needs to be done with acts inside an Artist besides 
	//getting IDs, so only the IDs are passed to avoid unnecessary code 
	private List<String> _actIDs;
	
	//Artist constructor
	public Artist(String name, String ID) {
		_artistName = name;
		_artistID = ID;
		_actIDs = new ArrayList<String>();
	}

	public String get_artistName() {
		return _artistName;
	}

	public String get_artistID() {
		return _artistID;
	}

	public List<String> get_actIDs() {
		return _actIDs;
	}
	
	public void addActToArtist(String actID) {
		_actIDs.add(actID);
	}
}

