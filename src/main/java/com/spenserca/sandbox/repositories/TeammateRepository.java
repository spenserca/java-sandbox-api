package com.spenserca.sandbox.repositories;

import com.spenserca.sandbox.models.dao.TeammateDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeammateRepository extends JpaRepository<TeammateDao, Integer> {
}
