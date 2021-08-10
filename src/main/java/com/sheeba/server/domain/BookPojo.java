package com.sheeba.server.domain;

public class BookPojo {
    private String id;
    private String name;
    private String isbnNo;
    private boolean borrowed;
    private String stdRollNo;

    public BookPojo(String id, String name, String isbnNo, boolean borrowed) {
        this.id = id;
        this.name = name;
        this.isbnNo = isbnNo;
        this.borrowed = borrowed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsbnNo() {
        return isbnNo;
    }

    public String getStdRollNo() {
        return stdRollNo;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setRollNo(String stdRollNo) {
        this.stdRollNo = stdRollNo;
    }

    public void removeRollNo(String stdRollNo1) {
        if (stdRollNo1.equals(stdRollNo)) {
            String emptyString = "";
            this.stdRollNo = emptyString;
        }
    }

    @Override
    public String toString() {
        return "BookPojo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isbnNo='" + isbnNo + '\'' +
                ", borrowed=" + borrowed +
                ", stdRollNo='" + stdRollNo + '\'' +
                '}';
    }
}
