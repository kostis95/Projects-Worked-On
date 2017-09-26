/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.BeaconData;
import com.model.DataType;
import com.model.VIT;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Gabriël
 */
@Repository
public class BeaconDataDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
//    Query wordt gecreerd met input parameter beaconId en wordt vervolgens uitgevoerd door hibernate
//    waarna de resultaten worden teruggegeven.
        @SuppressWarnings("unchecked")
        
    public List<BeaconData> getAllBeaconData(int beaconId) {
        hql = "FROM BeaconData b WHERE b.beaconDataPK.beaconbeaconId = " + beaconId;
        query = getCurrentSession().createQuery(hql);
        return (List<BeaconData>) query.list();
    }
    
    public List<VIT> getVITS() {
        hql = "FROM VIT";
        query = getCurrentSession().createQuery(hql);
        return (List<VIT>) query.list();
    }
    
    public List<DataType> getDataTypes() {
        hql = "FROM DataType";
        query = getCurrentSession().createQuery(hql);
        return (List<DataType>) query.list();
    }
    
      
       
    public BeaconData getBeaconData(int beaconId, int vitId, int dataTypeId) {
        hql = "FROM BeaconData b WHERE b.beaconDataPK.beaconId = :beaconId AND b.beaconDataPK.vITId = :vitId AND b.beaconDataPK.dataTypeId = :dataTypeId";
        query = getCurrentSession().createQuery(hql);
        System.out.println(query.getQueryString());
        query.setParameter("beaconId", beaconId);
        query.setParameter("vitId", vitId);
        query.setParameter("dataTypeId", dataTypeId);
        
        BeaconData beaconData = (BeaconData) query.list().get(0);

        return beaconData;
    }    

    public void addBeaconData(BeaconData beaconData) {
        getCurrentSession().save(beaconData);
    }
    
    

    public void updateBeaconData(BeaconData beaconData) {
        getCurrentSession().update(beaconData);
    }

    public void deleteBeaconData(int beaconId, int vitId, int dataTypeId) {
        BeaconData beaconData = getBeaconData(beaconId, vitId, dataTypeId);
        if(beaconData != null) {
            getCurrentSession().delete(beaconData);
        }
        
        getCurrentSession().delete(beaconData);
    }
}
