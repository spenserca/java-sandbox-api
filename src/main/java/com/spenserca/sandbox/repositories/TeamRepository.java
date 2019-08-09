package com.spenserca.sandbox.repositories;

import com.spenserca.sandbox.models.Team;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TeamRepository {
    public List<Team> getTeams() {
        return Collections.emptyList();
    }
}
