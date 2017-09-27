/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Beacon;
import com.model.ExternalUser;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Youri
 */
@Repository
public class ExternalUserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addExternalUser(ExternalUser user) {
        // no HQL needed
        getCurrentSession().save(user);
    }

    public void updateExternalUser(ExternalUser user) {
        // ExternalUser userToUpdate = getUser(user.getId());
        /// userToUpdate.setLastName(user.getLastName());
        // userToUpdate.setLevelOfCoaching(user.getLevelOfCoaching());

        // getCurrentSession().update(userToUpdate);
    }

    public ExternalUser getExternalUser(int id) {
        hql = "from ExternalUser m where m.externalUserId= :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        if (query.list().isEmpty()) {
            return null;
        }

        ExternalUser user = (ExternalUser) query.list().get(0);
        return user;
    }
    @SuppressWarnings("unchecked")
    public List<ExternalUser> getExternalUsers(int[] beaconId) {
        ArrayList<Integer> list = new ArrayList();

        for (int id : beaconId) {
            list.add(id);
        }
        hql = "from ExternalUser m where m.externalUserId IN ( :id )";
        query = getCurrentSession().createQuery(hql);
        query.setParameterList("id", list);

        return (List<ExternalUser>) query.list();
    }

    public ExternalUser getExternalUserByToken(String token) {
        hql = "from ExternalUser m where m.apiToken= :token";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("token", token);

        if (query.list().isEmpty()) {
            return null;
        }

        ExternalUser user = (ExternalUser) query.list().get(0);
        return user;
    }

    public void deleteExternalUser(int id) {
        ExternalUser user = getExternalUser(id);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }

    public List<ExternalUser> getExternalUsers() {
        hql = "from ExternalUser";
        query = getCurrentSession().createQuery(hql);

        return (List<ExternalUser>) query.list();
    }

    public List<ExternalUser> search(String name) {
        hql = "from ExternalUser eu WHERE eu.name LIKE :name";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("name", "%" + name + "%");

        return (List<ExternalUser>) query.list();
    }

    public void storeAllExternalUsers(List<ExternalUser> users) {
        for (ExternalUser user : users) {
            getCurrentSession().save(user);
        }

    }

}
