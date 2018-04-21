package tbs.objects;

import java.util.ArrayList;
import java.util.List;

public class Act {
	private String _actID;
	private String _actTitle;
	private int _minDuration;
	private List<String> _performanceIDs;
	
	//Act constructor
	public Act(String inputID, String inputTitle, int duration) {
		_actID = inputID;
		_actTitle = inputTitle;
		_minDuration = duration;
		_performanceIDs = new ArrayList<String>();
	}

	public String get_actID() {
		return _actID;
	}
	
	public void addPerformanceToAct(String performanceID) {
		_performanceIDs.add(performanceID);
	}

	public List<String> get_performanceIDs() {
		return _performanceIDs;
	}
}
