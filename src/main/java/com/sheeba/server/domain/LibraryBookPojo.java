package com.sheeba.server.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "book")
public class LibraryBookPojo {
    @Id
    private int id;
    private String name;
    private String isbnNo;
    private boolean borrowed;
    private int stdRollNo;

    public LibraryBookPojo(int id, String name, String isbnNo, boolean borrowed) {
        this.id = id;
        this.name = name;
        this.isbnNo = isbnNo;
        this.borrowed = borrowed;
    }
    public LibraryBookPojo(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsbnNo() {
        return isbnNo;
    }

    public int getStdRollNo() {
        return stdRollNo;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setRollNo(int stdRollNo) {
        this.stdRollNo = stdRollNo;
    }

    public void removeRollNo(int stdRollNo1) {
        if (stdRollNo1 == stdRollNo) {
            int empty = 0;
            this.stdRollNo = empty;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryBookPojo that = (LibraryBookPojo) o;
        return id == that.id && borrowed == that.borrowed && stdRollNo == that.stdRollNo && Objects.equals(name, that.name) && Objects.equals(isbnNo, that.isbnNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isbnNo, borrowed, stdRollNo);
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
