package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team implements Serializable {

    @GeneratedValue
    @Id
    private int id;
    private String name;
    private String yell;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Member> members;

    public Team() {
        super();
    }

    public Team(String name, String yell) {

        this.name = name;
        this.yell = yell;
        members = new ArrayList<Member>();
    }

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

    public String getYell() {
        return yell;
    }

    public void setYell(String yell) {
        this.yell = yell;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member m) {
        if (members.size() < 4) {
            members.add(m);
            m.setTeam(this);
        }
    }

    public void deleteMember(Member m) {
        members.remove(m);
        m.setTeam(null);

    }
}
