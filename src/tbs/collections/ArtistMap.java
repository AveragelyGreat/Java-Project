package tbs.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import tbs.objects.Artist;

public class ArtistMap {
	private Map<String, Artist> _artistsMap;
	
	//Map of artists constructor
	public ArtistMap() {
		_artistsMap = new HashMap<String, Artist>();
	}
	
	public void addArtistToMap(Artist artist) {
		//artistID is used as the key for the artist
		_artistsMap.put(artist.get_artistID(), artist);
	}
	
	public Artist getArtist(String artistID) {
		return _artistsMap.get(artistID);
	}
	
	public List<String> getArtistIDs() {
		List<String> artistIDs = new ArrayList<String>(_artistsMap.keySet());
		return artistIDs;
	}
	
	public List<String> getArtistNames() {
		//Loops through all artists in the map to get the names
		List<String> artistNames = new ArrayList<String>();
		for (Artist artist : _artistsMap.values()) {
			artistNames.add(artist.get_artistName());
		}
		return artistNames;
	}

	public int getSize() {
		return _artistsMap.size();
	}
	
	public Boolean containsArtist(String artistID) {
		return _artistsMap.containsKey(artistID);
	}
	
	public Boolean containsName(String name) {
		for (Artist artist : _artistsMap.values()) {
			if (Objects.equals(name.toLowerCase(), artist.get_artistName().toLowerCase())) {
				return  true;
			}
		}
		return false;
	}
}
