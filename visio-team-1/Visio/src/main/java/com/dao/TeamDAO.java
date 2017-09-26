package com.dao;

import com.model.Member;
import com.model.Team;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addTeam(Team team) {
        getCurrentSession().save(team);
    }

    public void updateTeam(Team team) {
        Team teamToUpdate = getTeam(team.getId());
        teamToUpdate.setName(team.getName());
        teamToUpdate.setYell(team.getYell());

        getCurrentSession().update(teamToUpdate);

    }

    public List<Member> getMembers(int id) {
        hql = "from Team t where t.id= :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        return (List<Member>) query.list();

    }

    public Team getTeam(int id) {
        hql = "from Team t where t.id= :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        Team team = (Team) query.list().get(0);
        return team;
    }

    public void deleteTeam(int id) {
        Team team = getTeam(id);
        if (team != null) {
            getCurrentSession().delete(team);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Team> getTeams() {
        hql = "from Team";
        query = getCurrentSession().createQuery(hql);

        return (List<Team>) query.list();

    }

    public void storeAllTeams(List<Team> teams) {

        for (Team team : teams) {
            getCurrentSession().save(team);
        }

    }
}
