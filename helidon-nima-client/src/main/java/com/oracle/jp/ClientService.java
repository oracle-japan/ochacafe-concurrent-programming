package com.oracle.jp;

import io.helidon.nima.webclient.http1.Http1Client;
import io.helidon.nima.webserver.http.HttpRules;
import io.helidon.nima.webserver.http.HttpService;
import io.helidon.nima.webserver.http.ServerRequest;
import io.helidon.nima.webserver.http.ServerResponse;

public class ClientService implements HttpService{

    private static Http1Client client;

    static void client(Http1Client client) {
        ClientService.client = client;
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/say", this::say);
    }

    private void say(ServerRequest req, ServerResponse res) {
        String result = client.get().uri("/cowsay/say").request(String.class);
        res.send(result);
    }
    
}
