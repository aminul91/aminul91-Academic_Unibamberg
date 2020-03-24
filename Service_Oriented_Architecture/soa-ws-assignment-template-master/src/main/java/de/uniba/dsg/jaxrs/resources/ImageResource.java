package de.uniba.dsg.jaxrs.resources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

import de.uniba.dsg.CustomSpotifyApi;
import de.uniba.dsg.jaxrs.exceptions.ClientRequestException;
import de.uniba.dsg.jaxrs.exceptions.RemoteApiException;
import de.uniba.dsg.models.ErrorMessage;

/**
 * 
 * @author noman
 *
 *
 * Resource is available at /cover-art/{trackID}
 * 
 */

@Path("cover-art")
@Produces("image/png")
public class ImageResource{
	
	@Path("{trackID}")
	@GET
	public byte[] getCoverArt(@PathParam("trackID") String trackID) {
		
		if(trackID == null) {
			throw new ClientRequestException(new ErrorMessage("No track ID provided"));
		}
		
		Track track = null;
		
		GetTrackRequest request = CustomSpotifyApi.getInstance().getTrack(trackID).build();
		try {
			track = request.execute();
			
			if(track.getAlbum().getImages().length > 0) {
				String imageUrl = track.getAlbum().getImages()[0].getUrl();
				URL url = new URL(imageUrl);
				InputStream inputStream = url.openStream();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				byte[] b = new byte[2048];
				BufferedImage image = new BufferedImage(1000, 1000, 1);
			    int length;

			    while ((length = inputStream.read(b)) != -1) {
			    	byteArrayOutputStream.write(b, 0, length);
			    }
			    
			    ImageIO.write(image, "png", byteArrayOutputStream);
			    byte[] imageData = byteArrayOutputStream.toByteArray();

			    return imageData;
			}
			else {
				throw new ResourceFinderException("No cover art found for the particular track ID");
			}		    
		} catch (SpotifyWebApiException e) {
			throw new RemoteApiException(new ErrorMessage("Spotify wasn't able to find any track for the specified ID"));
		} catch (IOException e) {
			throw new ClientRequestException(new ErrorMessage("Some network error occurred while requesting"));
		}
	}
}
