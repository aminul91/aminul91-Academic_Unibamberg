package de.uniba.dsg.interfaces;

import de.uniba.dsg.models.Interpret;
import de.uniba.dsg.models.Song;

public interface TrackApi {
	
	Song getTracks(String song_title, String artist_name);
	
}
