/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author gerbrecht
 */
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.model.Beacon;
import com.model.ExternalUser;
import com.model.Route;
import com.service.BeaconService;
import com.service.ExternalUserService;
import com.service.RouteService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class routeMainController {

    @Autowired
    private BeaconService service;

    @Autowired
    private RouteService routeService;
    
    @Autowired
    private ExternalUserService exUserService;
       

    @RequestMapping(value = "/routeMain", method = RequestMethod.GET)
    public ModelAndView routeMain() {
        ModelAndView view = new ModelAndView("route/routeMain");

        Hibernate.initialize(routeService.getAllRoutes());

        List<Beacon> beacons = service.getAllBeacons();
        view.addObject("beacons", beacons);

        List<Route> routes = routeService.getAllRoutes();
        view.addObject("routes", routes);
        
        List<ExternalUser> externalUsers = exUserService.getExternalUsers();
        view.addObject("externalUsers", externalUsers);

        return view;
    }

    @RequestMapping(value = "/routeMain", method = RequestMethod.POST)
    public ModelAndView routeMainPost(@RequestBody String jsonRoute) {
        System.out.println("Beacon: " + jsonRoute);
        JsonObject jobj = new Gson().fromJson(jsonRoute, JsonObject.class);

        String begin = jobj.get("begin").toString();
        String end = jobj.get("end").toString();
        String description = jobj.get("description").toString();

        begin = begin.replaceAll("^\"|\"$", "");
        end = end.replaceAll("^\"|\"$", "");
        description = description.replaceAll("^\"|\"$", "");
        
        JsonArray externalUsers = jobj.getAsJsonArray("externalUsers");
        JsonArray array = jobj.getAsJsonArray("waypoints");
        if (array.size() == 0 || externalUsers.size() == 0) {
            System.out.println("geen waypoints of user geselecteerd");
        }else{
            int[] beacondsIds = new int[array.size()];
            int[] externalUserIds = new int[externalUsers.size()];
            for (int i = 0; i < array.size(); i++) {
                beacondsIds[i] = array.get(i).getAsInt();
            }
            for (int i = 0; i < externalUsers.size(); i++) {
                externalUserIds[i] = externalUsers.get(i).getAsInt();
            }
            System.out.println("waypoitns: " + Arrays.toString(beacondsIds));
            System.out.println("externaluserids: " + Arrays.toString(externalUserIds));

            routeService.addRoute(beacondsIds, description, begin, end, externalUserIds);
            System.out.println("done");
        }        
        return null;
    }   
        
    @RequestMapping(value = "/route/view/{id}", method = RequestMethod.GET)
    public ModelAndView routeChange(@PathVariable("id") Integer id) throws IOException  {
        ModelAndView view = new ModelAndView("route/routeEdit");
        //geef bestaande route mee
        Route route = routeService.getRoute(id);
        
        
        view.addObject("route", route);

        view.addObject("waypoints", route.getWaypointsList());
        view.addObject("users", route.getExternalUserList());
        
        return view;
    }
}
