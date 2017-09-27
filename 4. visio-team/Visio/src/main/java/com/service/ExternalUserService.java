/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.ExternalUserDAO;
import com.model.ExternalUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Youri
 */
@Service
@Transactional
public class ExternalUserService {
    
    @Autowired
    private ExternalUserDAO userDAO;

    public void addExternalUser(ExternalUser user) {
        userDAO.addExternalUser(user);
    }

    public void updateExternalUser(ExternalUser user) {
        userDAO.updateExternalUser(user);
    }
    
    public ExternalUser getExternalUser(int id) {
        return userDAO.getExternalUser(id);
    }

    public ExternalUser getExternalUserByToken(String token) {
        return userDAO.getExternalUserByToken(token);
    }

    public void deleteExternalUser(int id) {
        userDAO.deleteExternalUser(id);
    }

    public List<ExternalUser> getExternalUsers() {
        return userDAO.getExternalUsers();
    }
    
    public List<ExternalUser> search(String name) {
        return userDAO.search(name);
    }

    public void storeAllExternalUsers(List<ExternalUser> users) {
        userDAO.storeAllExternalUsers(users);
    }
    
    public ExternalUserDAO getDAO() {
        return userDAO;
    }
    
}
