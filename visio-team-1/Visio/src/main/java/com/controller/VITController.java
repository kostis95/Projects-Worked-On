/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Beacon;
import com.model.VIT;
import com.service.BeaconService;
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
public class VITController {

    @Autowired
    private VITService vITService;
    
    @Controller
    @RequestMapping(value = "/vits")
    public class BeaconController {

        @Autowired
        private VITService vITService;

        @ModelAttribute("VIT")
        public VIT getModelAttribute() {
            return new VIT();
        }
        
        @RequestMapping(value = "/overview", method = RequestMethod.GET)
        public ModelAndView overview() {
            ModelAndView view = new ModelAndView("vit/vits");
            List<VIT> vits = vITService.getVITs();
            view.addObject("vits", vits);
            return view;
        }
        
        @RequestMapping(value = "/add", method = RequestMethod.GET)
        public ModelAndView add() {
            ModelAndView view = new ModelAndView("vit/add");
            return view;
        }
        
        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public ModelAndView add(@ModelAttribute VIT vit) {
            
            vITService.addVIT(vit);
            
            ModelAndView view = new ModelAndView("vit/vits");
            view.addObject("vits", vITService.getVITs());
            return view;
        }
        
        @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
        public ModelAndView edit(@PathVariable Integer id) {
            ModelAndView view = new ModelAndView("vit/edit");
            view.addObject("vit", vITService.getVIT(id));
            return view;
        }
        
        @RequestMapping(value = "/edit", method = RequestMethod.POST)
        public ModelAndView edit(@ModelAttribute VIT vit) {
            
            vITService.updateVIT(vit);
            
            ModelAndView view = new ModelAndView("vit/vits");
            view.addObject("vits", vITService.getVITs());
            return view;
        }
        
        @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
        public ModelAndView delete(@PathVariable Integer id) {
            
            ModelAndView view = new ModelAndView("vit/vits");
            vITService.deleteVIT(id);
            view.addObject("vit", vITService.getVITs());
            return view;
        }
    }
}
