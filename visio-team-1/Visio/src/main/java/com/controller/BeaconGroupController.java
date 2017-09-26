/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.BeaconGroup;
import com.service.BeaconGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gerbrecht
 */

@Controller
@RequestMapping(value = "/beaconGroups")
public class BeaconGroupController {

    @Autowired
    private BeaconGroupService beaconGroupService;
    
    
     
    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public ModelAndView groupIds() {
        ModelAndView view = new ModelAndView("beacongroup/overview");
        List<BeaconGroup> beaconGroups = beaconGroupService.getAllBeaconGroups();
        view.addObject("beaconGroup", beaconGroups);

        return view;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addBeaconGroup() {
        return new ModelAndView("beacongroup/add");
    }


}
