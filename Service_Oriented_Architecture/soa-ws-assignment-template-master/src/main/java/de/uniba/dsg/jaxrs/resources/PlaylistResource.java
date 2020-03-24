package de.uniba.dsg.jaxrs.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;


import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.interfaces.PlaylistApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Interpret;
import de.uniba.dsg.models.Playlist;
import de.uniba.dsg.models.PlaylistRequest;
import de.uniba.dsg.models.Song;

@Path("playlists")
public class PlaylistResource implements PlaylistApi{

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createPlaylist(PlaylistRequest request) {
		
		final Logger logging = Logger.getLogger(PlaylistResource.class.getName());
		
				logging.info(request.toString());
		
			
				  try{
					    List<String> Ids =request.getArtistIds();
			            ArtistResource ArtistsResources = new ArtistResource();
			            HashMap<String, List<Song>> map = new HashMap<String, List<Song>>();
			            List<Song> songs = new ArrayList<>();
			            int defaultPlaylistSize = 10;
			            int count=0;
			            logging.info(Ids.toString());
			           
				    	    for(String Id: Ids) {
				    	    	if(count==0) {
				    	    	map.put(Id, new ArrayList<Song>());
				    	    	map.get(Id).addAll(ArtistsResources.getTopTracks(Id));
				    	    	songs.addAll(ArtistsResources.getTopTracks(Id));
				    	    	}
				    	    	if(!(songs.size()==defaultPlaylistSize)) {
					    	    	Interpret similartracks=ArtistsResources.getSimilarArtist(Id);
					    	    	map.get(Id).addAll(ArtistsResources.getTopTracks(similartracks.getId()));	
					    	    }
				    	    	count++;
				    	    } 
				    	    
				    	    logging.info(map.toString());
				    	    logging.info(songs.toString());
			    	        logging.info("1111");
					      
					        Playlist playlist = new Playlist();
					        playlist.setTitle(request.getTitle());
				            int songCounter=0;
				          
					      for(Map.Entry<String, List<Song>> entry : map.entrySet()) {
					    	  //logging.info("msg: "+entry.getValue().get(0));
					    	  //logging.info("msg: "+entry.getValue());
					    	  //logging.info("msg"+entry.getValue().size());
					    	  songCounter=entry.getValue().size();
					    	  playlist.setTracks(entry.getValue());
					    	  
					      }
					      
					      playlist.setNumoftracks(songCounter);
					      logging.info("mapSize"+ map.entrySet().size());
					      logging.info("2222");
					        
				            logging.info(playlist.getTitle());
				            for(Song song : playlist.getTracks()) {
				            	logging.info("Song: "+song.getArtist());
				            }
				return Response.status(Response.Status.CREATED.getStatusCode()).entity(playlist).build();
					 	    
				  }
				  catch(Exception e){
					  throw new ClientRequestException(new ErrorMessage("failing due to some exception"));
				  }
				    	    
				  	  
			                
			  }
}
	       	    

////	    Ids.add("53XhwfbYqKCa1cC15pYq2q");
////	    Ids.add("3Av8xosLvxNfsrYjPWAGMP");
////	    Ids.add("4gzpq5DPGxSnKTe4SA8HAU");
////	    Ids.add("1AZ30JnvQU1pbX6sbRE0Yn");

	

	

