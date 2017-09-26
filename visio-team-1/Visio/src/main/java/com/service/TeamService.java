package com.service;

import com.dao.TeamDAO;
import com.model.Team;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamDAO teamDAO;

    public void addTeam(Team team) {
        teamDAO.addTeam(team);
    }

    public void updateTeam(Team team) {
        teamDAO.updateTeam(team);
    }

    public Team getTeam(int id) {
        return teamDAO.getTeam(id);
    }

    public void deleteTeam(int id) {
        teamDAO.deleteTeam(id);
    }

    public List<Team> getTeams() {
        return teamDAO.getTeams();
    }

    public void storeAllTeams(List<Team> teams) {

        teamDAO.storeAllTeams(teams);
    }
    /*
    public List<Member> getMembers(Team team) {
        List<Member> members=teamDAO.getMembers(team.getId());
        return members;
    }
     */
}
