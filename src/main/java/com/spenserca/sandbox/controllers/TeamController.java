package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeamController {
    private TeamRepository teamRepository;
    private TeamDto teamDto;

    @Inject
    public TeamController(
        TeamRepository teamRepository,
        TeamDto teamDto
    ) {
        this.teamRepository = teamRepository;
        this.teamDto = teamDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teams")
    public ResponseEntity<List<Team>> getAll() {
        try {
            List<TeamDao> teamDaos = teamRepository.findAll();

            if (!teamDaos.isEmpty()) {
                List<Team> teams = teamDaos
                    .stream()
                    .map(teamDto::toModel)
                    .collect(Collectors.toList());

                return ResponseEntity.ok(teams);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting teams", e);
        }

        return ResponseEntity.ok(Collections.emptyList());
    }
}
