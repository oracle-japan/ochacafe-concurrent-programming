package com.oracle.jp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ricksbrown.cowsay.Cowsay;

@RestController
public class CowsayController {
    
    @GetMapping(path = "/cowsay/say")
    public String say() {
        var params = new String[] { "-f", "cowfile", "Moo!" };
        return Cowsay.say(params);
    }
}
