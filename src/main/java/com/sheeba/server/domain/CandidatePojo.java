package com.sheeba.server.domain;

public class CandidatePojo {
    private String name;
    private String party;
    private String symbol;
    private int vote;

    public CandidatePojo(String name, String party, String symbol, int vote) {
        this.name = name;
        this.party = party;
        this.symbol = symbol;
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getVote() {
            return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}

