package com.sheeba.server.service;

import com.sheeba.server.domain.OnlyDrinksPojo;
import com.sheeba.server.domain.ProductNotFoundException;
import com.sheeba.server.repository.DrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendingMachineService {
    public static final String PRODUCT_NOT_FOUND = " product Not found";
    public final DrinksRepository drinksRepository;

    @Autowired
    public VendingMachineService(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    public String addDrink(OnlyDrinksPojo drinksPojo) {
        OnlyDrinksPojo drink = drinksRepository.save(drinksPojo);
        String name = drink.getProductName();
        return name + " success";
    }

    public List<OnlyDrinksPojo> getDrinks() {
        List<OnlyDrinksPojo> drinks = new ArrayList<>();
        Iterable<OnlyDrinksPojo> iterable = drinksRepository.findAll();
        for (OnlyDrinksPojo drink : iterable) {
            drinks.add(drink);
        }
        return drinks;
    }

    public OnlyDrinksPojo getDrink(String productName) {
        Optional<OnlyDrinksPojo> drink = drinksRepository.findByProductName(productName);
        if (drink.isPresent()) {
            return drink.get();
        }
        return null;
    }

    public String buyDrinks(String productName, double cost) throws ProductNotFoundException {
        Optional<OnlyDrinksPojo> drink = drinksRepository.findByProductName(productName);
        if (!drink.isPresent()) {
            throw new ProductNotFoundException(productName + PRODUCT_NOT_FOUND);
        } else if (drink.get().getStock() <= 0) {
            return "no stock";
        } else if (cost < drink.get().getCost()) {
            return "invalid amount";
        } else if (drink.get().getStock() > 0 && cost >= drink.get().getCost()) {
            int reduce = 0;
            int increase = 0;
            reduce = drink.get().getStock() - 1;
            increase = drink.get().getSold() + 1;
            OnlyDrinksPojo drinksPojo = drink.get();
            drinksPojo.setStock(reduce);
            drinksPojo.setSold(increase);
            drinksRepository.save(drinksPojo);
            return drink.get().getProductName() + " delivered";
        }
        throw new ProductNotFoundException(productName + PRODUCT_NOT_FOUND);
    }

    public String dispense(String productName, double cost) throws ProductNotFoundException {
        Iterable<OnlyDrinksPojo> iterable = drinksRepository.findAll();
        for (OnlyDrinksPojo drink : iterable) {
            String name = drink.getProductName();
            double rate = drink.getCost();
            int stock = drink.getStock();
            int sold = drink.getSold();
            if (productName.equals(name) && cost >= rate && stock > 0) {
                int reduce = 0;
                int increase = 0;
                reduce = stock - 1;
                increase = sold + 1;
                drink.setStock(reduce);
                drink.setSold(increase);
                return productName;
            } else if (productName.equals(name) && cost < rate && stock < 0) {
                throw new ProductNotFoundException(productName + PRODUCT_NOT_FOUND);
            }
        }
        throw new ProductNotFoundException(productName + PRODUCT_NOT_FOUND);
    }

    public List<String> totalStock() {
        List<String> output = new ArrayList<>();
        Iterable<OnlyDrinksPojo> iterable = drinksRepository.findAll();
        for (OnlyDrinksPojo drink : iterable) {
            String name = drink.getProductName();
            int stock = drink.getStock();
            output.add(name + " : " + stock);
        }
        return output;
    }

    public String addStock(String productName, int stock) throws ProductNotFoundException {
        Optional<OnlyDrinksPojo> drink = drinksRepository.findByProductName(productName);
        if (drink.isPresent()) {
            int addStock = 0;
            int actualStock = drink.get().getStock();
            addStock = actualStock + stock;
            OnlyDrinksPojo drinks = drink.get();
            drinks.setStock(addStock);
            drinksRepository.save(drinks);
            return productName + " : " + addStock;
        }
        throw new ProductNotFoundException(productName + PRODUCT_NOT_FOUND);
    }

    @GetMapping("/new/getCost")
    public List<String> getCost() {
        List<String> output = new ArrayList<>();
        Iterable<OnlyDrinksPojo> iterable = drinksRepository.findAll();
        for (OnlyDrinksPojo drink : iterable) {
            String name = drink.getProductName();
            double cost = drink.getCost();
            output.add(name + " : " + cost);
        }
        return output;
    }

    public List<String> getSold() {
        List<String> output = new ArrayList<>();
        Iterable<OnlyDrinksPojo> iterable = drinksRepository.findAll();
        for (OnlyDrinksPojo drink : iterable) {
            String name = drink.getProductName();
            int sold = drink.getSold();
            output.add(name + " : " + sold);
        }
        return output;
    }

}
