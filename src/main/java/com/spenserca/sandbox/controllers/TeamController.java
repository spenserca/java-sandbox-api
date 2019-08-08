package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.Team;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TeamController {
    private TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

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
