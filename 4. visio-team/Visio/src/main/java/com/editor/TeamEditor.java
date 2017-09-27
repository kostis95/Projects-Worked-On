package com.editor;

import com.model.Team;
import com.service.TeamService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamEditor extends PropertyEditorSupport {

    @Autowired
    private TeamService teamService;

    // Converts a String team id to a Team (when submitting form)
    @Override
    public void setAsText(String text) {
        Team t = this.teamService.getTeam(Integer.valueOf(text));

        this.setValue(t);
    }

}
