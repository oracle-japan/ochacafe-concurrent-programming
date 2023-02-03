package com.oracle.jp;

import io.helidon.webserver.Routing.Rules;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

import io.helidon.config.Config;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class ClientService implements Service {

    @Override
    public void update(Rules rules) {
        rules.get("/say", this::say);
    }

    private void say(ServerRequest req, ServerResponse res) {
        Config config = Config.create();
        String baseUrl = config.get("cowweb.baseUrl").asString().get();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(baseUrl + "/client/say")).build();
        try {
            var response = httpClient.sendAsync(request, BodyHandlers.ofString()).get().body();
            res.send(response);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException();
        }
    }

}
