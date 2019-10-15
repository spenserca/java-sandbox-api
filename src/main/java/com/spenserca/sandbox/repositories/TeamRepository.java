package com.spenserca.sandbox.repositories;

import com.spenserca.sandbox.models.dao.TeamDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamDao, Integer> {
    Optional<List<TeamDao>> getAll();
}
