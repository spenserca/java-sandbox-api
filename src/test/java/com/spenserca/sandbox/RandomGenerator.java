package com.spenserca.sandbox;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import org.apache.commons.lang.RandomStringUtils;

import java.sql.Timestamp;
import java.util.Random;

public class RandomGenerator {
    public static Team getTeam() {
        return new Team(
            new Random().nextInt(),
            RandomStringUtils.randomAlphanumeric(10),
            RandomStringUtils.randomAlphanumeric(10),
            new Timestamp(new Random().nextLong())
        );
    }

    public static TeamDao getTeamDao() {
        return new TeamDao(
            new Random().nextInt(),
            RandomStringUtils.randomAlphanumeric(10),
            RandomStringUtils.randomAlphanumeric(10),
            new Timestamp(new Random().nextLong())
        );
    }
}
