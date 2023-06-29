package com.ks.adventure.repository;

import com.ks.adventure.business.repository.ProvinceRepository;
import com.ks.adventure.dto.ProvinceDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;
import java.util.stream.Collectors;

public class SQLProvinceRepository implements ProvinceRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SQLProvinceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProvinceDTO> findAll() {

        StoredProcedureQuery spFindAll = entityManager.createStoredProcedureQuery("HumanResources.findAllProvinces");

        List<Object[]> results = spFindAll.getResultList();

        return results
                .stream()
                .map(SQLProvinceRepository::getResumeDetail)
                .collect(Collectors.toList());

    }

    private static ProvinceDTO getResumeDetail(Object[] tuple) {
        return new ProvinceDTO(
                ((Number) tuple[0]).intValue(),
                tuple[1].toString()
        );
    }

}
