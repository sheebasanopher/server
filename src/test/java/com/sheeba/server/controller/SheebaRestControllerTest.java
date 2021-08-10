package com.sheeba.server.controller;

import com.sheeba.server.domain.DrinksContainer;
import com.sheeba.server.domain.DrinksPojo;
import com.sheeba.server.domain.VoterFile;
import com.sheeba.server.domain.VoterPojo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SheebaRestControllerTest {
    SheebaRestController sheebaRestController;

    @Test
    public void testVoters() {
        //Given
        VoterPojo sindhuVoter = new VoterPojo("sindhu", "v9023", false, 23);
        VoterPojo banuVoter = new VoterPojo("banu", "v5649", false, 56);
        VoterPojo gethaVoter = new VoterPojo("getha", "v9034", false, 34);
        VoterPojo thanveerVoter = new VoterPojo("thanveer", "v7833", false, 23);
        VoterPojo balaVoter = new VoterPojo("bala", "v67904", false, 23);

        List<VoterPojo> voters = new ArrayList<>();
        voters.add(sindhuVoter);
        voters.add(banuVoter);
        voters.add(gethaVoter);
        voters.add(thanveerVoter);
        voters.add(balaVoter);

        VoterFile voterfile = new VoterFile(voters);
        sheebaRestController = new SheebaRestController();

        //When
        Map<Integer, List<String>> output = sheebaRestController.voters(voterfile);

        //Then

        assertEquals("sindhu", output.get(23).get(0));
        List<String> expectedName1 = new ArrayList<>();
        expectedName1.add("banu");
        assertEquals(expectedName1, output.get(56));

        assertEquals("getha", output.get(34).get(0));
        assertEquals("thanveer", output.get(23).get(1));
        assertEquals("bala", output.get(23).get(2));
    }

    @Test
    public void testMultiple() {
        //give
        DrinksPojo pepsiDrink = new DrinksPojo("pepsi", 10, 23);
        DrinksPojo maazaDrink = new DrinksPojo("maaza", 10, 24);
        DrinksPojo frootiDrink = new DrinksPojo("frooti", 10, 9);

        List<DrinksPojo> drinks = new ArrayList<>();
        drinks.add(pepsiDrink);
        drinks.add(maazaDrink);
        drinks.add(frootiDrink);

        DrinksContainer drinksContainer = new DrinksContainer(drinks);

        sheebaRestController = new SheebaRestController();
        //when
        List<String> output = sheebaRestController.multipleDrinks(drinksContainer);
        //then
        assertEquals("pepsi is found", output.get(0));
        assertEquals("frooti is not found", output.get(2));

    }

    @Test
    public void testCost() {

        DrinksPojo sliceDrinks = new DrinksPojo("slice", 10, 20);
        DrinksPojo miradaDrinks = new DrinksPojo("mirada", 10, 6);

        sheebaRestController = new SheebaRestController();

        String output = sheebaRestController.cost(sliceDrinks);
        assertEquals("slice is found", output);
        String output1 = sheebaRestController.cost(miradaDrinks);
        assertEquals("mirada is not found", output1);

    }

    @Test
    public void testIsVotingAllowed() {
        VoterPojo peterVote = new VoterPojo("peter", "5677", false, 34);
        VoterPojo jimmyVote = new VoterPojo("jimmy", "6789", false, 10);

        sheebaRestController = new SheebaRestController();

        String output = sheebaRestController.isVotingAllowed(peterVote);
        assertEquals("Voting Allowed", output);
        String output1 = sheebaRestController.isVotingAllowed(jimmyVote);
        assertEquals("Voting not Allowed", output1);

    }

    @Test
    public void testVote() {
        sheebaRestController = new SheebaRestController();
        VoterPojo voterPojo = sheebaRestController.vote("jonny");

    }

    @Test
    public void testWeb() {
        sheebaRestController = new SheebaRestController();
        String output = sheebaRestController.web("ruby");
    }

    @Test
    public void teatStart() {
        sheebaRestController = new SheebaRestController();
        String output = sheebaRestController.start("shafiq", 3467);
    }
    @Test
    public void testGetDrinks(){
        sheebaRestController=new SheebaRestController();
        DrinksPojo output=sheebaRestController.getDrinks("pepsi",10,20);
    }
    @Test
    public void testGetDrinks1(){
        sheebaRestController=new SheebaRestController();
        DrinksPojo output=sheebaRestController.getDrinks("sprite",34);

    }
    @Test
    public void testGetDrinks2(){
        sheebaRestController=new SheebaRestController();
        DrinksPojo output=sheebaRestController.getDrinks("limka");

    }
//    @Test
//    public  void testGetVoter(){
//        sheebaRestController=new SheebaRestController();
//        VoterPojo output=sheebaRestController.getVoter("ivan");
//
//    }
    @Test
    public void testSayHello(){
        sheebaRestController=new SheebaRestController();
        String output=sheebaRestController.sayHello("handr","mr");
    }


    @Test
    void sample() {
        //Given


        //When


        //Then
    }
}
