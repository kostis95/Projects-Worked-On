/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author Kostis
 */

import com.model.Beacon;
import com.model.BeaconData;
import com.model.BeaconDataPK;
import com.model.BeaconDataView;
import com.model.DataType;
import com.model.VIT;
import com.service.BeaconDataService;
import com.service.BeaconService;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/beacondata")
public class BeaconDataController {
    
    @Autowired
    private BeaconDataService beaconDataService;
    @Autowired
    private BeaconService beaconService;
  

    @ModelAttribute("BeaconData")
    public BeaconData getModelAttribute() {
        return new BeaconData();
    }
    
//     @ModelAttribute("BeaconDataView")
//    public BeaconDataView getModelAttribute() {
//        return new BeaconDataView();
//    }
    
     @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ModelAndView ListBeaconData(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("beaconData");
        List<BeaconData> beaconDataList = beaconDataService.getBeaconDataById(id);
        view.addObject("beacondatalist", beaconDataList); 
        view.addObject("beaconid", id);
        List<VIT> VITList = beaconDataService.getVITS();
        view.addObject("vitlist", VITList);
        List<DataType> dataTypeList = beaconDataService.getDataTypes();
        view.addObject("datatypelist", dataTypeList);
        return view;
    }
    
//    modelattribute allen bij post
        @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public ModelAndView BeaconData(
            @ModelAttribute BeaconData beaconData,
        @PathVariable Integer id) {
            ModelAndView view = new ModelAndView("beaconData");
            beaconData.setBeaconDataPK(new BeaconDataPK());
            beaconData.getBeaconDataPK().setBeaconbeaconId(id);
            beaconData.getBeaconDataPK().setVITId(beaconData.getVit().getVITId());
            beaconData.getBeaconDataPK().setDataTypeId(beaconData.getDataType().getDataTypeId());
        try{
            beaconDataService.addBeaconData(beaconData);
        } catch (Exception ex) {
                Logger.getLogger(BeaconDataController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
        List<BeaconData> beaconDataList =   beaconDataService.getBeaconDataById(id);
        view.addObject("beacondatalist", beaconDataList); 
        view.addObject("beaconid", id);
        List<VIT> VITList = beaconDataService.getVITS();
        view.addObject("vitlist", VITList);
        List<DataType> dataTypeList = beaconDataService.getDataTypes();
        view.addObject("datatypelist", dataTypeList);
        return view;
    }
    

    
    
 
   
}
