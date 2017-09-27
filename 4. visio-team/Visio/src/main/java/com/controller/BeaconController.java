package com.controller;

/**
 *
 * @author gerbrecht & Kostis
 */
import com.model.Beacon;
import com.model.BeaconGroup;
import com.service.BeaconDataService;
import com.service.BeaconGroupService;
import com.service.BeaconService;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/beacons")
public class BeaconController {

    @Autowired
    private BeaconService beaconService;

    @ModelAttribute("Beacon")
    public Beacon getModelAttribute() {
        return new Beacon();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView beacons() {
        ModelAndView view = new ModelAndView("beacon/search");
        List<Beacon> beacons = beaconService.getAllBeacons();
        view.addObject("beacons", beacons);
        view.addObject("zoomLevel", 7);
        view.addObject("centerLat", 52.379189);
        view.addObject("centerLon", 4.899431);
        return view;
    }

    @RequestMapping(value = "search/{lat}/{lon}", method = RequestMethod.GET)
    public ModelAndView beaconsLocationSearch(@PathVariable("lon") float lon, @PathVariable("lat") float lat) {

        ModelAndView view = new ModelAndView("beacon/search");

        List<Beacon> beacons = beaconService.getBeaconsInRange(1, lon, lat);
        view.addObject("beacons", beacons);
        view.addObject("zoomLevel", 10);
        view.addObject("centerLat", lat);
        view.addObject("centerLon", lon);
        return view;
    }

    @RequestMapping(value = "/groupIds", method = RequestMethod.GET)
    public ModelAndView groupIds() {
        ModelAndView view = new ModelAndView("groupIds");
        BeaconGroupService beaconGroupService = new BeaconGroupService();
        List<BeaconGroup> beaconGroups = beaconGroupService.getAllBeaconGroups();
        view.addObject("beaconGroup", beaconGroups);

        return view;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editBeaconPage(@PathVariable("id") Integer id) throws IOException {

        ModelAndView editBeaconView = new ModelAndView("/beacon/edit");
        Beacon beacon = beaconService.getBeacon(id);
        editBeaconView.addObject("beacon", beacon);

        return editBeaconView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletebeacon(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("beacon/search");
        beaconService.deleteBeacon(id);
        List<Beacon> beacons = beaconService.getAllBeacons();
        modelAndView.addObject("beacons", beacons);
        String message = "beacon was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView beaconPage() {
        ModelAndView modelAndView = new ModelAndView("beacon/add");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addBeacon(@ModelAttribute Beacon beacon) {
        ModelAndView view = new ModelAndView("beacon/search");
        beaconService.addBeacon(beacon); 
        
        String message = "Beacon is toegevoegd"; 
        view.addObject("message", message);
        
        return view;
    }

    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    public ModelAndView EditBeaconData(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("beaconData");
        List<Beacon> beacons = beaconService.getAllBeacons();
        view.addObject("beacons", beacons);
        return view;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editBeaconView(@ModelAttribute("beacon") Beacon beacon) throws IOException {
        ModelAndView beaconView = new ModelAndView("/beacon/search");

        beaconService.updateBeacon(beacon);
        List<Beacon> beacons = beaconService.getAllBeacons();
        beaconView.addObject("beacon", beacons);

        String message = "Beacon was succesfully edited.";
        beaconView.addObject("message", message);

        return beaconView;
    }
}
