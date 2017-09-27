/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.VIT;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PC
 */
@Repository
public class VITDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public VIT getVIT(int id) {
        hql = "from VIT v where v.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        VIT vit = (VIT) query.list().get(0);
        return vit;
    }

    public void addVIT(VIT vit) {
        getCurrentSession().save(vit);
    }

    public void updateVIT(VIT vit) {
        getCurrentSession().update(vit);
    }

    public void deleteVIT(int id) {
        VIT vit = getVIT(id);
        if (vit != null) {
            getCurrentSession().delete(vit);
        }
    }

    @SuppressWarnings("unchecked")
    public List<VIT> getVITs() {
        hql = "from VIT";
        query = getCurrentSession().createQuery(hql);

        return (List<VIT>) query.list();
    }
}
