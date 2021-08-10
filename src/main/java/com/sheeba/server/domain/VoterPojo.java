package com.sheeba.server.domain;

public class VoterPojo {
    private String name;
    private String id;
    private boolean alreadyVoted;
    private int age;

    public VoterPojo(String name, String id, boolean alreadyVoted, int age) {
        this.name = name;
        this.id = id;
        this.alreadyVoted = alreadyVoted;
        this.age = age;
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
        return "VoterPojo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", alreadyVoted=" + alreadyVoted +
                ", age=" + age +
                '}';
    }
}