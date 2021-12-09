package com.company;

public class Student {
    public String name;
    public String surname;
    public int numberOfTicket;
    public int numberOfGroup;
    public int[] marks;

    public Student(String surname, String name, int numberOfTicket, int numberOfGroup, int[] marks) {
        this.surname = surname;
        this.name = name;
        this.numberOfTicket = numberOfTicket;
        this.numberOfGroup = numberOfGroup;
        this.marks = marks;
    }
}
