package com.sheeba.server.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "voter")
public class VoteVoterPojo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private boolean alreadyVoted;
    private int age;

    public VoteVoterPojo(String id, String name, boolean alreadyVoted, int age) {
        this.id = id;
        this.name = name;
        this.alreadyVoted = alreadyVoted;
        this.age = age;
    }

    public VoteVoterPojo() {

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isAlreadyVoted() {
        return alreadyVoted;
    }

    public int getAge() {
        return age;
    }

    public void setAlreadyVoted(boolean alreadyVoted) {
        this.alreadyVoted = alreadyVoted;
    }

    @Override
    public String toString() {
        return "voteVoterPojo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alreadyVoted=" + alreadyVoted +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteVoterPojo that = (VoteVoterPojo) o;
        return alreadyVoted == that.alreadyVoted && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alreadyVoted);
    }
}
