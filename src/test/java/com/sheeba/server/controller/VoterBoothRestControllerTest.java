package com.sheeba.server.controller;

import com.sheeba.server.domain.CandidatePojo;
import com.sheeba.server.domain.VoterPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VoterBoothRestControllerTest {
    VoterBoothRestController voterBoothRestController;

    @BeforeEach
    public void setup() {
        voterBoothRestController = new VoterBoothRestController();
    }

    @Test
    public void testAddVoter() {
        //give
        VoterPojo kittyVoter = new VoterPojo("kitty", "in3456", false, 23);

        //when
        String output = voterBoothRestController.addVoter(kittyVoter);

        //then
        assertEquals("kitty Allowed", output);
    }

    @Test
    public void testNotAddVoter() {
        //give
        VoterPojo kamalaVoter = new VoterPojo("kamala", "in2309", false, 11);

        //when
        String output = voterBoothRestController.addVoter(kamalaVoter);

        //then
        assertEquals("kamala not Allowed", output);
    }

    @Test
    public void testGetVoters() {
        //give
        VoterPojo hamaVoter = new VoterPojo("hama", "in0954", false, 23);
        VoterPojo harryVoter = new VoterPojo("harry", "in0023", false, 44);
        String addHamaVoter = voterBoothRestController.addVoter(hamaVoter);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);

        //when
        List<VoterPojo> output = voterBoothRestController.getVoters();

        //then
        assertEquals("harry", output.get(1).getName());
    }

    @Test
    public void testNotGetVoters() {
        //given
        VoterPojo babyVoter = new VoterPojo("baby", "in5690", false, 11);
        String addBabyVoter = voterBoothRestController.addVoter(babyVoter);

        //when
        List<VoterPojo> output = voterBoothRestController.getVoters();

        //then
        assertEquals(0, output.size());
    }

    @Test
    public void testGetVoter() {
        //given
        VoterPojo potterVoter = new VoterPojo("potter", "on8934", false, 45);
        String addPotterVoter = voterBoothRestController.addVoter(potterVoter);

        //when
        VoterPojo output = voterBoothRestController.getVoter("on8934");

        //then
        assertEquals("potter", output.getName());
    }

    @Test
    public void testNotGetVoter() {
        //given
        VoterPojo avaVoter = new VoterPojo("ava", "in4370", false, 55);
        String addAvaVoter = voterBoothRestController.addVoter(avaVoter);

        //when
        VoterPojo output = voterBoothRestController.getVoter("ht2668");

        //then
        assertNull(output);
    }

    @Test
    public void testIsVotingAllowed() {
        //given
        VoterPojo fathimaVoter = new VoterPojo("fathima", "in2356", false, 24);
        String addFathimaVoter = voterBoothRestController.addVoter(fathimaVoter);

        //when
        boolean output = voterBoothRestController.isVotingAllowed("in2356");

        //then
        assertTrue(output);
    }

    @Test
    public void testIsVotingNotAllowed() {
        //given
        VoterPojo banuVoter = new VoterPojo("banu", "in9000", true, 34);
        String addBanu = voterBoothRestController.addVoter(banuVoter);

        //when
        boolean output = voterBoothRestController.isVotingAllowed("in9000");

        //then
        assertFalse(output);
    }

    @Test
    public void testAddCandidate() {
        //give
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);

        //when
        String addStalin = voterBoothRestController.addCandidate(stalinCandidate);

        //then
        assertEquals("stalin success", addStalin);

    }

    @Test
    public void testGetCandidates() {
        //given
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);

        //when
        List<CandidatePojo> output = voterBoothRestController.getCandidates();

        //then
        assertEquals("kamal", output.get(0).getName());
    }

    @Test
    public void getCandidate() {
        //given
        CandidatePojo rajuCandidate = new CandidatePojo("raju", "admk", "leaf", 0);
        String addRajuCan = voterBoothRestController.addCandidate(rajuCandidate);

        //when
        CandidatePojo output = voterBoothRestController.getCandidate("raju");

        //then
        assertEquals("raju", output.getName());
    }

    @Test
    public void getNoCandidate() {
        //give
        CandidatePojo rajuCandidate = new CandidatePojo("raju", "admk", "leaf", 0);
        String addRajuCan = voterBoothRestController.addCandidate(rajuCandidate);

        //when
        CandidatePojo output = voterBoothRestController.getCandidate("harry");

        //then
        assertNull(output);
    }

    @Test
    public void testVote() {
        //give
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);

        //when
        String output = voterBoothRestController.voting("in4576", "kamal");
        String output1 = voterBoothRestController.voting("in6789", "kamal");

        //then
        assertEquals("kamal : torch", output);
        assertEquals("kamal : torch", output1);
    }

    @Test
    public void testNoVote() {
        //given
        CandidatePojo rajuCandidate = new CandidatePojo("raju", "admk", "leaf", 0);
        String addRajuCan = voterBoothRestController.addCandidate(rajuCandidate);
        VoterPojo kapoorVoter = new VoterPojo("kapoore", "in3000", true, 11);
        String addKapoorVoter = voterBoothRestController.addVoter(kapoorVoter);

        //when
        String output = voterBoothRestController.voting("in3000", "kamal");

        //then
        assertNull(output);
    }

    @Test
    public void testResult() {
        //given
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);
        String addStalinCan = voterBoothRestController.addCandidate(stalinCandidate);

        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);
        VoterPojo harryVoter = new VoterPojo("harry", "in9023", false, 45);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);

        String voting = voterBoothRestController.voting("in4576", "kamal");
        String voting1 = voterBoothRestController.voting("in6789", "kamal");
        String voting2 = voterBoothRestController.voting("in9023", "stalin");

        //when
        List<String> output = voterBoothRestController.result();

        //then
        assertEquals("kamal : 2", output.get(0));
    }

    @Test
    public void testResultPinNo() {
        //give
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);
        String addStalinCan = voterBoothRestController.addCandidate(stalinCandidate);

        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);
        VoterPojo harryVoter = new VoterPojo("harry", "in9023", false, 45);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);

        String voting = voterBoothRestController.voting("in4576", "kamal");
        String voting1 = voterBoothRestController.voting("in6789", "kamal");
        String voting2 = voterBoothRestController.voting("in9023", "stalin");

        //when
        List<String> output = voterBoothRestController.result(9090);

        //then
        assertEquals("kamal : 2", output.get(0));
    }

    @Test
    public void testResultWrongPinNo() {
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);
        String addStalinCan = voterBoothRestController.addCandidate(stalinCandidate);

        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);
        VoterPojo harryVoter = new VoterPojo("harry", "in9023", false, 45);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);

        String voting = voterBoothRestController.voting("in4576", "kamal");
        String voting1 = voterBoothRestController.voting("in6789", "kamal");
        String voting2 = voterBoothRestController.voting("in9023", "stalin");

        //when
        List<String> output = voterBoothRestController.result(5678);

        //then
        assertEquals("incorrect pinNo", output.get(0));
    }

    @Test
    public void testVotedPeople() {
        //give
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);
        String addStalinCan = voterBoothRestController.addCandidate(stalinCandidate);

        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);
        VoterPojo harryVoter = new VoterPojo("harry", "in9023", false, 45);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);

        String voting = voterBoothRestController.voting("in4576", "kamal");
        String voting1 = voterBoothRestController.voting("in6789", "kamal");
        String voting2 = voterBoothRestController.voting("in9023", "stalin");

        //when
        List<String> output = voterBoothRestController.voted();

        //then
        assertEquals("anil : in4576", output.get(0));
    }

    @Test
    public void testWinner() {
        //given
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);
        String addStalinCan = voterBoothRestController.addCandidate(stalinCandidate);
        CandidatePojo obamaCandidate = new CandidatePojo("obama", "union", "flag", 0);
        String addObamaCan = voterBoothRestController.addCandidate(obamaCandidate);

        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);
        VoterPojo harryVoter = new VoterPojo("harry", "in9023", false, 45);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);
        VoterPojo babaVoter = new VoterPojo("baba", "in4699", false, 56);
        String addBabVoter = voterBoothRestController.addVoter(babaVoter);
        VoterPojo peterVoter = new VoterPojo("peter", "in2344", false, 89);
        String addPeterVoter = voterBoothRestController.addVoter(peterVoter);
        VoterPojo luluVoter = new VoterPojo("lulu", "in4600", false, 56);
        String addLuluVoter = voterBoothRestController.addVoter(luluVoter);

        String voting = voterBoothRestController.voting("in4576", "kamal");
        String voting1 = voterBoothRestController.voting("in6789", "kamal");
        String voting2 = voterBoothRestController.voting("in9023", "stalin");
        String voting3 = voterBoothRestController.voting("in4699", "obama");
        String voting4 = voterBoothRestController.voting("in2344", "obama");
        String voting5 = voterBoothRestController.voting("in4600", "obama");

        //when
        List<CandidatePojo> output = voterBoothRestController.bothWin();

        //then
        assertEquals("obama", output.get(0).getName());
    }

    @Test
    public void testBothWin() {
        //give
        CandidatePojo kamalCandidate = new CandidatePojo("kamal", "nmnm", "torch", 0);
        String addKamalCan = voterBoothRestController.addCandidate(kamalCandidate);
        CandidatePojo stalinCandidate = new CandidatePojo("stalin", "dmk", "sun", 0);
        String addStalinCan = voterBoothRestController.addCandidate(stalinCandidate);
        CandidatePojo obamaCandidate = new CandidatePojo("obama", "union", "flag", 0);
        String addObamaCan = voterBoothRestController.addCandidate(obamaCandidate);
        CandidatePojo epsCandidate = new CandidatePojo("epd", "admk", "leaf", 0);
        String addEpsCan = voterBoothRestController.addCandidate(epsCandidate);

        VoterPojo anilVoter = new VoterPojo("anil", "in4576", false, 23);
        String addAnilVoter = voterBoothRestController.addVoter(anilVoter);
        VoterPojo ramyaVoter = new VoterPojo("ramya", "in6789", false, 48);
        String addRamyaVoter = voterBoothRestController.addVoter(ramyaVoter);
        VoterPojo harryVoter = new VoterPojo("harry", "in9023", false, 45);
        String addHarryVoter = voterBoothRestController.addVoter(harryVoter);
        VoterPojo babaVoter = new VoterPojo("baba", "in4699", false, 56);
        String addBabVoter = voterBoothRestController.addVoter(babaVoter);
        VoterPojo peterVoter = new VoterPojo("peter", "in2344", false, 89);
        String addPeterVoter = voterBoothRestController.addVoter(peterVoter);
        VoterPojo luluVoter = new VoterPojo("lulu", "in4600", false, 56);
        String addLuluVoter = voterBoothRestController.addVoter(luluVoter);
        VoterPojo jasminVoter = new VoterPojo("jasmin", "in2378", false, 48);
        String addJasminVoter = voterBoothRestController.addVoter(jasminVoter);
        VoterPojo sheelaVoter = new VoterPojo("sheela", "in7892", false, 45);
        String addSheelaVoter = voterBoothRestController.addVoter(sheelaVoter);

        String voting = voterBoothRestController.voting("in4576", "kamal");
        String voting1 = voterBoothRestController.voting("in6789", "kamal");
        String voting2 = voterBoothRestController.voting("in9023", "stalin");
        String voting6 = voterBoothRestController.voting("in2378", "stalin");
        String voting7 = voterBoothRestController.voting("in7892", "stalin");
        String voting3 = voterBoothRestController.voting("in4699", "obama");
        String voting4 = voterBoothRestController.voting("in2344", "obama");
        String voting5 = voterBoothRestController.voting("in4600", "obama");

        //when
        List<CandidatePojo> output = voterBoothRestController.bothWin();

        //then
        assertEquals("stalin", output.get(0).getName());
        assertEquals("obama", output.get(1).getName());

    }
}
