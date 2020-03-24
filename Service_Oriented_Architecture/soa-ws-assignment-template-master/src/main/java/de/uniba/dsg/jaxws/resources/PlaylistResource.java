package de.uniba.dsg.jaxws.resources;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.uniba.dsg.interfaces.PlaylistApiSOAP;
import de.uniba.dsg.jaxws.MusicApiImpl;
import de.uniba.dsg.jaxws.exceptions.MusicRecommenderFault;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Playlist;
import de.uniba.dsg.models.PlaylistRequest;

public class PlaylistResource implements PlaylistApiSOAP{

	@Override
	public Playlist createPlaylist(PlaylistRequest request) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(MusicApiImpl.restServerUri)
				.path("/playlists")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(request));
		
		int responseStatus = response.getStatus();
		
		if (responseStatus == 201) {
			Playlist createdPlaylist = response.readEntity(new GenericType<Playlist>(){});			
			return createdPlaylist;
		} else if (responseStatus == 400) {
			String cause = response.readEntity(ErrorMessage.class).getMessage();
			throw new MusicRecommenderFault("A client side error occurred", cause);
		} else if (responseStatus == 404) {
			String cause = response.readEntity(ErrorMessage.class).getMessage();
			throw new MusicRecommenderFault("The requested resource was not found", cause);
		} else if (responseStatus == 500) {
			String cause = response.readEntity(ErrorMessage.class).getMessage();
			throw new MusicRecommenderFault("An internal server error occurred", cause);
		} else {
			String cause = response.readEntity(ErrorMessage.class).getMessage();
			throw new MusicRecommenderFault("An unknown remote server error occurred", cause);
		}			
		
	}
}
