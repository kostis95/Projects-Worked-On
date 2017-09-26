/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.ExternalUser;
import com.model.SearchModel;
import com.service.ExternalUserService;
import com.service.VITService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author PC
 */
@Controller
@RequestMapping(value = "/externaluser")
public class ExternalUserController {

    @Autowired
    private ExternalUserService externalUserService;
    
    @Autowired
    private VITService VITService;

    @ModelAttribute("ExternalUser")
    public ExternalUser getModelAttribute() {
        return new ExternalUser();
    }
    
    @ModelAttribute("SearchModel")
    public SearchModel getSearchModelAttribute() {
        return new SearchModel();
    }
     
    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public ModelAndView overview() {
        ModelAndView view = new ModelAndView("externaluser/overview");

        return view;
    }
    
    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    public ModelAndView searchResult(@ModelAttribute SearchModel searchModel) {
        ModelAndView view = new ModelAndView("externaluser/overview");
        List<ExternalUser> externalUsers = externalUserService.search(searchModel.getFilter());
        view.addObject("externalUsers", externalUsers);
        
        return view;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer euid) {
        ModelAndView view = new ModelAndView("externaluser/edit");
        ExternalUser euser = externalUserService.getExternalUser(euid);
        view.addObject("externalUser", euser);
        view.addObject("VITs", VITService.getVITs());
        view.addObject("genders", new String[] {"M", "V"});
        
        return view;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView test(@ModelAttribute("ExternalUser") ExternalUser externalUser) {
        ModelAndView view = new ModelAndView("externaluser/edit");
        externalUserService.updateExternalUser(externalUser);

        return view;
    }
}
