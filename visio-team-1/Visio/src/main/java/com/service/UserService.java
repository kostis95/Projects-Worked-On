/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.UserDAO;
import com.model.User;
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
public class UserService {
    
    @Autowired
    private UserDAO userDAO;
    
    public User getUser(int id) {
        return userDAO.getUser(id);
    }
    public void addUser(User user) {
        userDAO.addUser(user);
    }
    
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
        
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
    
    public User loginUser(String loginName, String password) {
        return (userDAO.loginUser(loginName, password));
    }
    
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
    
}
