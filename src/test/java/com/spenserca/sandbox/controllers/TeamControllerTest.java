package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.RandomGenerator;
import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class TeamControllerTest {
    private TeamController underTest;
    private TeamRepository mockTeamRepository = mock(TeamRepository.class);
    private TeamDto mockTeamDto = mock(TeamDto.class);

    @Before
    public void setUp() {
        when(mockTeamRepository.findAll()).thenReturn(Collections.singletonList(RandomGenerator.getTeamDao()));
        when(mockTeamDto.toModel(any())).thenReturn(RandomGenerator.getTeam());

        underTest = new TeamController(
            mockTeamRepository,
            mockTeamDto
        );
    }

    @Test
    public void get_Called_CallsTeamRepositoryFindAll() {
        underTest.get();

        verify(mockTeamRepository).findAll();
    }

    @Test
    public void get_CalledAndTeamsAreFound_CallsTeamDtoToModelWithCorrectParams() {
        TeamDao expected = RandomGenerator.getTeamDao();
        when(mockTeamRepository.findAll()).thenReturn(Collections.singletonList(expected));

        underTest.get();

        verify(mockTeamDto).toModel(expected);
    }

    @Test
    public void get_CalledAndTeamsAreFound_ReturnsValueFromTeamDtoToModel() {
        TeamDao teamDao = RandomGenerator.getTeamDao();
        Team expectedTeam = RandomGenerator.getTeam();
        List<Team> expected = Collections.singletonList(expectedTeam);
        when(mockTeamRepository.findAll()).thenReturn(Collections.singletonList(teamDao));
        when(mockTeamDto.toModel(teamDao)).thenReturn(expectedTeam);

        ResponseEntity<List<Team>> responseEntity = underTest.get();

        List<Team> actual = responseEntity.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void get_CalledAndNoTeamsAreFound_ReturnsEmptyList() {
        when(mockTeamRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Team>> responseEntity = underTest.get();

        List<Team> actual = responseEntity.getBody();
        assertThat(actual).isEmpty();
    }

    @Test
    public void get_TeamRepositoryFindAllThrowsNullPointerException_ThrowsRuntimeException() {
        NullPointerException expectedCause = new NullPointerException();
        String expectedMessage = "Error getting teams";
        when(mockTeamRepository.findAll()).thenThrow(expectedCause);

        assertThatThrownBy(() -> underTest.get()).hasMessage(expectedMessage);
        assertThatThrownBy(() -> underTest.get()).hasCause(expectedCause);
    }
}