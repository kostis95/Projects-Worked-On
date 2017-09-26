package com.dao;

import com.model.Coach;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoachDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addCoach(Coach coach) {
        // no HQL needed
        getCurrentSession().save(coach);
    }

    public void updateCoach(Coach coach) {
        Coach coachToUpdate = getCoach(coach.getId());
        coachToUpdate.setLastName(coach.getLastName());
        coachToUpdate.setLevelOfCoaching(coach.getLevelOfCoaching());

        getCurrentSession().update(coachToUpdate);

    }

    public Coach getCoach(int id) {

        hql = "from Coach m where m.id= :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        Coach coach = (Coach) query.list().get(0);
        return coach;
    }

    public void deleteCoach(int id) {
        Coach coach = getCoach(id);
        if (coach != null) {
            getCurrentSession().delete(coach);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Coach> getCoaches() {
        hql = "from Coach";
        query = getCurrentSession().createQuery(hql);

        return (List<Coach>) query.list();
    }

    public void storeAllCoaches(List<Coach> coaches) {

        for (Coach coach : coaches) {
            getCurrentSession().save(coach);
        }

    }
}
