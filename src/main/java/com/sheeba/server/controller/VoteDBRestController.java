package com.sheeba.server.controller;

import com.sheeba.server.domain.VoteCandidatePojo;
import com.sheeba.server.domain.VoteVoterPojo;
import com.sheeba.server.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class VoteDBRestController {
    public final VoteService voteService;

    @Autowired
    public VoteDBRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/new/addVoter")
    public String addVoter(@RequestBody VoteVoterPojo voteVoterPojo) {
        return voteService.addVoter(voteVoterPojo);
    }

    @GetMapping("/new/getVoters")
    public List<VoteVoterPojo> getVoters() {
        return voteService.getVoters();
    }

    @GetMapping("/new/getVoter")
    public VoteVoterPojo getVoter(@RequestParam String voterId) {
        return voteService.getVoter(voterId);
    }

    @PostMapping("/new/addCandidate")
    public String addCandidate(@RequestBody VoteCandidatePojo voteCandidatePojo) {
        return voteService.addCandidate(voteCandidatePojo);
    }

    @GetMapping("/new/getCandidates")
    public List<VoteCandidatePojo> getCandidates() {
        return voteService.getCandidates();
    }

    @GetMapping("/new/getCandidate")
    public VoteCandidatePojo getCandidate(@RequestParam int candidateId) {
        return voteService.getCandidate(candidateId);
    }

    @GetMapping("/new/isVotingAllowed")
    public boolean isVotingAllowed(@RequestParam String voterId) {
        return voteService.isVotingAllowed(voterId);
    }

    @GetMapping("/new/voting")
    public String voting(@RequestParam String voterId, @RequestParam String candidateName) {
        return voteService.voting(voterId, candidateName);
    }

    @GetMapping("/new/result")
    public List<String> result() {
        return voteService.result();
    }

    @GetMapping("/new/resultWithPinNo")
    public List<String> result(@RequestParam int pinNo) {
        return voteService.result(pinNo);
    }

    @GetMapping("/new/votedPeople")
    public List<String> votedPeople() {
        return voteService.votedPeople();
    }

    @GetMapping("/new/winner")
    public List<VoteCandidatePojo> winner() {
        return voteService.winner();
    }

    @GetMapping("/new/getResultMap")
    public Map<Integer, List<String>> getResultMap() {
        return voteService.getResultMap();
    }

    @GetMapping("/new/totalPeopleVoted")
    public int totalPeopleVoted() {
        return voteService.countVotedPeople();
    }
}
