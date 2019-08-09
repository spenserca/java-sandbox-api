package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.Team;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class TeamController {
    private TeamRepository teamRepository;

    @Inject
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teams")
    public ResponseEntity<List<Team>> get() {
        List<Team> teams;

        try {
            teams = teamRepository.getTeams();
        } catch (Exception e) {
            throw new RuntimeException("Error getting teams", e);
        }

        return ResponseEntity.ok(teams);
    }
}
