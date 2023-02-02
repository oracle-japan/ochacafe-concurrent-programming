package com.oracle.jp;

import io.helidon.webserver.Routing.Rules;

import java.time.Duration;

import io.helidon.common.http.DataChunk;
import io.helidon.common.reactive.Single;
import io.helidon.webclient.WebClient;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class ClientService implements Service {

    @Override
    public void update(Rules rules) {
        rules.get("/say", this::say);
    }

    private void say(ServerRequest req, ServerResponse res) {
        WebClient webClient = WebClient
                .builder()
                .baseUri("http://cowweb-helidon:8080")
                .build();
        webClient
                .get()
                .path("/cowsay/say")
                .request(String.class)
                .map(it -> it)
                .onError(res::send)
                .forSingle(res::send);
    }

}
