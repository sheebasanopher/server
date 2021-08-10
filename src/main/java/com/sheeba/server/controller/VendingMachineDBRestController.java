package com.sheeba.server.controller;

import com.sheeba.server.domain.OnlyDrinksPojo;
import com.sheeba.server.domain.ProductNotFoundException;
import com.sheeba.server.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendingMachineDBRestController {
    private final VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineDBRestController(VendingMachineService vendingMachineService) {

        this.vendingMachineService = vendingMachineService;
    }

    @PostMapping("/new/addDrink")
    public String addDrink(@RequestBody OnlyDrinksPojo drinksPojo) {
        return vendingMachineService.addDrink(drinksPojo);
    }

    @GetMapping("/new/getDrinks")
    public List<OnlyDrinksPojo> getDrinks() {
        return vendingMachineService.getDrinks();
    }

    @GetMapping("/new/getDrink")
    public OnlyDrinksPojo getDrink(@RequestParam String productName) {
        return vendingMachineService.getDrink(productName);
    }

    @GetMapping("/new/buyDrinks")
    public String buyDrinks(@RequestParam String productName, @RequestParam double cost) throws ProductNotFoundException {
        try {
            return vendingMachineService.buyDrinks(productName, cost);
        } catch (ProductNotFoundException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/new/dispense")
    public String dispense(@RequestParam String productName, @RequestParam double cost) throws ProductNotFoundException {
        try {
            return vendingMachineService.dispense(productName, cost);
        } catch (ProductNotFoundException exception) {
            return exception.getMessage();
        }
    }

    @GetMapping("/new/totalStock")
    public List<String> totalStock() {
        return vendingMachineService.totalStock();
    }

    @GetMapping("/new/addStock")
    public String addStock(@RequestParam String productName, @RequestParam int stock) throws ProductNotFoundException {
        try {
            return vendingMachineService.addStock(productName, stock);
        } catch (ProductNotFoundException exception) {
            return exception.getMessage();
        }
    }

    @GetMapping("/new/getCost")
    public List<String> getCost() {
        return vendingMachineService.getCost();
    }

    @GetMapping("/new/getSold")
    public List<String> getSold() {
        return vendingMachineService.getSold();
    }


}
