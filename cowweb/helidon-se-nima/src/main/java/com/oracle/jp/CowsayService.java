package com.oracle.jp;

import com.github.ricksbrown.cowsay.Cowsay;

import io.helidon.nima.webserver.http.HttpRules;
import io.helidon.nima.webserver.http.HttpService;
import io.helidon.nima.webserver.http.ServerRequest;
import io.helidon.nima.webserver.http.ServerResponse;

public class CowsayService implements HttpService {

    @Override
    public void routing(HttpRules rules) {
        rules.get("/say", this::say);
    }

    private void say(ServerRequest req, ServerResponse res) {
        var params = new String[] { "-f", "cowfile", "Moo!" };
        res.send(Cowsay.say(params));
    }
    
}
