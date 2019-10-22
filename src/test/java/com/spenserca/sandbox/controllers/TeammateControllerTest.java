package com.spenserca.sandbox.controllers;

import com.spenserca.sandbox.RandomGenerator;
import com.spenserca.sandbox.models.dao.TeammateDao;
import com.spenserca.sandbox.models.domain.Teammate;
import com.spenserca.sandbox.models.dto.TeammateDto;
import com.spenserca.sandbox.repositories.TeammateRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class TeammateControllerTest {
    private TeammateController underTest;
    private TeammateRepository mockTeammateRepository = mock(TeammateRepository.class);
    private TeammateDto mockTeammateDto = mock(TeammateDto.class);

    @Before
    public void setUp() {
        when(mockTeammateRepository.findAll()).thenReturn(Collections.singletonList(RandomGenerator.getTeammateDao()));
        when(mockTeammateDto.toModel(any())).thenReturn(RandomGenerator.getTeammate());

        underTest = new TeammateController(
            mockTeammateRepository,
            mockTeammateDto
        );
    }


    @Test
    public void get_Called_CallsTeammateRepositoryFindAll() {
        underTest.get();

        verify(mockTeammateRepository).findAll();
    }

    @Test
    public void get_CalledAndTeamsAreFound_CallsTeammateDtoToModelWithCorrectParams() {
        TeammateDao expected = RandomGenerator.getTeammateDao();
        when(mockTeammateRepository.findAll()).thenReturn(Collections.singletonList(expected));

        underTest.get();

        verify(mockTeammateDto).toModel(expected);
    }

    @Test
    public void get_CalledAndTeamsAreFound_ReturnsValueFromTeammateDtoToModel() {
        TeammateDao TeammateDao = RandomGenerator.getTeammateDao();
        Teammate expectedTeammate = RandomGenerator.getTeammate();
        List<Teammate> expected = Collections.singletonList(expectedTeammate);
        when(mockTeammateRepository.findAll()).thenReturn(Collections.singletonList(TeammateDao));
        when(mockTeammateDto.toModel(TeammateDao)).thenReturn(expectedTeammate);

        ResponseEntity<List<Teammate>> responseEntity = underTest.get();

        List<Teammate> actual = responseEntity.getBody();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void get_CalledAndNoTeamsAreFound_ReturnsEmptyList() {
        when(mockTeammateRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Teammate>> responseEntity = underTest.get();

        List<Teammate> actual = responseEntity.getBody();
        assertThat(actual).isEmpty();
    }

    @Test
    public void get_TeammateRepositoryFindAllThrowsNullPointerException_ThrowsRuntimeException() {
        NullPointerException expectedCause = new NullPointerException();
        String expectedMessage = "Error getting teammates";
        when(mockTeammateRepository.findAll()).thenThrow(expectedCause);

        assertThatThrownBy(() -> underTest.get()).hasMessage(expectedMessage);
        assertThatThrownBy(() -> underTest.get()).hasCause(expectedCause);
    }
}