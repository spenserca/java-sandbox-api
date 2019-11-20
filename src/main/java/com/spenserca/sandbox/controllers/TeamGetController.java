package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Optional;

@RestController
public class TeamGetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamGetController.class);
    private TeamRepository teamRepository;
    private TeamDto teamDto;

    @Inject
    public TeamGetController(
        TeamRepository teamRepository,
        TeamDto teamDto
    ) {
        this.teamRepository = teamRepository;
        this.teamDto = teamDto;
    }

    @GetMapping(path = "/teams/{id}")
    public ResponseEntity<Team> get(@PathVariable int id) {
        LOGGER.info("TeamController.get called with id: " + id);

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
