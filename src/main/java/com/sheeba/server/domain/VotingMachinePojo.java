package com.sheeba.server.domain;

import java.util.ArrayList;
import java.util.List;

public class VotingMachinePojo {
    List<CandidatePojo> candidatePojoList = new ArrayList<CandidatePojo>();

    public VotingMachinePojo() {
    }

    public List<CandidatePojo> getCandidatePojoList() {
        return candidatePojoList;
    }

    public boolean addCandidate(CandidatePojo candidatePojo) {
        return candidatePojoList.add(candidatePojo);
    }
}
