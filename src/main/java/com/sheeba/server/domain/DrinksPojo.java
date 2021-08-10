package com.sheeba.server.domain;

import java.util.Objects;

public class DrinksPojo {
    private String productName;
    private int stock;
    private double cost;

    public DrinksPojo(String productName, int stock, double cost) {
        this.productName = productName;
        this.stock = stock;
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public double getCost() {
        return cost;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "DrinksPojo{" +
                "productName='" + productName + '\'' +
                ", stock=" + stock +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinksPojo that = (DrinksPojo) o;
        return stock == that.stock && Double.compare(that.cost, cost) == 0 && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, stock, cost);
    }
}


