package com.oracle.jp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@RestController
public class ClientController {
    private final RestTemplate restTemplate;

    @Value(value = "${cowweb.baseUrl}")
    private String baseUrl;

    /**
     * @param restTemplate
     */
    @Autowired
    public ClientController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(path = "/client/say")
    public String say(@RequestParam("say") Optional<String> message,
            @RequestParam("cowfile") Optional<String> cowfile) {
        return restTemplate.getForEntity(baseUrl + "/cowsay/say", String.class).getBody();
    }

    @GetMapping(path = "/client/think")
    public String think(@RequestParam("say") Optional<String> message,
    @RequestParam("cowfile") Optional<String> cowfile) {
        return restTemplate.getForEntity(baseUrl + "/cowsay/think", String.class).getBody();
    }

    @GetMapping(path = "/client/ping")
    public String ping() {
        return baseUrl;
    }

}
