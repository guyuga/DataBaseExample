package com.guyuga.databaseproject.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by guyug on 21/2/2017.
 */

public class Course extends RealmObject {

    public Course() {
    }

    public Course(String name, String credits) {
        this.name = name;
        this.credits = credits;
    }

    @PrimaryKey
    private int id;
    private String name;
    private String credits;
    private int numberOfStudents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
