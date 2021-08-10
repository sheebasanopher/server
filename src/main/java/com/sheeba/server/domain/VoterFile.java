package com.sheeba.server.domain;

import java.util.List;

public class VoterFile {
    private List<VoterPojo> voterPojos;

    public VoterFile(List<VoterPojo> voterPojos) {
        this.voterPojos = voterPojos;
    }

    public VoterFile() {
    }

    public List<VoterPojo> getVoterPojos() {
        return voterPojos;
    }

    public void setVoterPojos(List<VoterPojo> voterPojos) {
        this.voterPojos = voterPojos;
    }
}
