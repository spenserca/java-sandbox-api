package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamPostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamPostController.class);
    private TeamRepository teamRepository;
    private TeamDto teamDto;

    @Autowired
    public TeamPostController(
            TeamRepository teamRepository,
            TeamDto teamDto
    ) {
        this.teamRepository = teamRepository;
        this.teamDto = teamDto;
    }

    @PostMapping(path = "/teams")
    public ResponseEntity<TeamDao> post(@RequestBody Team team) {
        TeamDao teamDao = teamDto.toDao(team);

        TeamDao savedTeam = teamRepository.save(teamDao);

        return ResponseEntity.ok(savedTeam);
    }
}
