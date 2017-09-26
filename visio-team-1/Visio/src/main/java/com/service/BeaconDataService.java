/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.BeaconDataDAO;
import com.model.BeaconData;
import com.model.DataType;
import com.model.VIT;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gabriël
 */
@Service
@Transactional
public class BeaconDataService {
    
    @Autowired
    private BeaconDataDAO beaconDataDAO;
    
    public List<BeaconData> getBeaconDataById(int beaconId) {
        return beaconDataDAO.getAllBeaconData(beaconId);
    }
    
    public List<VIT> getVITS() {
        return beaconDataDAO.getVITS();
    }
    
      public List<DataType> getDataTypes() {
        return beaconDataDAO.getDataTypes();
    }
    
    public BeaconData getBeaconData(int beaconId, int vitId, int dataTypeId) {
        return beaconDataDAO.getBeaconData(beaconId, vitId, dataTypeId);
    }
    
    public void addBeaconData(BeaconData beaconData) {
        beaconDataDAO.addBeaconData(beaconData);
    }
    
    public void updateBeaconData(BeaconData beaconData) {
        beaconDataDAO.updateBeaconData(beaconData);
    }
    
    public void deleteBeaconData(int beaconId, int vitId, int dataTypeId) {
        beaconDataDAO.deleteBeaconData(beaconId, vitId, dataTypeId);
    }
}
