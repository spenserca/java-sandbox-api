package com.spenserca.sandbox.models.dto;

import com.spenserca.sandbox.RandomGenerator;
import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TeamDtoTest {
    private TeamDto underTest = new TeamDto();

    @Test
    public void toModel_Called_ReturnsCorrectlyMappedTeamObject() {
        TeamDao expected = RandomGenerator.getTeamDao();

        Team actual = underTest.toModel(expected);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actual.getId()).isEqualTo(expected.getId());
        softly.assertThat(actual.getName()).isEqualTo(expected.getName());
        softly.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        softly.assertThat(actual.getModifiedDate()).isEqualTo(expected.getModifiedDate());
        softly.assertAll();
    }
}