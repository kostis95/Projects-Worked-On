package com.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Coach")
public class Coach implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lastName = null;
    private int levelOfCoaching = 1;

    public Coach() {

    }

    public Coach(int id, String lastName, int levelOfCoaching) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.levelOfCoaching = levelOfCoaching;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLevelOfCoaching() {
        return levelOfCoaching;
    }

    public void setLevelOfCoaching(int levelOfCoaching) {
        this.levelOfCoaching = levelOfCoaching;
    }
}
