package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TeamController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);
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
        LOGGER.info("TeamController.getAll called");

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

    @GetMapping(path = "/teams/{id}")
    public ResponseEntity<Team> getById(@PathVariable int id) {
        LOGGER.info("TeamController.getById called with id: " + id);

        try {
            Optional<TeamDao> optionalTeamDao = teamRepository.findById(id);

            if (optionalTeamDao.isPresent()) {
                Team team = teamDto.toModel(optionalTeamDao.get());

                return ResponseEntity.ok(team);
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new RuntimeException("Error getting teams", e);
        }
    }
}
