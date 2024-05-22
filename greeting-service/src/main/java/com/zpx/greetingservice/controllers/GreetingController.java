package com.zpx.greetingservice.controllers;

import com.zpx.greetingservice.configuration.GreetingConfiguration;
import com.zpx.greetingservice.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration configuration;


    public Greeting greeting (@RequestParam(value = "name", defaultValue = "")String name) {

        if (name.isEmpty()) {
            name = configuration.getDefaultValue();
        }

        return new Greeting(counter.incrementAndGet(),String.format(template, configuration.getGreeting(), name));
    }
}
