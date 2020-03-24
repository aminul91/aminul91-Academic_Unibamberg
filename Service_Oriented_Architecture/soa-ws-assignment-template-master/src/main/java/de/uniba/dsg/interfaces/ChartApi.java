package de.uniba.dsg.interfaces;

import java.util.List;

import de.uniba.dsg.models.Song;
import de.uniba.dsg.models.Interpret;

public interface ChartApi {
	
	List<Song> getTopSongs(String artistId);

}
