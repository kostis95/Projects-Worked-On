/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Beacon;
import com.model.ExternalUser;
import com.model.Route;
import com.model.Waypoints;
import com.model.WaypointsPK;
import java.util.ArrayList;
import java.util.List;
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
public class RouteDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    
    public void addRoute(List<Beacon> beacons, String description, String begin, String eind, List<ExternalUser> externalUsers) {
        Route route = new Route(0, begin, eind);
        
        int routeId = 0;
                
        //TODO add externalusers id's
        route.setDescription(description);
        route.setExternalUserList(externalUsers);
        this.getCurrentSession().save(route);
        
        routeId = route.getRouteId();
        System.out.println("nieuwe route met id:" + routeId);
        
        ArrayList<Waypoints> waypoints = new ArrayList();
        
        for (int i = 0; i < beacons.size(); i++) {
            Waypoints waypoint = new Waypoints();            
            waypoint.setRoute(route);
            waypoint.setBeaconbeaconId(beacons.get(i));
            waypoint.setWaypointsPK(new WaypointsPK(routeId, i));
            
            waypoints.add(waypoint);
        }
        
        route.setWaypointsList(waypoints);
        this.getCurrentSession().saveOrUpdate(route);
    }
    
    public List<Route> getAllRoutes() {
        hql = "from Route";
        query = getCurrentSession().createQuery(hql);

        return (List<Route>) query.list();
    }
    
    public Route getRoute(int id){
        hql = "from Route r where r.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        return (Route) query.list().get(0);
    }

}
