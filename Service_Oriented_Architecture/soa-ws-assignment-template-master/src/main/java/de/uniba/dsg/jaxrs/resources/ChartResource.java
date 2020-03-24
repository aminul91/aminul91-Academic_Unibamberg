package de.uniba.dsg.jaxrs.resources;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;


import de.uniba.dsg.interfaces.ChartApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Song;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("charts")
public class ChartResource implements ChartApi{

	@Override
	@GET
	@Path("{artistId}")
	public List<Song> getTopSongs(@PathParam("artistId") String artistId) {	
		if (artistId == null) {
			throw new ClientRequestException(new ErrorMessage("Required query parameter is missing: artistId"));
		}
		try{
			List<Song> songs = new ArrayList<>();
			 ArtistResource ArtistsResources = new ArtistResource();
			 songs.addAll(ArtistsResources.getTopTracks(artistId));			 
			 if(songs.isEmpty()) {
				 throw new ResourceFinderException("No top tracks found for the specified Artist");
			 }
			 else {
				 return songs; 
			 }	 
		}
		 catch(Exception e){
			  throw new ClientRequestException(new ErrorMessage(e.getMessage()));
		  }
	}	
}
