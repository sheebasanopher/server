package com.sheeba.server.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "drinks")
public class OnlyDrinksPojo {
    @Id
    private int id;
    private String productName;
    private int stock;
    private double cost;
    private int sold;

    public OnlyDrinksPojo(int id, String productName, int stock, double cost, int sold) {
        this.id = id;
        this.productName = productName;
        this.stock = stock;
        this.cost = cost;
        this.sold = sold;
    }

    public OnlyDrinksPojo() {

    }

    public int getId() {
        return id;
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

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "OnlyDrinksPojo{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", stock=" + stock +
                ", cost=" + cost +
                ", sold=" + sold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnlyDrinksPojo that = (OnlyDrinksPojo) o;
        return id == that.id && stock == that.stock && Double.compare(that.cost, cost) == 0 && sold == that.sold && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, stock, cost, sold);
    }
}

