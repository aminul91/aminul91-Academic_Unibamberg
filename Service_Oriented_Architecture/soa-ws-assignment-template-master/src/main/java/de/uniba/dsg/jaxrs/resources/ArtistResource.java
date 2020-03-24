package de.uniba.dsg.jaxrs.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsRelatedArtistsRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;

import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.interfaces.ArtistApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.jaxrs.exceptions.ResourceNotFoundException;
import de.uniba.dsg.models.ErrorMessage;
import de.uniba.dsg.models.Interpret;
import de.uniba.dsg.models.Song;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("artist")
public class ArtistResource implements ArtistApi {

	@Override
	@GET

	@Path("{artist-id}")
	
	public Interpret getArtist(@HeaderParam("referer") String refer, @PathParam("artist-id") String artistId) {

		checkMissingArtistId(artistId);

		GetArtistRequest getArtistRequest = CustomSpotifyApi.getInstance().getArtist(artistId).build();

		try {
			Artist artistSearchResult = getArtistRequest.execute();

			if (artistSearchResult == null) {
				throw new ResourceNotFoundException(
						new ErrorMessage(String.format("No matching artist found for query: %s", artistId)));
			}

			Interpret result = new Interpret();
			result.setId(artistSearchResult.getId());
			result.setName(artistSearchResult.getName());
			result.setPopularity(artistSearchResult.getPopularity());
			result.setGenres(Arrays.asList(artistSearchResult.getGenres()));

			return result;
		} catch (SpotifyWebApiException | IOException e) {
			throw new RemoteApiException(new ErrorMessage(e.getMessage()));
		}
	}

	@Override
	@GET
	@Path("{artist-id}/top-tracks")
	public List<Song> getTopTracks(@PathParam("artist-id") String artistId) {
		// TODO Auto-generated method stub
		checkMissingArtistId(artistId);

		GetArtistsTopTracksRequest topTracksRequest = CustomSpotifyApi.getInstance()
				.getArtistsTopTracks(artistId, CountryCode.DE).build();
		List<Song> topTracksList = new ArrayList<>();

		try {
			Track[] topTracksResult = topTracksRequest.execute();
			if (topTracksResult.length > 5) {
				topTracksResult = Arrays.copyOfRange(topTracksResult, 0, 5);
			}
			if (topTracksResult != null && topTracksResult.length == 0) {
				throw new ResourceNotFoundException(
						new ErrorMessage(String.format("No matching tracks found for query: %s", artistId)));
			}

			for (Track track : topTracksResult) {
				Song song = new Song();
				song.setTitle(track.getName());
				ArtistSimplified[] artists = track.getArtists();

				StringBuilder sb = new StringBuilder("");

				for (ArtistSimplified artist : artists) {
					sb.append(artist.getName()).append(", ");
				}
				String artistName = sb.substring(0, sb.lastIndexOf(","));
				song.setArtist(artistName);
				song.setDuration(track.getDurationMs() / 1000);
				topTracksList.add(song);
			}

		} catch (SpotifyWebApiException | IOException e) {
			throw new RemoteApiException(new ErrorMessage(e.getMessage()));

		}
		return topTracksList;

	}

	@Override
	@GET
	@Path("{artist-id}/similar-artist")
	public Interpret getSimilarArtist(@PathParam("artist-id") String artistId) {
		checkMissingArtistId(artistId);
		GetArtistsRelatedArtistsRequest artistRequest = CustomSpotifyApi.getInstance()
				.getArtistsRelatedArtists(artistId).build();

		try {

			Artist[] artistSearchResult = artistRequest.execute();
			if (artistSearchResult != null && artistSearchResult.length == 0) {
				throw new ResourceNotFoundException(
						new ErrorMessage(String.format("No matching artist found for query: %s", artistId)));
			}

			Artist artist = artistSearchResult[0];
			Interpret result = new Interpret();
			result.setId(artist.getId());
			result.setName(artist.getName());
			result.setPopularity(artist.getPopularity());
			result.setGenres(Arrays.asList(artist.getGenres()));

			return result;
		} catch (SpotifyWebApiException | IOException e) {
			throw new RemoteApiException(new ErrorMessage(e.getMessage()));
		}
		// TODO Auto-generated method stub

	}

	private void checkMissingArtistId(String artistId) {
		if (artistId == null) {
			throw new ClientRequestException(new ErrorMessage("Required query parameter is missing: artistId"));
		}
	}
}
