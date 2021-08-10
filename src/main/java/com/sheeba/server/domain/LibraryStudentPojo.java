package com.sheeba.server.domain;

import javax.persistence.*;

@Entity(name = "student")
public class LibraryStudentPojo {
    private String name;
    @Id
    private int rollNo;
    private String department;
    private int age;

    public LibraryStudentPojo(String name, int rollNo, String department, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.age = age;
    }

    public LibraryStudentPojo() {
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
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
