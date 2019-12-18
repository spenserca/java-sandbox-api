package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeammateDao;
import com.spenserca.sandbox.models.domain.Teammate;
import com.spenserca.sandbox.models.dto.TeammateDto;
import com.spenserca.sandbox.repositories.TeammateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeammateListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeammateListController.class);
    private TeammateRepository teammateRepository;
    private TeammateDto teammateDto;

    @Autowired
    public TeammateListController(
            TeammateRepository teammateRepository,
            TeammateDto teammateDto
    ) {
        this.teammateRepository = teammateRepository;
        this.teammateDto = teammateDto;
    }

    @GetMapping(path = "/teammates")
    public ResponseEntity<List<Teammate>> get() {
        LOGGER.info("TeamListController called");

        try {
            List<TeammateDao> teammateDaos = teammateRepository.findAll();

            if (!teammateDaos.isEmpty()) {
                List<Teammate> teammates = teammateDaos
                        .stream()
                        .map(teammateDto::toModel)
                        .collect(Collectors.toList());

                return ResponseEntity.ok(teammates);
            }

            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException("Error getting teammates", e);
        }
    }
}
