/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.BeaconDAO;
import com.dao.ExternalUserDAO;
import com.dao.RouteDAO;
import com.model.ExternalUser;
import com.model.Route;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Youri
 */
@Service
@Transactional
public class RouteService {

    @Autowired
    private RouteDAO routeDAO;
    @Autowired
    private BeaconDAO BeaconDAO;
    @Autowired
    private ExternalUserDAO ExternalUserDAO;

    public void addRoute(int[] beacons, String description, String begin, String eind, int[] externalUserId) {

        routeDAO.addRoute(BeaconDAO.getBeacons(beacons), description, begin, eind, ExternalUserDAO.getExternalUsers(externalUserId));
    }

    public List<Route> getAllRoutes() {
        return routeDAO.getAllRoutes();
    }
    
    public Route getRoute(int id) {
        return routeDAO.getRoute(id);
    }

}
