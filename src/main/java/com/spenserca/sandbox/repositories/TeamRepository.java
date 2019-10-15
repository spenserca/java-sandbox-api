package com.spenserca.sandbox.repositories;

import com.spenserca.sandbox.models.dao.TeamDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamDao, Integer> {
}
