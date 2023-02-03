package com.oracle.jp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@RestController
public class ClientController {
    @Value(value = "${cowweb.baseUrl}")
    private String baseUrl;

    @GetMapping(path = "/client/say")
    public String say(@RequestParam("say") Optional<String> message,
            @RequestParam("cowfile") Optional<String> cowfile) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(baseUrl + "/client/say")).build();
        try {
            var response = httpClient.sendAsync(request, BodyHandlers.ofString()).get().body();
            return response;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException();
        }
    }

}
