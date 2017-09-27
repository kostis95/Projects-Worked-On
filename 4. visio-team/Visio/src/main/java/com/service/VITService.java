/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.VITDAO;
import com.model.User;
import com.model.VIT;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Gabriël
 */
@Service
@Transactional
public class VITService {
    
    @Autowired
    private VITDAO vitDAO;
    
    public VIT getVIT(int id) {
        return vitDAO.getVIT(id);
    }
    public void addVIT(VIT vit) {
        vitDAO.addVIT(vit);
    }
    
    public void updateVIT(VIT vit) {
        vitDAO.updateVIT(vit);
    }
        
    public void deleteVIT(int id) {
        vitDAO.deleteVIT(id);
    }
    
    public List<VIT> getVITs() {
        return vitDAO.getVITs();
    }
    
}
