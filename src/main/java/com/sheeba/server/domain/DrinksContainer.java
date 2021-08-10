package com.sheeba.server.domain;

import java.util.List;

public class DrinksContainer {
    private List<DrinksPojo> drinksPojo;

    public DrinksContainer(List<DrinksPojo> drinksPojo) {
        this.drinksPojo = drinksPojo;
    }

    public List<DrinksPojo> getDrinksPojo() {
        return drinksPojo;
    }

    public void setDrinksPojos(List<DrinksPojo> drinksPojos) {
        this.drinksPojo = drinksPojos;
    }
}
