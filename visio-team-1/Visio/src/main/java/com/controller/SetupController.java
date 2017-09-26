package com.controller;

import com.model.Member;
import com.model.Team;
import com.service.MemberService;
import com.service.TeamService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Peter
 */
@Controller

public class SetupController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/dba")
    public ModelAndView ledenlijst() {
        ModelAndView menuView = new ModelAndView("menu");
        dbSetup();
        String message = "Database setup was successfull.";
        menuView.addObject("message", message);
        return menuView;

    }

    private void dbSetup() {
        List<Member> members = new ArrayList<Member>();
        Member member = new Member(0, "Adrie", "Aardnoot", null);
        members.add(member);
        member = new Member(0, "Brutus", "Braaf", null);
        members.add(member);
        member = new Member(0, "Charlotte", "Chocola", null);
        members.add(member);
        member = new Member(0, "Dirk", "Draaikont", null);
        members.add(member);
        member = new Member(0, "Elsbeth", "Everzwijn", null);
        members.add(member);
        member = new Member(0, "Erik", "Everzwijn", null);
        members.add(member);
        member = new Member(0, "Eduard", "Everzwijn", null);
        members.add(member);
        member = new Member(0, "Frits", "Fabriek", null);
        members.add(member);

        List<Team> teams = new ArrayList<Team>();
        Team team = new Team("Les champions du monde", "Du pain, du vin, du jeu de boules!");
        teams.add(team);
        team.addMember(members.get(0));
        team.addMember(members.get(2));
        team.addMember(members.get(7));

        team = new Team("Les champignons du monde", "Go, go, go!");
        team.addMember(members.get(1));
        team.addMember(members.get(3));
        teams.add(team);

        team = new Team("Comme des Francais", "Alors, enfants du jeu de boules!");
        teams.add(team);

        teamService.storeAllTeams(teams);
        memberService.storeAllMembers(members);
    }
}
