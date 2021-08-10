package com.sheeba.server.service;

import com.sheeba.server.domain.VoteCandidatePojo;
import com.sheeba.server.domain.VoteVoterPojo;
import com.sheeba.server.repository.CandidateRepository;
import com.sheeba.server.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoteService {
    public final VoterRepository voterRepository;
    public final CandidateRepository candidateRepository;

    @Autowired
    public VoteService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public String addVoter(VoteVoterPojo voteVoterPojo) {
        int age = voteVoterPojo.getAge();
        if (age >= 18) {
            VoteVoterPojo voter = voterRepository.save(voteVoterPojo);
            return voter.getName() + " success";
        }
        return voteVoterPojo.getName() + " invalid age";
    }

    public List<VoteVoterPojo> getVoters() {
        List<VoteVoterPojo> output = new ArrayList<>();
        Iterable<VoteVoterPojo> iterable = voterRepository.findAll();
        for (VoteVoterPojo voter : iterable) {
            output.add(voter);
        }
        return output;
    }

    public VoteVoterPojo getVoter(String voterId) {
        Optional<VoteVoterPojo> optional = voterRepository.findById(voterId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public String addCandidate(VoteCandidatePojo voteCandidatePojo) {
        VoteCandidatePojo addCandidate = candidateRepository.save(voteCandidatePojo);
        String name = addCandidate.getName();
        return name + " success";
    }

    public List<VoteCandidatePojo> getCandidates() {
        List<VoteCandidatePojo> output = new ArrayList<>();
        Iterable<VoteCandidatePojo> iterable = candidateRepository.findAll();
        for (VoteCandidatePojo candidate : iterable) {
            output.add(candidate);
        }
        return output;
    }

    public VoteCandidatePojo getCandidate(int candidateId) {
        Optional<VoteCandidatePojo> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            return candidate.get();
        }
        return null;
    }

    public boolean isVotingAllowed(String voterId) {
        Optional<VoteVoterPojo> voter = voterRepository.findById(voterId);
        if (voter.isPresent() && !voter.get().isAlreadyVoted()) {
            return true;
        }
        return false;
    }

    public String voting(String voterId, String candidateName) {
        String output = "";
        Optional<VoteVoterPojo> voter = voterRepository.findById(voterId);
        if (!voter.isPresent()) {
            return output = "invalid voter Id";
        } else if (voter.get().isAlreadyVoted()) {
            return output = "already voted";
        }
        Optional<VoteCandidatePojo> candidate = candidateRepository.findByName(candidateName);
        if (!candidate.isPresent()) {
            return output = "invalid candidate name";
        } else {
            VoteVoterPojo voterPojo = voter.get();
            VoteCandidatePojo candidatePojo = candidate.get();
            voterPojo.setAlreadyVoted(true);
            int vote = candidatePojo.getVote();
            int increase = 0;
            increase = vote + 1;
            candidatePojo.setVote(increase);
            voterRepository.save(voterPojo);
            candidateRepository.save(candidatePojo);
            return candidatePojo.getName() + " : " + candidatePojo.getSymbol() + " voted success";
        }
    }

    public List<String> result() {
        List<String> result = new ArrayList<>();
        Iterable<VoteCandidatePojo> iterable = candidateRepository.findAll();
        for (VoteCandidatePojo candidate : iterable) {
            String name = candidate.getName();
            int vote = candidate.getVote();
            result.add(name + " : " + vote);
        }
        return result;
    }

    public List<String> result(int pinNo) {
        List<String> output = new ArrayList<>();
        output.add("invalid pinNo");
        List<String> result = new ArrayList<>();
        Iterable<VoteCandidatePojo> iterable = candidateRepository.findAll();
        if (pinNo == 9595) {
            for (VoteCandidatePojo candidate : iterable) {
                String name = candidate.getName();
                int vote = candidate.getVote();
                result.add(name + " : " + vote);
            }
            return result;
        }
        return output;
    }

    public List<String> votedPeople() {
        List<String> output = new ArrayList<>();
        Iterable<VoteVoterPojo> iterable = voterRepository.findAll();
        for (VoteVoterPojo voter : iterable) {
            String name = voter.getName();
            String id = voter.getId();
            boolean isAlreadyVoted = voter.isAlreadyVoted();
            if (isAlreadyVoted) {
                output.add(name + " : " + id);
            }
        }
        return output;
    }

    public List<VoteCandidatePojo> winner() {
        List<VoteCandidatePojo> output = new ArrayList<>();
        List<VoteCandidatePojo> candidates = getCandidates();
        VoteCandidatePojo tempCandidate = candidates.get(0);
        for (VoteCandidatePojo candidate : candidates) {
            if (candidate.getVote() > tempCandidate.getVote()) {
                tempCandidate = candidate;
            }
        }
        for (VoteCandidatePojo candidate : candidates) {
            if (candidate.getVote() == tempCandidate.getVote()) {
                output.add(candidate);
            }
        }
        return output;
    }

    public List<VoteCandidatePojo> newWinner() {
        List<VoteCandidatePojo> output = new ArrayList<>();
        List<VoteCandidatePojo> candidates = getCandidates();
        VoteCandidatePojo tempCandidate = candidates.get(0);
        for (VoteCandidatePojo candidate : candidates) {
            if (candidate.getVote() > tempCandidate.getVote()) {
                tempCandidate = candidate;
                output.clear();
                output.add(candidate);
            } else if (candidate.getVote() == tempCandidate.getVote()) {
                output.add(candidate);
            }
        }
        return output;
    }

    public Map<Integer, List<String>> getResultMap() {
        Map<Integer, List<String>> candidateByVoteMap = new HashMap<>();
        List<VoteCandidatePojo> candidates = getCandidates();
        for (VoteCandidatePojo candidate : candidates) {
            String name = candidate.getName();
            int vote = candidate.getVote();
            boolean isVotePresent = candidateByVoteMap.containsKey(vote);
            if (isVotePresent) {
                List<String> output = candidateByVoteMap.get(vote);
                output.add(name);
                candidateByVoteMap.put(vote, output);
            } else {
                List<String> output = new ArrayList<>();
                output.add(name);
                candidateByVoteMap.put(vote, output);
            }
        }
        return candidateByVoteMap;
    }

    public int countVotedPeople() {
        Iterable<VoteVoterPojo> iterable = voterRepository.findAll();
        int count = 0;
        for (VoteVoterPojo voter : iterable) {
            boolean isAlreadyVoted = voter.isAlreadyVoted();
            if (isAlreadyVoted) {
                //count = count + 1;
                count++;
            }
        }
        return count;
    }
}
