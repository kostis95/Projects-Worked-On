/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Beacon;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabriël
 */
@Repository
public class BeaconDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Beacon getBeacon(int id) {
        hql = "from Beacon b where b.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        Beacon beacon = (Beacon) query.list().get(0);
        return beacon;
    }

    public Beacon getBeacon(String uuid, int major, int minor) {
        hql = "from Beacon b where b.uuid = :uuid AND b.major = :major AND b.minor = :minor";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("uuid", uuid);
        query.setParameter("major", major);
        query.setParameter("minor", minor);

        Beacon beacon = (Beacon) query.list().get(0);
        return beacon;
    }
    
    public void addBeacon(Beacon beacon) {
        getCurrentSession().save(beacon);
    }

    public void updateBeacon(Beacon beacon) {
        getCurrentSession().update(beacon);
    }

    public void deleteBeacon(int id) {
        Beacon beacon = getBeacon(id);
        if (beacon != null) {
            getCurrentSession().delete(beacon);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Beacon> getAllBeacons() {
        hql = "from Beacon";
        query = getCurrentSession().createQuery(hql);

        return (List<Beacon>) query.list();
    }
    
    public List<Beacon> getBeaconsInRange(float range, float longitude, float latitude) {
        hql = "from Beacon b where "
                + "(b.longitude - :range < :longitude) and (b.longitude + :range > :longitude) and "
                + "(b.latitude - :range < :latitude) and (b.latitude + :range > :latitude)";
        
        query = getCurrentSession().createQuery(hql);
        query.setParameter("range", Math.abs(range));
        query.setParameter("longitude", longitude);
        query.setParameter("latitude", latitude);
        
        System.out.println(query.getQueryString());
        
        return (List<Beacon>) query.list();
    }        
        
    @SuppressWarnings("unchecked")
    public List<Beacon> getBeacons(int[] beaconId) {
        ArrayList<Integer> list = new ArrayList();
        
        for (int id : beaconId) {
            list.add(id);
        }
        hql = "from Beacon b where b.id IN (:id)";
        query = getCurrentSession().createQuery(hql);
        query.setParameterList("id", list);
        
        System.out.println("lijst met beacon waypoints: " + list.toString());

        return (List<Beacon>) query.list();
    }

}
