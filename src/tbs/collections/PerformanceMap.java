package tbs.collections;

import java.util.HashMap;
import java.util.Map;

import tbs.objects.Performance;

public class PerformanceMap {
	private Map<String, Performance> _performancesMap;
	
	//Map of performances constructor
	public PerformanceMap() {
		_performancesMap = new HashMap<String, Performance>();
	}
	
	public void addPerformanceToMap(Performance performance) {
		//performanceID is used as the key for the performance
		_performancesMap.put(performance.get_performanceID(), performance);
	}
	
	public Performance getPerformance(String performanceID) {
		return _performancesMap.get(performanceID);
	}

	public int getSize() {
		return _performancesMap.size();
	}
	
	public Boolean containsPerformance(String performanceID) {
		return _performancesMap.containsKey(performanceID);
	}
}
