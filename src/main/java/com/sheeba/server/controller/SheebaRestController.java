package com.sheeba.server.controller;

import com.sheeba.server.domain.DrinksContainer;
import com.sheeba.server.domain.DrinksPojo;
import com.sheeba.server.domain.VoterFile;
import com.sheeba.server.domain.VoterPojo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SheebaRestController {

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name, @RequestParam String title) {
        return "Hello " + title + "." + name;
    }

//    @GetMapping("/getVoter")
//    public VoterPojo getVoter(@RequestParam String name) {
//        return new VoterPojo(name, "1", false, 11);
//    }

    @GetMapping("/getDrinks")
    public DrinksPojo getDrinks(@RequestParam String name) {
        return new DrinksPojo(name, 10, 32);
    }

    @GetMapping("/getJuice")
    public DrinksPojo getDrinks(@RequestParam String name, int cost) {
        return new DrinksPojo(name, 10, cost);
    }

    @GetMapping("/getCoolDrinks")
    public DrinksPojo getDrinks(@RequestParam String name, int stock, int cost) {
        return new DrinksPojo(name, stock, cost);
    }

    @GetMapping("/uriCode")
    public String start(@RequestParam String name, int pin) {
        return name + " = " + pin;
    }

    @GetMapping("/call")
    public String web(String name) {
        return "Hello " + name + " ! .";
    }

    @GetMapping("/voteIndia")
    public VoterPojo vote(String name) {
        return new VoterPojo(name, "in3456", false, 24);
    }

    @PostMapping("/isVotingAllowed")
    public String isVotingAllowed(@RequestBody VoterPojo voterPojo) {
        if (voterPojo.getAge() >= 18) {
            return "Voting Allowed";
        } else {
            return "Voting not Allowed";
        }
    }

    @PostMapping("/mrpRate")
    public String cost(@RequestBody DrinksPojo drinks) {
        if (drinks.getCost() >= 10) {
            return drinks.getProductName() + " is found";
        } else {
            return drinks.getProductName() + " is not found";
        }
    }

    @PostMapping("/cost")
    public List<String> multipleDrinks(@RequestBody DrinksContainer drinksContainer) {
        List<DrinksPojo> drinksPojos = drinksContainer.getDrinksPojo();
        List<String> output = new ArrayList<>();
        for (DrinksPojo drinks : drinksPojos) {
            if (drinks.getCost() >= 10) {
                output.add(drinks.getProductName() + " is found");
            } else {
                output.add(drinks.getProductName() + " is not found");
            }
        }
        return output;
    }

    @PostMapping("/voteMe")
    public Map<Integer, List<String>> voters(@RequestBody VoterFile voterFile) {
        List<VoterPojo> voterPojo = voterFile.getVoterPojos();
        Map<Integer, List<String>> output = new HashMap<>();
        for (VoterPojo voter : voterPojo) {
            String name = voter.getName();
            int age = voter.getAge();
            boolean sameAge = output.containsKey(age);
            if (sameAge) {
                List<String> value = output.get(age);
                value.add(name);
                output.put(age, value);
            } else {
                List<String> names = new ArrayList<>();
                names.add(name);
                output.put(age, names);
            }
        }
         return output;
    }
}

