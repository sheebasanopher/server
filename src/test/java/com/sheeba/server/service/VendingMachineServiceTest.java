package com.sheeba.server.service;

import com.sheeba.server.domain.OnlyDrinksPojo;
import com.sheeba.server.domain.ProductNotFoundException;
import com.sheeba.server.repository.DrinksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VendingMachineServiceTest {
    public VendingMachineService vendingMachineService;
    public final DrinksRepository drinksRepository = Mockito.mock(DrinksRepository.class);

    @BeforeEach
    public void setup() {
        vendingMachineService = new VendingMachineService(drinksRepository);
    }

    @Test
    public void testTotalStock() {
        OnlyDrinksPojo pepsi = new OnlyDrinksPojo(1, "pepsi", 10, 20, 0);
        OnlyDrinksPojo sprite = new OnlyDrinksPojo(2, "sprite", 10, 26, 0);
        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 10, 80, 0);
        List<OnlyDrinksPojo> drinks = new ArrayList<>();
        drinks.add(pepsi);
        drinks.add(sprite);
        drinks.add(redBull);

        Mockito.when(drinksRepository.findAll()).thenReturn(drinks);

        List<String> output = vendingMachineService.totalStock();

        assertEquals(3, output.size());
    }

    @Test
    public void testBuyDrinks() throws ProductNotFoundException {
        OnlyDrinksPojo pepsi = new OnlyDrinksPojo(1, "pepsi", 10, 20, 0);

        Mockito.when(drinksRepository.findByProductName("pepsi")).thenReturn(Optional.of(pepsi));

        String output = vendingMachineService.buyDrinks("pepsi", 20);

        assertEquals("pepsi delivered", output);
    }

    @Test
    public void testProductNotFoundInBuyDrinks() throws ProductNotFoundException {
        OnlyDrinksPojo sprite = new OnlyDrinksPojo(2, "sprite", 10, 26, 0);

        Mockito.when(drinksRepository.findByProductName("sprite")).thenReturn(Optional.of(sprite));

        Throwable exception = assertThrows(ProductNotFoundException.class, () -> vendingMachineService.buyDrinks("redbull", 43));

        assertEquals("redbull product Not found", exception.getMessage());

    }

    @Test
    public void testNoStockInBuyDrinks() throws ProductNotFoundException {
        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 0, 80, 0);

        Mockito.when(drinksRepository.findByProductName("redBull")).thenReturn(Optional.of(redBull));

        String output = vendingMachineService.buyDrinks("redBull", 89);

        assertEquals("no stock", output);
    }

    @Test
    public void testInvalidAmountInBuyDrinks() throws ProductNotFoundException {
        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 10, 80, 0);
        Mockito.when(drinksRepository.findByProductName("redBull")).thenReturn(Optional.of(redBull));
        String output = vendingMachineService.buyDrinks("redBull", 20);

        assertEquals("invalid amount", output);
    }

    @Test
    public void testAddStock() throws ProductNotFoundException {

        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 0, 80, 0);

        Mockito.when(drinksRepository.findByProductName("redBull")).thenReturn(Optional.of(redBull));

        String output = vendingMachineService.addStock("redBull", 5);

        assertEquals("redBull : 5", output);
    }

    @Test
    public void testNotAddStock() throws ProductNotFoundException {
        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 0, 80, 0);

        Mockito.when(drinksRepository.findByProductName("redBull")).thenReturn(Optional.of(redBull));

        Throwable exception = assertThrows(ProductNotFoundException.class, () -> vendingMachineService.addStock("pepsi", 10));

        assertEquals("pepsi product Not found", exception.getMessage());
    }

    @Test
    public void testGetCost() {
        OnlyDrinksPojo pepsi = new OnlyDrinksPojo(1, "pepsi", 10, 20, 0);
        OnlyDrinksPojo sprite = new OnlyDrinksPojo(2, "sprite", 10, 26, 0);
        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 10, 80, 0);
        List<OnlyDrinksPojo> drinks = new ArrayList<>();
        drinks.add(pepsi);
        drinks.add(sprite);
        drinks.add(redBull);

        Mockito.when(drinksRepository.findAll()).thenReturn(drinks);

        List<String> output1 = vendingMachineService.getCost();

        assertEquals("pepsi : 20.0", output1.get(0));

    }

    @Test
    public void testGetSold() {
        OnlyDrinksPojo pepsi = new OnlyDrinksPojo(1, "pepsi", 10, 20, 5);
        OnlyDrinksPojo sprite = new OnlyDrinksPojo(2, "sprite", 10, 26, 6);
        OnlyDrinksPojo redBull = new OnlyDrinksPojo(3, "redBull", 10, 80, 2);
        List<OnlyDrinksPojo> drinks = new ArrayList<>();
        drinks.add(pepsi);
        drinks.add(sprite);
        drinks.add(redBull);

        Mockito.when(drinksRepository.findAll()).thenReturn(drinks);

        List<String> output = vendingMachineService.getSold();

        assertEquals("pepsi : 5", output.get(0));
    }
}
