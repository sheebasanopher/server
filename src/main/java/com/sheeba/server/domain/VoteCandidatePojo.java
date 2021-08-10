package com.sheeba.server.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "candidate")
public class VoteCandidatePojo {
    @Id
    private int id;
    private String name;
    private String party;
    private String symbol;
    private int vote;

    public VoteCandidatePojo(int id, String name, String party, String symbol, int vote) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.symbol = symbol;
        this.vote = vote;
    }

    public VoteCandidatePojo() {

    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "VoteCandidatePojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", party='" + party + '\'' +
                ", symbol='" + symbol + '\'' +
                ", vote=" + vote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteCandidatePojo that = (VoteCandidatePojo) o;
        return id == that.id && vote == that.vote && Objects.equals(party, that.party) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, party, symbol, vote);
    }
}


