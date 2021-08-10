package com.sheeba.server.controller;

import com.sheeba.server.domain.DrinksPojo;
import com.sheeba.server.domain.ProductNotFoundException;
import com.sheeba.server.domain.VendingMachinePojo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class VendingMachineRestController {
    VendingMachinePojo vendingMachinePojo = new VendingMachinePojo();

    @PostMapping("/addDrink")
    public String addDrink(@RequestBody DrinksPojo drinks) {
        boolean isAddSuccessful = vendingMachinePojo.addDrink(drinks);
        if (isAddSuccessful) {
            return drinks.getProductName() + " success";
        } else {
            return drinks.getProductName() + " not success";
        }
    }

    @GetMapping("/getDrinks1")
    public Set<DrinksPojo> getDrinks() {
        return vendingMachinePojo.getDrinksPojoSet();
    }

    @GetMapping("/getDrink2")
    public DrinksPojo getDrink(@RequestParam String productName) {
        Set<DrinksPojo> drinksPojo = vendingMachinePojo.getDrinksPojoSet();
        for (DrinksPojo drinks : drinksPojo) {
            String name = drinks.getProductName();
            if (name.equals(productName)) {
                return drinks;
            }
        }
        return null;
    }

    @GetMapping("/dispense")
    public String dispense(@RequestParam String productName, @RequestParam double cost) throws ProductNotFoundException {
        Set<DrinksPojo> drinks = vendingMachinePojo.getDrinksPojoSet();
        for (DrinksPojo drink : drinks) {
            String name = drink.getProductName();
            double totalCost = drink.getCost();
            int stock = drink.getStock();
            if (productName.equals(name) && (cost >= totalCost) && (stock > 0)) {
                int reduce = stock - 1;
                drink.setStock(reduce);
                return productName;
            } else if (productName.equals(name) && (cost < totalCost) && (stock == 0)) {
                throw new ProductNotFoundException(productName + " not found");
            }
        }
        throw new ProductNotFoundException(productName + " not found");
    }

    @GetMapping("/totalStock")
    public List<String> totalStock() {
        Set<DrinksPojo> drinks = vendingMachinePojo.getDrinksPojoSet();
        List<String> totalStock = new ArrayList<>();
        for (DrinksPojo drink : drinks) {
            String name = drink.getProductName();
            int stock = drink.getStock();
            totalStock.add(name + " : " + stock);
        }
        return totalStock;
    }

    @GetMapping("/addStock")
    public String addStock(@RequestParam String productName, @RequestParam int addStock) throws ProductNotFoundException {
        Set<DrinksPojo> drinks = vendingMachinePojo.getDrinksPojoSet();
        for (DrinksPojo drink : drinks) {
            String name = drink.getProductName();
            int stock = drink.getStock();
            if (productName.equals(name)) {
                int add = 0;
                add = stock + addStock;
                drink.setStock(add);
                return productName + " : " + add;
            }
        }
        throw new ProductNotFoundException(productName + " not found");
    }
}