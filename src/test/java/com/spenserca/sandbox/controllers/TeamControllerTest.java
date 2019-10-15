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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class TeamControllerTest {
    private TeamController underTest;
    private TeamRepository mockTeamRepository = mock(TeamRepository.class);
    private TeamDto mockTeamDto = mock(TeamDto.class);

    @Before
    public void setUp() {
        when(mockTeamRepository.getAll()).thenReturn(Optional.of(Collections.singletonList(RandomGenerator.getTeamDao())));
        when(mockTeamDto.toModel(any())).thenReturn(RandomGenerator.getTeam());

        underTest = new TeamController(
            mockTeamRepository,
            mockTeamDto
        );
    }

    @Test
    public void get_Called_CallsTeamRepositoryGetAll() {
        underTest.get();

        verify(mockTeamRepository).getAll();
    }

    @Test
    public void get_CalledAndTeamsAreFound_CallsTeamDtoToModelWithCorrectParams() {
        TeamDao expected = RandomGenerator.getTeamDao();
        when(mockTeamRepository.getAll()).thenReturn(Optional.of(Collections.singletonList(expected)));

        underTest.get();

        verify(mockTeamDto).toModel(expected);
    }

    @Test
    public void get_CalledAndTeamsAreFound_ReturnsValueFromTeamDtoToModel() {
        TeamDao teamDao = RandomGenerator.getTeamDao();
        Team expectedTeam = RandomGenerator.getTeam();
        List<Team> expected = Collections.singletonList(expectedTeam);
        when(mockTeamRepository.getAll()).thenReturn(Optional.of(Collections.singletonList(teamDao)));
        when(mockTeamDto.toModel(teamDao)).thenReturn(expectedTeam);

        ResponseEntity<List<Team>> responseEntity = underTest.get();

        List<Team> actual = responseEntity.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void get_CalledAndNoTeamsAreFound_ReturnsEmptyList() {
        when(mockTeamRepository.getAll()).thenReturn(Optional.empty());

        ResponseEntity<List<Team>> responseEntity = underTest.get();

        List<Team> actual = responseEntity.getBody();
        assertThat(actual).isEmpty();
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