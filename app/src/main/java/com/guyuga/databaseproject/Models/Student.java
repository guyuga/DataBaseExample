package com.guyuga.databaseproject.Models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by guyug on 21/2/2017.
 */

public class Student extends RealmObject {

    public Student() {
    }

    public Student(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    private String name;
    private String birthDate;
    private RealmList<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public RealmList<Course> getCourses() {
        return courses;
    }

    public void setCourses(RealmList<Course> courses) {
        this.courses = courses;
    }
}
