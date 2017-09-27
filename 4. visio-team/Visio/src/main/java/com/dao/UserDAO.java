/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabriël
 */
@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User getUser(int id) {
        hql = "from User u where u.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        User user = (User) query.list().get(0);
        return user;
    }

    public User loginUser(String loginName, String password) {
        hql = "from User u where u.loginName = :loginName and u.password = :password";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("loginName", loginName);
        query.setParameter("password", password);
        if (!query.list().isEmpty()) {
            User user = (User) query.list().get(0);
            return user;
        }
        return null;
    }

    public void addUser(User user) {
        getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        if (user == null) {
            System.out.println("null!!!");
        }
        System.out.println(user.getUserId());
        User updateUser = getUser(user.getUserId());
        
        updateUser.setLoginName(user.getLoginName());
        updateUser.setPassword(user.getPassword());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAddress(user.getAddress());
        updateUser.setCity(user.getCity());
        updateUser.setRole(user.getRole());
        
        getCurrentSession().update(updateUser);
    }

    public void deleteUser(int id) {
        User user = getUser(id);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        hql = "from User";
        query = getCurrentSession().createQuery(hql);

        return (List<User>) query.list();
    }
}
