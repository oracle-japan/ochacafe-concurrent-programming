package com.oracle.jp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

import io.helidon.config.Config;
import io.helidon.nima.webserver.http.HttpRules;
import io.helidon.nima.webserver.http.HttpService;
import io.helidon.nima.webserver.http.ServerRequest;
import io.helidon.nima.webserver.http.ServerResponse;

public class ClientService implements HttpService {

    @Override
    public void routing(HttpRules rules) {
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
