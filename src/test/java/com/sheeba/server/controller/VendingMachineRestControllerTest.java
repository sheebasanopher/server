package com.sheeba.server.controller;

import com.sheeba.server.domain.DrinksPojo;
import com.sheeba.server.domain.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class VendingMachineRestControllerTest {
    VendingMachineRestController vendingMachineRestController;

    @BeforeEach
    public void setup() {
        vendingMachineRestController = new VendingMachineRestController();
    }

    @Test
    public void testAddDrink() {
        //given
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 10, 20);

        //when
        String output = vendingMachineRestController.addDrink(spriteDrink);

        //then
        assertEquals("sprite success", output);
    }

    @Test
    public void testGetDrinks() {
        //given
        DrinksPojo frootiDrinks = new DrinksPojo("frooti", 10, 20);
        DrinksPojo kingFisher = new DrinksPojo("kingFisher", 10, 34);
        String addingDrink = vendingMachineRestController.addDrink(frootiDrinks);
        String KingFisherDrink = vendingMachineRestController.addDrink(kingFisher);

        //when
        Set<DrinksPojo> output = vendingMachineRestController.getDrinks();

        //then
        assertEquals(2, output.size());
        Iterator<DrinksPojo> setIterator = output.iterator();
        assertEquals("frooti", setIterator.next().getProductName());
        assertEquals("kingFisher", setIterator.next().getProductName());
    }

    @Test
    public void testGetDrink() {
        //given
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 10, 24);
        DrinksPojo redBullDrink = new DrinksPojo("redBull", 10, 30);
        String spriteAdd = vendingMachineRestController.addDrink(spriteDrink);
        String resBullAdd = vendingMachineRestController.addDrink(redBullDrink);

        //when
        DrinksPojo output = vendingMachineRestController.getDrink("sprite");

        //then
        assertEquals("sprite", output.getProductName());
    }

    @Test
    public void testNotGetDrink() {
        //given
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 10, 24);
        DrinksPojo redBullDrink = new DrinksPojo("redBull", 10, 30);
        String spriteAdd = vendingMachineRestController.addDrink(spriteDrink);
        String resBullAdd = vendingMachineRestController.addDrink(redBullDrink);

        //when
        DrinksPojo output = vendingMachineRestController.getDrink("maaza");

        //then
        assertNull(output);
    }

    @Test
    public void testDuplicateAddDrink() {
        //give
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 10, 20);
        vendingMachineRestController.addDrink(spriteDrink);
        vendingMachineRestController.addDrink(spriteDrink);

        //when
        Set<DrinksPojo> drinks = vendingMachineRestController.getDrinks();

        //then
        assertEquals(1, drinks.size());
    }

    @Test
    public void testDuplicateAddDrink1() {
        //give
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 10, 20);

        //when
        String output = vendingMachineRestController.addDrink(spriteDrink);
        String output1 = vendingMachineRestController.addDrink(spriteDrink);

        //then
        assertEquals("sprite success", output);
        assertEquals("sprite not success", output1);
    }

    @Test
    public void testDispense() throws ProductNotFoundException {
        //give
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 10, 20);
        String addSprite = vendingMachineRestController.addDrink(spriteDrink);
        DrinksPojo frootiDrinks = new DrinksPojo("frooti", 10, 20);
        String addfrooti = vendingMachineRestController.addDrink(frootiDrinks);

        //when
        String output = vendingMachineRestController.dispense("frooti", 20);

        //then
        assertEquals("frooti", output);

    }

    @Test
    public void testNotDispense() throws ProductNotFoundException {
        //give
        DrinksPojo kingFisher = new DrinksPojo("kingFisher", 0, 34);
        String addKingFisher = vendingMachineRestController.addDrink(kingFisher);

        //when
        Throwable exception = assertThrows(ProductNotFoundException.class, () -> vendingMachineRestController.dispense("kingFisher", 20));

        //then
        assertEquals("kingFisher not found", exception.getMessage());
    }

    @Test
    public void testTotalStock() {
        //give
        DrinksPojo redBullDrink = new DrinksPojo("redBull", 10, 30);
        String addRedBull = vendingMachineRestController.addDrink(redBullDrink);
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 5, 20);
        String addSprite = vendingMachineRestController.addDrink(spriteDrink);
        DrinksPojo frootiDrinks = new DrinksPojo("frooti", 2, 20);
        String addfrooti = vendingMachineRestController.addDrink(frootiDrinks);

        //when
        List<String> output = vendingMachineRestController.totalStock();

        //then
        assertEquals("redBull : 10", output.get(2));
    }

    @Test
    public void testAddStock() throws ProductNotFoundException {
        //given
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 5, 20);
        String addSprite = vendingMachineRestController.addDrink(spriteDrink);
        DrinksPojo frootiDrinks = new DrinksPojo("frooti", 2, 20);
        String addfrooti = vendingMachineRestController.addDrink(frootiDrinks);

        //when
        String output = vendingMachineRestController.addStock("sprite", 3);

        //then
        assertEquals("sprite : 8", output);
    }

    @Test
    public void testNoAddStock() throws ProductNotFoundException {
        DrinksPojo redBullDrink = new DrinksPojo("redBull", 10, 30);
        String addRedBull = vendingMachineRestController.addDrink(redBullDrink);
        DrinksPojo spriteDrink = new DrinksPojo("sprite", 5, 20);
        String addSprite = vendingMachineRestController.addDrink(spriteDrink);

        Throwable exception = assertThrows(ProductNotFoundException.class, () -> vendingMachineRestController.addStock("hello", 7));

        assertEquals("hello not found", exception.getMessage());
    }

}
