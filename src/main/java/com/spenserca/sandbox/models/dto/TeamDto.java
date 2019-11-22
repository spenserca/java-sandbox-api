package com.spenserca.sandbox.models.dto;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TeamDto {
    public Team toModel(@NotNull TeamDao teamDao) {
        return new Team(
            teamDao.getId(),
            teamDao.getName(),
            teamDao.getDescription(),
            teamDao.getModifiedDate()
        );
    }

    public TeamDao toDao(@NotNull Team team) {
        return new TeamDao(
            team.getTeamId(),
            team.getName(),
            team.getDescription(),
            team.getModifiedDate()
        );
    }
}
