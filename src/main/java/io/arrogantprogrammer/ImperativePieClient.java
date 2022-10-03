package io.arrogantprogrammer;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "imperative-pies")
public interface ImperativePieClient {

    @GET
    @Path("/veg")
    List<String> getVeg();

    @GET
    @Path("/protein")
    List<String> getProtein();

    @GET
    @Path("/filling")
    List<String> getFilling();

}
