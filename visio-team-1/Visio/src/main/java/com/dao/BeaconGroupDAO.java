/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Beacon;
import com.model.BeaconGroup;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gerbrecht
 */

@Repository
public class BeaconGroupDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void addBeaconGroup(BeaconGroup beaconGroup) {
        getCurrentSession().save(beaconGroup);
    }
    
    public void updateBeaconGroup(BeaconGroup beaconGroup) {
        getCurrentSession().update(beaconGroup);
    }
    
    @SuppressWarnings("unchecked")
    public List<BeaconGroup> getAllBeaconGroups() {
        hql = "from BeaconGroup";
        query = getCurrentSession().createQuery(hql);

        return (List<BeaconGroup>) query.list();
    }
    
    public BeaconGroup getBeaconGroup(int id) {
        hql = "from BeaconGroup beaconGroup where beaconGroup.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        BeaconGroup beaconGroup = (BeaconGroup) query.list().get(0);
        return beaconGroup;
    }
    
}
