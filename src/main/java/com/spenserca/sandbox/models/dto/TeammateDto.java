package com.spenserca.sandbox.models.dto;

import com.spenserca.sandbox.models.dao.TeammateDao;
import com.spenserca.sandbox.models.domain.Teammate;
import org.springframework.stereotype.Component;

@Component
public class TeammateDto {
    public Teammate toModel(TeammateDao teammateDao) {
        return new Teammate(
            teammateDao.getId(),
            teammateDao.getFirstName(),
            teammateDao.getLastName(),
            teammateDao.getNickName(),
            teammateDao.getModifiedDate()
        );
    }
}
