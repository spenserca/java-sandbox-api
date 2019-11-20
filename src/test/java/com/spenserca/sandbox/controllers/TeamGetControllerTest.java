package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.RandomGenerator;
import com.spenserca.sandbox.models.dao.TeamDao;
import com.spenserca.sandbox.models.domain.Team;
import com.spenserca.sandbox.models.dto.TeamDto;
import com.spenserca.sandbox.repositories.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeamGetControllerTest {
    private TeamGetController underTest;
    private TeamRepository mockTeamRepository = mock(TeamRepository.class);
    private TeamDto mockTeamDto = mock(TeamDto.class);

    @Before
    public void setUp() {
        when(mockTeamRepository.findById(anyInt())).thenReturn(Optional.of(RandomGenerator.getTeamDao()));
        when(mockTeamDto.toModel(any())).thenReturn(RandomGenerator.getTeam());

        underTest = new TeamGetController(
            mockTeamRepository,
            mockTeamDto
        );
    }

    @Test
    public void get_Called_CallsTeamRepositoryFindByIdWithTheExpectedId() {
        int expected = new Random().nextInt();

        underTest.get(expected);

        verify(mockTeamRepository).findById(expected);
    }

    @Test
    public void get_CalledAndTeamIsFound_CallsTeamDtoToModelWithTheExpectedTeamDao() {
        TeamDao expected = RandomGenerator.getTeamDao();
        when(mockTeamRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        underTest.get(new Random().nextInt());

        verify(mockTeamDto).toModel(expected);
    }

    @Test
    public void get_CalledAndTeamIsFound_ReturnsValueFromTeamDtoToModel() {
        TeamDao teamDao = RandomGenerator.getTeamDao();
        Team expected = RandomGenerator.getTeam();
        when(mockTeamRepository.findById(anyInt())).thenReturn(Optional.of(teamDao));
        when(mockTeamDto.toModel(teamDao)).thenReturn(expected);

        ResponseEntity<Team> responseEntity = underTest.get(new Random().nextInt());

        Team actual = responseEntity.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void get_CalledAndNoTeamIsFound_ReturnsNotFoundStatus() {
        when(mockTeamRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Team> responseEntity = underTest.get(new Random().nextInt());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void get_TeamRepositoryFindByIdThrowsNullPointerException_ThrowsRuntimeException() {
        NullPointerException expectedCause = new NullPointerException();
        String expectedMessage = "Error getting teams";
        when(mockTeamRepository.findById(anyInt())).thenThrow(expectedCause);

        assertThatThrownBy(() -> underTest.get(anyInt())).hasMessage(expectedMessage);
        assertThatThrownBy(() -> underTest.get(anyInt())).hasCause(expectedCause);
    }
}
