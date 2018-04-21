package tbs.collections;

import java.util.HashMap;
import java.util.Map;

import tbs.objects.Act;

public class ActMap {
	private Map<String, Act> _actsMap;
	
	//Map of acts constructor
	public ActMap() {
		_actsMap = new HashMap<String, Act>();
	}
	
	public void addActToMap(Act act) {
		//ActID is used as the key for the act
		_actsMap.put(act.get_actID(), act);
	}
	
	public Act getAct(String actID) {
		return _actsMap.get(actID);
	}

	public int getSize() {
		return _actsMap.size();
	}
	
	public Boolean containsAct(String actID) {
		return _actsMap.containsKey(actID);
	}
}
