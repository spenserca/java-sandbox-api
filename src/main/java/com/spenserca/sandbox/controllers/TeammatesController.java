package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeammateDao;
import com.spenserca.sandbox.models.domain.Teammate;
import com.spenserca.sandbox.models.dto.TeammateDto;
import com.spenserca.sandbox.repositories.TeammateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeammatesController {
    private TeammateRepository teammateRepository;
    private TeammateDto teammateDto;

    @Inject
    public TeammatesController(
        TeammateRepository teammateRepository,
        TeammateDto teammateDto
    ) {
        this.teammateRepository = teammateRepository;
        this.teammateDto = teammateDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teammates")
    public ResponseEntity<List<Teammate>> get() {
        try {
            List<TeammateDao> teammateDaos = teammateRepository.findAll();

            if (!teammateDaos.isEmpty()) {
                List<Teammate> teammates = teammateDaos
                    .stream()
                    .map(teammateDto::toModel)
                    .collect(Collectors.toList());

                return ResponseEntity.ok(teammates);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting teammates", e);
        }

        return ResponseEntity.ok(Collections.emptyList());
    }
}
