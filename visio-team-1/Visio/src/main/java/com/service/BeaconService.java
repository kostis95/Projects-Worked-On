/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.BeaconDAO;
import com.model.Beacon;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gabriël
 */
@Service
@Transactional
public class BeaconService {

    @Autowired
    private BeaconDAO beaconDAO;

    public Beacon getBeacon(int id) {
        return beaconDAO.getBeacon(id);
    }
    
    public Beacon getBeacon(String uuid, int major, int minor) {
        return beaconDAO.getBeacon(uuid, major, minor);
    }

    public void addBeacon(Beacon beacon) {
        beaconDAO.addBeacon(beacon);
    }

    public void updateBeacon(Beacon beacon) {
        beaconDAO.updateBeacon(beacon);
    }

    public void deleteBeacon(int id) {
        beaconDAO.deleteBeacon(id);
    }

    public List<Beacon> getAllBeacons() {
        return beaconDAO.getAllBeacons();
    }
    
    public List<Beacon> getBeaconsInRange(float range, float longitude, float latitude) {
        return beaconDAO.getBeaconsInRange(range, longitude, latitude);
    }
    
    public List<Beacon> searchBeacons(int[] beaconId) {
        return beaconDAO.getBeacons(beaconId);
    }
}
