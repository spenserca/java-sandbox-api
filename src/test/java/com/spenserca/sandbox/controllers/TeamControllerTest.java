package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.Team;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class TeamControllerTest {
    private TeamController underTest;
    private TeamRepository mockTeamRepository;

    @Before
    public void setUp() {
        mockTeamRepository = mock(TeamRepository.class);
        when(mockTeamRepository.getTeams()).thenReturn(Collections.emptyList());

        underTest = new TeamController(mockTeamRepository);
    }

    @Test
    public void get_Called_CallsTeamRepositoryGetTeams() {
        underTest.get();

        verify(mockTeamRepository).getTeams();
    }

    @Test
    public void get_Called_ReturnsValueFromTeamRepositoryGetTeams() {
        int id = new Random().nextInt();
        String name = RandomStringUtils.random(64);
        String description = RandomStringUtils.random(64);
        Timestamp createdDate = new Timestamp(new Random().nextLong());
        Team team = new Team(
                id,
                name,
                description,
                createdDate
        );
        List<Team> expected = Collections.singletonList(team);
        when(mockTeamRepository.getTeams()).thenReturn(expected);

        ResponseEntity<List<Team>> responseEntity = underTest.get();

        List<Team> actual = responseEntity.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void get_TeamRepositoryGetTeamsThrowsNullPointerException_ThrowsRuntimeException() {
        NullPointerException expectedCause = new NullPointerException();
        String expectedMessage = "Error getting teams";
        when(mockTeamRepository.getTeams()).thenThrow(expectedCause);

        assertThatThrownBy(() -> underTest.get()).hasMessage(expectedMessage);
        assertThatThrownBy(() -> underTest.get()).hasCause(expectedCause);
    }
}