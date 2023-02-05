package com.oracle.jp;

import io.helidon.common.http.Http;
import io.helidon.nima.webserver.WebServer;
import io.helidon.nima.webserver.http.HttpRouting;

public class Main {
    private static final Http.HeaderValue SERVER = Http.Header.create(Http.Header.SERVER, "Nima");

    public static void main(String[] args) {
        WebServer.builder()
            .routing(Main::routing)
            .start();
    }

    static void routing(HttpRouting.Builder rules) {
        rules.addFilter((chain, req, res) -> {
            res.header(SERVER);
            chain.proceed();
        }).register("/cowsay", new CowsayService());
    }

}
