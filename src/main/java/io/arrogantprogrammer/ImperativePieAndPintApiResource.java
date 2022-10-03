package io.arrogantprogrammer;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/imperative")
public class ImperativePieAndPintApiResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImperativePieAndPintApiResource.class);

    @Inject @RestClient
    ImperativePieClient pieClient;

    @Inject @RestClient
    ImperativePintClient pintClient;

    @GET
    public String randomPieAndPint() {

        Beer beer = allBeers().get(new Random().nextInt(allBeers().size() - 1));
        return "%s with %s and %s pie served with a pint of %s".formatted(
                pieClient.getProtein().get(new Random().nextInt(pieClient.getProtein().size() - 1)),
                pieClient.getVeg().get(new Random().nextInt(pieClient.getVeg().size() - 1)),
                pieClient.getFilling().get(new Random().nextInt(pieClient.getProtein().size() - 1)),
                beer.name + " " + beer.tagline
        );
    }

    List<Beer> allBeers() {
        int i = 1;
        boolean morePages = true;
        List<Beer> allBeers = new ArrayList<>();
        while (morePages) {
            List<Beer> beers = pintClient.getBeersPage(i);
            if (beers.isEmpty()) {
                morePages = false;
            }else {
                allBeers.addAll(beers);
                i++;
            }
        }
        LOGGER.info("returning {} beers", allBeers.size());
        return allBeers;
    }


}
