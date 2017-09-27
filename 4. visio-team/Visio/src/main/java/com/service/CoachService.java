package com.service;

import com.dao.CoachDAO;
import com.model.Coach;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoachService {

    @Autowired
    private CoachDAO coachDAO;

    public void addCoach(Coach coach) {
        coachDAO.addCoach(coach);
    }

    public void updateCoach(Coach coach) {
        coachDAO.updateCoach(coach);
    }

    public Coach getCoach(int id) {
        return coachDAO.getCoach(id);
    }

    public void deleteCoach(int id) {
        coachDAO.deleteCoach(id);
    }

    public List<Coach> getCoaches() {
        return coachDAO.getCoaches();
    }

    public void storeAllCoaches(List<Coach> coaches) {

        coachDAO.storeAllCoaches(coaches);

    }
}
