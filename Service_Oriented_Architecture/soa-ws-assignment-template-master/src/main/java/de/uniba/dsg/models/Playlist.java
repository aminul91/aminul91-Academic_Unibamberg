package de.uniba.dsg.models;

import java.util.List;

/**
 * TODO:
 * Playlist attributes should be
 * - title:String
 * - size:int
 * - tracks:List<Song>
 */
public class Playlist {
	
	private String title;
	private int numoftracks;
	private List<Song> tracks;

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public int getNumoftracks() {
		return numoftracks;
	}
	public void setNumoftracks(int numoftracks) {
		this.numoftracks = numoftracks;
	}

	public List<Song> getTracks() {
		return tracks;
	}
	public void setTracks(List<Song> tracks) {
		this.tracks = tracks;
	}
	

}