package com.sheeba.server.domain;

import java.util.ArrayList;
import java.util.List;

public class VoterBoothPojo {

    List<VoterPojo> voterPojoList = new ArrayList<VoterPojo>();
    VotingMachinePojo votingMachinePojo = new VotingMachinePojo();

    public VoterBoothPojo() {
    }

    public List<VoterPojo> getVoterPojoList() {
        return voterPojoList;
    }

    public VotingMachinePojo getVotingMachinePojo() {
        return votingMachinePojo;
    }

    public boolean addVoter(VoterPojo voterPojo) {
        return voterPojoList.add(voterPojo);
    }


}
