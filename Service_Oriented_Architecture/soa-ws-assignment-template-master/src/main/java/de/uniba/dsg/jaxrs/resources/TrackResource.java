package de.uniba.dsg.jaxrs.resources;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;
import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.interfaces.TrackApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Song;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("tracks")
public class TrackResource implements TrackApi {

	@Override
	@GET
	@Path("search")
	public Song getTracks(@QueryParam("title") String song_title, @QueryParam("artist") String artist_name) {
		// TODO Auto-generated method stub
		if (song_title == null && artist_name == null) {
			throw new ClientRequestException(new ErrorMessage("The provided song_title and artist_name both are null"));
		}
		Song resultSong = new Song();
		try {
			SearchItemRequest getTrackRequest = CustomSpotifyApi.getInstance()
					.searchItem("track:"+song_title + " artist:" + artist_name, "track").build();
//			"q=name:" + song_title + "%20artist:" + artist_name
			if (song_title == null) {
				throw new ClientRequestException(new ErrorMessage("Required query parameter is missing: Song Title"));
			}

			else if (artist_name == null) {
				throw new ClientRequestException(new ErrorMessage("Required query parameter is missing: artist name"));
			} else {

				SearchResult trackSearchResult = getTrackRequest.execute();
				Paging<Track> pagedTracks = trackSearchResult.getTracks();
				if (pagedTracks.getTotal() > 0) {
					Track[] tracks = pagedTracks.getItems();
					Track track = tracks[0];

					ArtistSimplified[] artists = track.getArtists();

					StringBuilder sb = new StringBuilder("");

					for (ArtistSimplified artist : artists) {
						sb.append(artist.getName()).append(", ");
					}
					String artistName = sb.substring(0, sb.lastIndexOf(","));
					resultSong.setTrackId(track.getId());
					resultSong.setArtist(artistName);
					resultSong.setTitle(track.getName());
					resultSong.setDuration(track.getDurationMs() / 1000);
				} else {
					throw new ResourceFinderException("No matching track found");
				}
			}

		} catch (IOException e) {
			throw new ClientRequestException(new ErrorMessage("Some network error occurred while requesting"));
		} catch (Exception e) {
			throw new ClientRequestException(new ErrorMessage(e.getMessage()));
		}
		return resultSong;
	}

}
