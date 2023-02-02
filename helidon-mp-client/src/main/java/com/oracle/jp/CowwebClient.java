package com.oracle.jp;

import java.util.Optional;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/cowsay")
@RegisterRestClient(baseUri = "http://cowweb-helidon:8080")
public interface CowwebClient {

    @GET
    @Path("/say")
    public String say(@QueryParam("say") Optional<String> message, @QueryParam("cowfile") Optional<String> cowfile);

    @GET
    @Path("/think")
    public String think(@QueryParam("think") Optional<String> message, @QueryParam("cowfile") Optional<String> cowfile);

    @GET
    @Path("/ping")
    public String ping();
}
