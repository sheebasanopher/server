package com.sheeba.server.controller;

import com.sheeba.server.domain.CandidatePojo;
import com.sheeba.server.domain.VoterBoothPojo;
import com.sheeba.server.domain.VoterPojo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VoterBoothRestController {

    VoterBoothPojo voterBoothPojo = new VoterBoothPojo();

    @PostMapping("/addVoter")
    public String addVoter(@RequestBody VoterPojo voterPojo) {
        if (voterPojo.getAge() >= 18) {
            boolean addVoter = voterBoothPojo.addVoter(voterPojo);
            if (addVoter) {
                return voterPojo.getName() + " Allowed";
            }
        }
        return voterPojo.getName() + " not Allowed";
    }

    @GetMapping("/getVoters")
    public List<VoterPojo> getVoters() {
        return voterBoothPojo.getVoterPojoList();

    }

    @GetMapping("/getVoter")
    public VoterPojo getVoter(@RequestParam String voterId) {
        List<VoterPojo> voters = voterBoothPojo.getVoterPojoList();
        for (VoterPojo voter : voters) {
            String id = voter.getId();
            if (id.equals(voterId)) {
                return voter;
            }
        }
        return null;
    }

    @GetMapping("/isVotingAllowed")
    public boolean isVotingAllowed(@RequestParam String voterId) {
        List<VoterPojo> voters = voterBoothPojo.getVoterPojoList();
        for (VoterPojo voter : voters) {
            String id = voter.getId();
            boolean isAlreadyVoter = voter.isAlreadyVoted();
            if (id.equals(voterId) && (!isAlreadyVoter)) {
                voter.setAlreadyVoted(true);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/addCandidate")
    public String addCandidate(@RequestBody CandidatePojo candidatePojo) {
        boolean addCandidate = voterBoothPojo.getVotingMachinePojo().addCandidate(candidatePojo);
        if (addCandidate) {
            return candidatePojo.getName() + " success";
        }
        return candidatePojo.getName() + " not success";
    }

    @GetMapping("/getCandidates")
    public List<CandidatePojo> getCandidates() {
        return voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
    }

    @GetMapping("/getCandidate")
    public CandidatePojo getCandidate(@RequestParam String name) {
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        for (CandidatePojo candidate : candidates) {
            String canName = candidate.getName();
            if (canName.equals(name)) {
                return candidate;
            }
        }
        return null;
    }

    @GetMapping("/voting")
    public String voting(@RequestParam String voterId, @RequestParam String candidateName) {
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        if (isVotingAllowed(voterId)) {
            for (CandidatePojo candidate : candidates) {
                int vote = candidate.getVote();
                String name = candidate.getName();
                String symbol = candidate.getSymbol();
                if (candidateName.equals(name)) {
                    int count = 0;
                    count = vote + 1;
                    candidate.setVote(count);
                    return name + " : " + symbol;
                }
            }
        }
        return null;
    }

    @GetMapping("/result")
    public List<String> result() {
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        List<String> result = new ArrayList<>();
        for (CandidatePojo candidate : candidates) {
            String name = candidate.getName();
            int vote = candidate.getVote();
            result.add(name + " : " + vote);
        }
        return result;
    }

    @GetMapping("/resultPinNo")
    public List<String> result(int pinNo) {
        List<String> output = new ArrayList<>();
        output.add("incorrect pinNo");
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        List<String> result = new ArrayList<>();
        if (pinNo == 9090) {
            for (CandidatePojo candidate : candidates) {
                String name = candidate.getName();
                int vote = candidate.getVote();
                result.add(name + " : " + vote);
                return result;
            }
        }
        return output;
    }

    @GetMapping("/votedPeople")
    public List<String> voted() {
        List<String> voted = new ArrayList<>();
        List<VoterPojo> voters = voterBoothPojo.getVoterPojoList();
        for (VoterPojo voter : voters) {
            String name = voter.getName();
            String id = voter.getId();
            boolean votedPeople = voter.isAlreadyVoted();
            if (votedPeople) {
                voted.add(name + " : " + id);
            }
        }
        return voted;
    }

    @GetMapping("/winner")
    public String winner() {
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        int maxTemp = candidates.get(0).getVote();
        String nameTemp = candidates.get(0).getName();
        String output="";
        for (CandidatePojo candidate : candidates) {
            int vote = candidate.getVote();
            String name = candidate.getName();
            if (vote > maxTemp) {
                maxTemp = vote;
                nameTemp = name;
            }
        }
        return output = maxTemp + " : " + nameTemp;
    }

    @GetMapping("/bothWon")
    public List<CandidatePojo> bothWin() {
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        List<CandidatePojo> output = new ArrayList<>();
        CandidatePojo temWon = candidates.get(0);
        for (CandidatePojo candidate : candidates) {
            if (candidate.getVote() > temWon.getVote()) {
                temWon = candidate;
            }
        }
        for (CandidatePojo candidate : candidates) {
            if (temWon.getVote() == candidate.getVote()) {
                output.add(candidate);
            }
        }
        return output;
    }

    public List<CandidatePojo> newBothWin() {
        List<CandidatePojo> candidates = voterBoothPojo.getVotingMachinePojo().getCandidatePojoList();
        CandidatePojo temWon = candidates.get(0);
        List<CandidatePojo> output = new ArrayList<>();
        for (CandidatePojo candidate : candidates) {
            if (candidate.getVote() > temWon.getVote()) {
                temWon = candidate;
                output.clear();
                output.add(candidate);
            } else if (temWon.getVote() == candidate.getVote()) {
                output.add(candidate);
            }
        }
        return output;
    }

}