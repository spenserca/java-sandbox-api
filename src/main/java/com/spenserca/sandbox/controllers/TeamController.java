package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TeamController {
    private TeamRepository teamRepository;

    @Inject
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teams")
    public ResponseEntity<List<Team>> get() {
        try {
            Optional<List<TeamDao>> teamDaos = teamRepository.getAll();

            if (teamDaos.isPresent()) {
                List<Team> teams = teamDaos.get()
                    .stream()
                    .map((td) -> new Team(td.getId(), td.getName(), td.getDescription(), td.getCreatedDate()))
                    .collect(Collectors.toList());

                return ResponseEntity.ok(teams);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting teams", e);
        }

        return ResponseEntity.ok(Collections.emptyList());
    }
}
