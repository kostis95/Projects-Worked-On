/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.BeaconGroupDAO;
import com.model.Beacon;
import com.model.BeaconGroup;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerbrecht
 */

@Service
@Transactional
public class BeaconGroupService {
    @Autowired
    private BeaconGroupDAO beaconGroupDAO;
    
    
    public void addBeaconGroup(BeaconGroup beaconGroup){
        beaconGroupDAO.addBeaconGroup(beaconGroup);
    }
    
    public void updateBeaconGroup(BeaconGroup beaconGroup) {
        beaconGroupDAO.updateBeaconGroup(beaconGroup);
    }
    
    public List<BeaconGroup> getAllBeaconGroups() {
        return beaconGroupDAO.getAllBeaconGroups();
    }
    
    public BeaconGroup getBeaconGroup(int id) {
        return beaconGroupDAO.getBeaconGroup(id);
    }
}
