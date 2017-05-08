package com.students.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Student {

    @Id
    @Unique
    private Long id;

    private String name;

    private String surname;

    private int course;

    private String occupation;

    private int results;

    @Generated(hash = 1936740436)
    public Student(Long id, String name, String surname, int course,
            String occupation, int results) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course = course;
        this.occupation = occupation;
        this.results = results;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCourse() {
        return this.course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getResults() {
        return this.results;
    }

    public void setResults(int results) {
        this.results = results;
    }

}
