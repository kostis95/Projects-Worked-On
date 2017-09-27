/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.DataType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Gjow - Admin
 */
@Repository
public class DataTypeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public DataType getDataType(int id) {
        hql = "from DataType dt where dt.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        
        DataType dataType = (DataType) query.list().get(0); 
        return dataType;
    }
    
    public void addDataType(DataType dataType) {
        getCurrentSession().save(dataType);
    }
    
    public void updateDataType(DataType dataType) {
        getCurrentSession().update(dataType);
    }

    public void deleteDataType(int id) {
        DataType dataType = getDataType(id);
        if (dataType != null) {
            getCurrentSession().delete(dataType);
        }
    }

    @SuppressWarnings("unchecked")
    public List<DataType> getDataTypes() {
        hql = "from DataType";
        query = getCurrentSession().createQuery(hql);

        return (List<DataType>) query.list();
    }
}
