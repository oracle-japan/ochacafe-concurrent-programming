package com.oracle.jp.cowweb;


import com.github.ricksbrown.cowsay.Cowsay;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/cowsay")
@RequestScoped
public class CowsayResource {
    /**
     * Return cowsay's 'say' message.
     *
     * @param message
     * @param cowfile
     * @return Cowsay's 'say' message.
     */
    @GET
    @Path("/say")
    public String say() {
        var params = new String[] { "-f", "cowfile", "Moo!" };
        return Cowsay.say(params);
    }
}
