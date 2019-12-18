package com.spenserca.sandbox;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.dao.TeammateDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.domain.Teammate;
import org.assertj.core.internal.bytebuddy.utility.RandomString;

import java.sql.Timestamp;
import java.util.Random;

public class RandomGenerator {
    public static Team getTeam() {
        return new Team(
                new Random().nextInt(),
                RandomString.make(10),
                RandomString.make(10),
                new Timestamp(new Random().nextLong())
        );
    }

    public static TeamDao getTeamDao() {
        return new TeamDao(
                new Random().nextInt(),
                RandomString.make(10),
                RandomString.make(10),
                new Timestamp(new Random().nextLong())
        );
    }

    public static TeammateDao getTeammateDao() {
        return new TeammateDao(
                new Random().nextInt(),
                RandomString.make(10),
                RandomString.make(10),
                RandomString.make(10),
                new Timestamp(new Random().nextLong())
        );
    }

    public static Teammate getTeammate() {
        return new Teammate(
                new Random().nextInt(),
                RandomString.make(10),
                RandomString.make(10),
                RandomString.make(10),
                new Timestamp(new Random().nextLong())
        );
    }
}
