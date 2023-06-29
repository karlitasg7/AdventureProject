package com.ks.adventure.repository;

import com.ks.adventure.business.repository.ShiftRepository;
import com.ks.adventure.dto.ShiftDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;
import java.util.stream.Collectors;

public class SQLShiftRepository implements ShiftRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SQLShiftRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ShiftDTO> findAll() {

        StoredProcedureQuery spFindAll = entityManager.createStoredProcedureQuery("HumanResources.findAllShifts");

        List<Object[]> results = spFindAll.getResultList();

        return results
                .stream()
                .map(SQLShiftRepository::getResumeDetail)
                .collect(Collectors.toList());

    }

    private static ShiftDTO getResumeDetail(Object[] tuple) {
        return new ShiftDTO(
                ((Number) tuple[0]).intValue(),
                tuple[1].toString()
        );
    }

}
