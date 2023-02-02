package com.oracle.jp;

import java.util.Optional;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RequestScoped
@Path("/client")
public class ClientResource {

    private final CowwebClient cowwebClient;

    /**
     * @param cowwebClient
     */
    @Inject
    public ClientResource(@RestClient CowwebClient cowwebClient) {
        this.cowwebClient = cowwebClient;
    }

    @GET
    @Path("/say")
    public String say(@QueryParam("say") Optional<String> message, @QueryParam("cowfile") Optional<String> cowfile) {
        return cowwebClient.say(message, cowfile);
    }

    @GET
    @Path("/think")
    public String think(@QueryParam("say") Optional<String> message, @QueryParam("cowfile") Optional<String> cowfile) {
        return cowwebClient.think(message, cowfile);
    }

}
