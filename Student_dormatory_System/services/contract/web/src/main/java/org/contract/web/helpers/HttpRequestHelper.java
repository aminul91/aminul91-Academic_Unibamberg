package org.contract.web.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.contract.web.Constants;
import org.contract.web.models.User;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HttpRequestHelper {
    private static final Client client = ClientBuilder.newClient(new ClientConfig(
            new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    ));

    public static User validateAccessToken(String accessToken) throws Exception {
        try {
            String resourcePath = String.format("%s/%s/accessToken/{accessToken}/validation", ConfigHelper.getAuthServiceUrl(), Constants.AUTH_SERVICE_AUTHENTICATION_RESOURCE_PATH);

            Response response = client.target(resourcePath)
                    .resolveTemplate("accessToken", accessToken)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();

            if (response.getStatus() == 200) {
                return response.readEntity(User.class);
            }

            return null;
        } catch (ProcessingException e) {
            throw new Exception("Error validating access token.");
        }
    }

    public static User getUser(String userId, String authHeaderValue) throws Exception {
        try {
            String resourcePath = String.format("%s/%s/{userId}", ConfigHelper.getAuthServiceUrl(), Constants.AUTH_SERVICE_USER_RESOURCE_PATH);

            Response response = client.target(resourcePath)
                    .resolveTemplate("userId", userId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", authHeaderValue)
                    .get();

            if (response.getStatus() == 200) {
                return response.readEntity(User.class);
            }

            return null;
        } catch (ProcessingException e) {
            throw new Exception("Error retrieving user.");
        }
    }
}
