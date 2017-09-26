/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.API;//

import com.model.Beacon;
import com.model.ExternalUser;
import com.model.Route;
import com.project.api.TokenGenerator;
import com.project.http.HttpResponse;
import com.service.BeaconService;
import com.service.ExternalUserService;
import com.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller for handling incoming API requests
 *
 * @author Youri
 */
@RestController
public class APIController {

    private final String DEFAULT_API_PATH = "/api"; // pointer to the root url path of the API

    @Autowired
    private ExternalUserService externalUserService;
    
    @Autowired
    private BeaconService beaconService;
    
    @Autowired
    private RouteService routeService; 

    @RequestMapping(value = DEFAULT_API_PATH + "/user/{token}", method = RequestMethod.GET)
    public ResponseEntity<ExternalUser> getUser(@PathVariable("token") String token) {

        ExternalUser user = externalUserService.getExternalUserByToken(token);

        if (user == null) {
            return HttpResponse.DOESNT_EXIST.getResponseEntity();
        } else {
            return HttpResponse.SUCCES.getResponseEntity(user);
        }
    }

    @RequestMapping(value = DEFAULT_API_PATH + "/user/", method = RequestMethod.POST)
    public ResponseEntity<ExternalUser> insertUser(@RequestBody ExternalUser user) {

        if (user.getExternalUserId() == 0) {
            return HttpResponse.WRONG_FORMAT.getResponseEntity();
        }

        if (externalUserService.getExternalUser(user.getExternalUserId()) != null) {
            return HttpResponse.ALREADY_EXIST.getResponseEntity();
        }
        
       String token = TokenGenerator.getToken(user.getExternalUserId());
       
       if (token == null) {
           return HttpResponse.UNKNOWN_ERROR.getResponseEntity();
       }
       
       user.setApiToken(token);

        externalUserService.addExternalUser(user);

        if (externalUserService.getExternalUser(user.getExternalUserId()).equals(user)) {
            return HttpResponse.SUCCES.getResponseEntity(user);
        } else {
            return HttpResponse.UNKNOWN_ERROR.getResponseEntity();
        }
    }
    
    @RequestMapping(value = DEFAULT_API_PATH + "/beacon/{uuid}/{major}/{minor}", method = RequestMethod.GET)
    public ResponseEntity<Beacon> getBeacon(@PathVariable("uuid") String uuid, @PathVariable("major") int major, @PathVariable("minor") int minor) {
        Beacon beacon = beaconService.getBeacon(uuid, major, minor);

        if (beacon == null) {
            return HttpResponse.DOESNT_EXIST.getResponseEntity();
        } else {
            return HttpResponse.SUCCES.getResponseEntity(beacon);
        }
    }
    
    @RequestMapping(value = DEFAULT_API_PATH + "/route/{id}", method = RequestMethod.GET)
    public ResponseEntity<Route> getRoute(@PathVariable("id") int id) {
        Route route = routeService.getRoute(id); 

        if (route == null) {
            return HttpResponse.DOESNT_EXIST.getResponseEntity();
        } else {
            return HttpResponse.SUCCES.getResponseEntity(route);
        }
    }

}
