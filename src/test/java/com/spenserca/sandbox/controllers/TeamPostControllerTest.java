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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeamPostControllerTest {
    private TeamPostController underTest;
    private TeamRepository mockTeamRepository = mock(TeamRepository.class);
    private TeamDto mockTeamDto = mock(TeamDto.class);

    @Before
    public void setUp() {
        when(mockTeamRepository.save(any(TeamDao.class))).thenReturn(RandomGenerator.getTeamDao());
        when(mockTeamDto.toModel(any(TeamDao.class))).thenReturn(RandomGenerator.getTeam());

        underTest = new TeamPostController(
            mockTeamRepository,
            mockTeamDto
        );
    }

    @Test
    public void post_Called_CallsTeamDtoToDaoWithThePassedInTeam() {
        Team expected = RandomGenerator.getTeam();

        underTest.post(expected);

        verify(mockTeamDto).toDao(expected);
    }

    @Test
    public void post_Called_CallsTeamRepositorySaveWithTheConvertedTeamDao() {
        TeamDao expected = RandomGenerator.getTeamDao();
        when(mockTeamDto.toDao(any(Team.class))).thenReturn(expected);

        underTest.post(RandomGenerator.getTeam());

        verify(mockTeamRepository).save(expected);
    }

    @Test
    public void post_Called_ReturnsOkResponse() {
        HttpStatus expected = HttpStatus.OK;

        ResponseEntity<Team> response = underTest.post(RandomGenerator.getTeam());

        HttpStatus actual = response.getStatusCode();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void post_Called_ReturnsPassedInTeam() {
        Team expected = RandomGenerator.getTeam();

        ResponseEntity<Team> response = underTest.post(expected);

        Team actual = response.getBody();
        assertThat(actual).isEqualTo(expected);
    }
}