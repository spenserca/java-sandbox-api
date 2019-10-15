package com.spenserca.sandbox.models.dto;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Random;

public class TeamDtoTest {
    private TeamDto underTest = new TeamDto();

    @Test
    public void toModel_Called_ReturnsCorrectlyMappedTeamObject() {
        TeamDao teamDao = new TeamDao(
            new Random().nextInt(),
            RandomStringUtils.randomAlphanumeric(10),
            RandomStringUtils.randomAlphanumeric(10),
            new Timestamp(new Random().nextLong())
        );
        Team expected = new Team(
            teamDao.getId(),
            teamDao.getName(),
            teamDao.getDescription(),
            teamDao.getNodifiedDate()
        );

        Team actual = underTest.toModel(teamDao);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actual.getId()).isEqualTo(expected.getId());
        softly.assertThat(actual.getName()).isEqualTo(expected.getName());
        softly.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        softly.assertThat(actual.getModifiedDate()).isEqualTo(expected.getModifiedDate());
        softly.assertAll();
    }
}