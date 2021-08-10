package com.sheeba.server.service;

import com.sheeba.server.domain.VoteCandidatePojo;
import com.sheeba.server.domain.VoteVoterPojo;
import com.sheeba.server.repository.CandidateRepository;
import com.sheeba.server.repository.VoterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class VoteServiceTest {
    public final VoterRepository voterRepository = Mockito.mock(VoterRepository.class);
    public final CandidateRepository candidateRepository = Mockito.mock(CandidateRepository.class);
    public VoteService voteService;

    @BeforeEach
    void setup() {
        voteService = new VoteService(voterRepository, candidateRepository);
    }

    @Test
    public void testIsVotingAllowed() {
        VoteVoterPojo harryVote = new VoteVoterPojo("52gs92", "harry", false, 23);

        Mockito.when(voterRepository.findById("52gs92")).thenReturn(Optional.of(harryVote));

        boolean output = voteService.isVotingAllowed("52gs92");

        assertTrue(output);
    }

    @Test
    public void testIsVotingNotAllowed() {
        VoteVoterPojo faraVoter = new VoteVoterPojo("hfio740932", "fara", false, 23);

        Mockito.when(voterRepository.findById("hfio740932")).thenReturn(Optional.of(faraVoter));

        boolean output = voteService.isVotingAllowed("dje23904");

        assertFalse(output);
    }

    @Test
    public void testVoting() {
        VoteVoterPojo harryVoter = new VoteVoterPojo("52gs92", "harry", false, 23);
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 0);

        Mockito.when(voterRepository.findById("52gs92")).thenReturn(Optional.of(harryVoter));
        Mockito.when(candidateRepository.findByName("obama")).thenReturn(Optional.of(obamaCandidate));

        String output = voteService.voting("52gs92", "obama");
        assertEquals("obama : tree voted success", output);
    }

    @Test
    public void testInValidVoterIdInVoting() {
        VoteVoterPojo harryVoter = new VoteVoterPojo("52gs92", "harry", false, 23);
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 0);

        Mockito.when(voterRepository.findById("52gs92")).thenReturn(Optional.of(harryVoter));
        Mockito.when(candidateRepository.findByName("obama")).thenReturn(Optional.of(obamaCandidate));

        String output = voteService.voting("gsj4656", "obama");
        assertEquals("invalid voter Id", output);
    }

    @Test
    public void testAlreadyVotedInVoting() {
        VoteVoterPojo harryVoter = new VoteVoterPojo("52gs92", "harry", true, 23);
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 0);

        Mockito.when(voterRepository.findById("52gs92")).thenReturn(Optional.of(harryVoter));
        Mockito.when(candidateRepository.findByName("obama")).thenReturn(Optional.of(obamaCandidate));

        String output = voteService.voting("52gs92", "obama");

        assertEquals("already voted", output);
    }

    @Test
    public void testInvalidCandidateNameInVoting() {
        VoteVoterPojo harryVoter = new VoteVoterPojo("52gs92", "harry", false, 23);
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 0);

        Mockito.when(voterRepository.findById("52gs92")).thenReturn(Optional.of(harryVoter));
        Mockito.when(candidateRepository.findByName("obama")).thenReturn(Optional.of(obamaCandidate));

        String output = voteService.voting("52gs92", "mandala");

        assertEquals("invalid candidate name", output);
    }

    @Test
    public void testResult() {
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 20);
        VoteCandidatePojo mandalaCandidate = new VoteCandidatePojo(2, "mandala", "black", "blackColour", 30);
        VoteCandidatePojo stalinCandidate = new VoteCandidatePojo(3, "stalin", "dmk", "sun", 55);
        VoteCandidatePojo trumpCandidate = new VoteCandidatePojo(4, "trump", "flag", "flag", 3);
        List<VoteCandidatePojo> candidates = new ArrayList<>();
        candidates.add(obamaCandidate);
        candidates.add(mandalaCandidate);
        candidates.add(stalinCandidate);
        candidates.add(trumpCandidate);

        Mockito.when(candidateRepository.findAll()).thenReturn(candidates);

        List<String> output = voteService.result();

        assertEquals(4, output.size());
    }

    @Test
    public void testResultWithPinNo() {
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 20);
        VoteCandidatePojo mandalaCandidate = new VoteCandidatePojo(2, "mandala", "black", "blackColour", 30);
        VoteCandidatePojo stalinCandidate = new VoteCandidatePojo(3, "stalin", "dmk", "sun", 55);
        VoteCandidatePojo trumpCandidate = new VoteCandidatePojo(4, "trump", "flag", "flag", 3);
        List<VoteCandidatePojo> candidates = new ArrayList<>();
        candidates.add(obamaCandidate);
        candidates.add(mandalaCandidate);
        candidates.add(stalinCandidate);
        candidates.add(trumpCandidate);

        Mockito.when(candidateRepository.findAll()).thenReturn(candidates);

        List<String> output = voteService.result(9595);

        assertEquals(4, output.size());
    }

    @Test
    public void testResultWithoutPinNo() {
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 20);
        VoteCandidatePojo mandalaCandidate = new VoteCandidatePojo(2, "mandala", "black", "blackColour", 30);
        VoteCandidatePojo stalinCandidate = new VoteCandidatePojo(3, "stalin", "dmk", "sun", 55);
        VoteCandidatePojo trumpCandidate = new VoteCandidatePojo(4, "trump", "flag", "flag", 3);
        List<VoteCandidatePojo> candidates = new ArrayList<>();
        candidates.add(obamaCandidate);
        candidates.add(mandalaCandidate);
        candidates.add(stalinCandidate);
        candidates.add(trumpCandidate);

        Mockito.when(candidateRepository.findAll()).thenReturn(candidates);

        List<String> output = voteService.result(52782);

        assertEquals("invalid pinNo", output.get(0));
    }

    @Test
    public void testVotedPeople() {
        VoteVoterPojo harryVoter = new VoteVoterPojo("52gs92", "harry", true, 23);
        VoteVoterPojo priyaVoter = new VoteVoterPojo("dh7360", "priya", true, 30);
        VoteVoterPojo saiVoter = new VoteVoterPojo("dg5288", "sai", true, 25);
        VoteVoterPojo maryVoter = new VoteVoterPojo("dhf7369", "mary", false, 40);
        List<VoteVoterPojo> voters = new ArrayList<>();
        voters.add(harryVoter);
        voters.add(priyaVoter);
        voters.add(saiVoter);
        voters.add(maryVoter);

        Mockito.when(voterRepository.findAll()).thenReturn(voters);

        List<String> output = voteService.votedPeople();

        assertEquals(3, output.size());
    }

    @Test
    public void testWinner() {
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 55);
        VoteCandidatePojo mandalaCandidate = new VoteCandidatePojo(2, "mandala", "black", "blackColour", 30);
        VoteCandidatePojo stalinCandidate = new VoteCandidatePojo(3, "stalin", "dmk", "sun", 55);
        VoteCandidatePojo trumpCandidate = new VoteCandidatePojo(4, "trump", "flag", "flag", 3);
        List<VoteCandidatePojo> candidates = new ArrayList<>();
        candidates.add(obamaCandidate);
        candidates.add(mandalaCandidate);
        candidates.add(stalinCandidate);
        candidates.add(trumpCandidate);

        Mockito.when(candidateRepository.findAll()).thenReturn(candidates);

        List<VoteCandidatePojo> output = voteService.winner();

        assertEquals("obama", output.get(0).getName());
        assertEquals("stalin", output.get(1).getName());
    }

    @Test
    public void testGetResultMap() {
        VoteCandidatePojo obamaCandidate = new VoteCandidatePojo(1, "obama", "union", "tree", 55);
        VoteCandidatePojo mandalaCandidate = new VoteCandidatePojo(2, "mandala", "black", "blackColour", 30);
        VoteCandidatePojo stalinCandidate = new VoteCandidatePojo(3, "stalin", "dmk", "sun", 55);
        VoteCandidatePojo trumpCandidate = new VoteCandidatePojo(4, "trump", "flag", "flag", 3);
        List<VoteCandidatePojo> candidates = new ArrayList<>();
        candidates.add(obamaCandidate);
        candidates.add(mandalaCandidate);
        candidates.add(stalinCandidate);
        candidates.add(trumpCandidate);

        Mockito.when(candidateRepository.findAll()).thenReturn(candidates);

        Map<Integer, List<String>> output = voteService.getResultMap();

        assertEquals(3, output.size());
    }

    @Test
    public void testCountVotedPeople() {
        VoteVoterPojo harryVoter = new VoteVoterPojo("52gs92", "harry", true, 23);
        VoteVoterPojo priyaVoter = new VoteVoterPojo("dh7360", "priya", true, 30);
        VoteVoterPojo saiVoter = new VoteVoterPojo("dg5288", "sai", true, 25);
        VoteVoterPojo maryVoter = new VoteVoterPojo("dhf7369", "mary", false, 40);
        List<VoteVoterPojo> voters = new ArrayList<>();
        voters.add(harryVoter);
        voters.add(priyaVoter);
        voters.add(saiVoter);
        voters.add(maryVoter);

        Mockito.when(voterRepository.findAll()).thenReturn(voters);

        int output = voteService.countVotedPeople();

        assertEquals(3, output);
    }
}
