package de.uniba.dsg.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * TODO:
 * PlaylistRequest attributes should be
 * - title:String
 * - artistSeeds:List<String>, must be serialized as 'seeds'
 * - numberOfSongs:int, must be serialized as 'size'
 */
@XmlType(propOrder = {"title", "artistIds","sizeOfPlayList"})
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaylistRequest {

	
	private String title = "";
    @XmlAttribute(name = "seeds")
    private List<String> artistIds = new ArrayList<String>();
    @XmlElement(name = "size")
    private int sizeOfPlayList = 0;

	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getArtistIds() {
		return artistIds;
	}
	public void setArtistIds(List<String> artistIds) {
		this.artistIds = artistIds;
	}
	public int getSizeOfPlayList() {
		return sizeOfPlayList;
	}
	public void setSizeOfPlayList(int sizeOfPlayList) {
		this.sizeOfPlayList = sizeOfPlayList;
	}
	
	
}
