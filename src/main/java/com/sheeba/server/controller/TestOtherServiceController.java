package com.sheeba.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TestOtherServiceController {
    private final RestTemplate restTemplate;

    @Autowired
    public TestOtherServiceController() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @GetMapping("new/check")
    public String callClearTripAirport(@RequestParam String airportCode) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.cleartrip.com/places/airports/search")
                .queryParam("string", airportCode);

        return restTemplate.getForEntity(builder.toUriString(), String.class).getBody();
    }
}
