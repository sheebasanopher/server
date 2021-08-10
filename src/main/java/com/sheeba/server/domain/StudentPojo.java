package com.sheeba.server.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentPojo {
    private String name;
    private String rollNo;
    private String department;
    private int age;
    private List<String> bookIdList = new ArrayList<>();

    public StudentPojo(String name, String rollNo, String department, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public List<String> getBookIdList() {
        return bookIdList;
    }

    public void addIdBook(String bookId) {
        this.bookIdList.add(bookId);
    }

    public void removeId(String bookId) {
        this.bookIdList.remove(bookId);
    }


    @Override
    public String toString() {
        return "StudentPojo{" +
                "name='" + name + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                '}';
    }

}
