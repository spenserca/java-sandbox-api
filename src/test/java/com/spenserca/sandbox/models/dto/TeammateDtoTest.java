package com.spenserca.sandbox.models.dto;

import com.spenserca.sandbox.RandomGenerator;
import com.spenserca.sandbox.models.dao.TeammateDao;
import com.spenserca.sandbox.models.domain.Teammate;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TeammateDtoTest {
    private TeammateDto underTest = new TeammateDto();

    @Test
    public void toModel_Called_ReturnsCorrectlyMappedTeammateObject() {
        TeammateDao expected = RandomGenerator.getTeammateDao();

        Teammate actual = underTest.toModel(expected);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actual.getTeammateId()).isEqualTo(expected.getId());
        softly.assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        softly.assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        softly.assertThat(actual.getNickName()).isEqualTo(expected.getNickName());
        softly.assertThat(actual.getModifiedDate()).isEqualTo(expected.getModifiedDate());
        softly.assertAll();
    }
}
