package com.oracle.jp;

import io.helidon.webserver.Routing.Rules;

import com.github.ricksbrown.cowsay.Cowsay;

import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class CowsayService implements Service {

    @Override
    public void update(Rules rules) {
        rules.get("/say", this::say);
    }

    private void say(ServerRequest req, ServerResponse res) {
        var params = new String[] { "-f", "cowfile", "Moo!" };
        res.send(Cowsay.say(params));
    }
    
}
