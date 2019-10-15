package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        when(mockTeamRepository.getAll()).thenReturn(Optional.of(Collections.emptyList()));

        underTest = new TeamController(mockTeamRepository);
    }

    @Test
    public void get_Called_CallsTeamRepositoryGetAll() {
        underTest.get();

        verify(mockTeamRepository).getAll();
    }

    @Test
    public void get_CalledAndTeamsAreFound_ReturnsListOfMappedTeams() {
        TeamDao teamDao = new TeamDao(
            new Random().nextInt(),
            RandomStringUtils.random(10),
            RandomStringUtils.random(10),
            new Timestamp(new Random().nextLong())
        );
        when(mockTeamRepository.getAll()).thenReturn(Optional.of(Collections.singletonList(teamDao)));
        List<Team> expected = Collections.singletonList(new Team(
            teamDao.getId(),
            teamDao.getName(),
            teamDao.getDescription(),
            teamDao.getCreatedDate()
        ));

        ResponseEntity<List<Team>> responseEntity = underTest.get();

        List<Team> actual = responseEntity.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void get_TeamRepositoryGetAllThrowsNullPointerException_ThrowsRuntimeException() {
        NullPointerException expectedCause = new NullPointerException();
        String expectedMessage = "Error getting teams";
        when(mockTeamRepository.getAll()).thenThrow(expectedCause);

        assertThatThrownBy(() -> underTest.get()).hasMessage(expectedMessage);
        assertThatThrownBy(() -> underTest.get()).hasCause(expectedCause);
    }
}