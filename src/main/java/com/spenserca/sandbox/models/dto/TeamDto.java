package com.spenserca.sandbox.models.dto;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TeamDto {
    public Team toModel(@NotNull TeamDao teamDao) {
        return new Team(
            teamDao.getId(),
            teamDao.getName(),
            teamDao.getDescription(),
            teamDao.getCreatedDate()
        );
    }
}
