package com.oracle.jp;

import io.helidon.common.http.Http;
import io.helidon.config.Config;
import io.helidon.nima.webclient.http1.Http1Client;
import io.helidon.nima.webserver.WebServer;
import io.helidon.nima.webserver.http.HttpRouting;

public class NimaMain {
    private static final Http.HeaderValue SERVER = Http.Header.create(Http.Header.SERVER, "Nima");

    public static void main(String[] args) {
        WebServer ws = WebServer.builder()
                .routing(NimaMain::routing)
                .start();
        
        Config config = Config.create();

        // String baseUrl = config.get("http://cowweb-helidon:8080").asString().get();

        ClientService.client(Http1Client.builder()
                .baseUri("http://cowweb-helidon:8080")
                .build());
    }

    static void routing(HttpRouting.Builder rules) {
        rules.addFilter((chain, req, res) -> {
            res.header(SERVER);
            chain.proceed();
        }).register("/client", new ClientService());
    }

}
