package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TeamListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamListController.class);
    private final TeamRepository teamRepository;
    private final TeamDto teamDto;

    @Inject
    public TeamListController(
        TeamRepository teamRepository,
        TeamDto teamDto
    ) {
        this.teamRepository = teamRepository;
        this.teamDto = teamDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teams")
    public ResponseEntity<List<Team>> get() {
        LOGGER.info("TeamController.get called");

        try {
            List<TeamDao> teamDaos = teamRepository.findAll();

            if (!teamDaos.isEmpty()) {
                List<Team> teams = teamDaos
                    .stream()
                    .map(teamDto::toModel)
                    .collect(Collectors.toList());

                return ResponseEntity.ok(teams);
            }

            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException("Error getting teams", e);
        }
    }
}
