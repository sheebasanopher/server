package com.sheeba.server.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendingMachinePojo {
    private List<DrinksPojo> drinksPojoList = new ArrayList<>();
    private Set<DrinksPojo> drinksPojoSet = new HashSet<>();

    public VendingMachinePojo(List<DrinksPojo> drinksPojoList) {
        this.drinksPojoList = drinksPojoList;
    }

    public VendingMachinePojo() {

    }

    public List<DrinksPojo> getDrinksPojoList() {
        return drinksPojoList;
    }

    public Set<DrinksPojo> getDrinksPojoSet() {
        return drinksPojoSet;
    }

    public boolean addDrink(DrinksPojo drinksPojo) {
        return drinksPojoSet.add(drinksPojo);
    }
}