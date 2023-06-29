package com.ks.adventure.repository;

import com.ks.adventure.business.repository.DepartmentRepository;
import com.ks.adventure.dto.DepartmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;
import java.util.stream.Collectors;

public class SQLDepartmentRepository implements DepartmentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SQLDepartmentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DepartmentDTO> findAll() {

        StoredProcedureQuery spFindAll = entityManager.createStoredProcedureQuery("HumanResources.findAllDepartments");

        List<Object[]> results = spFindAll.getResultList();

        return results
                .stream()
                .map(SQLDepartmentRepository::getResumeDetail)
                .collect(Collectors.toList());

    }

    private static DepartmentDTO getResumeDetail(Object[] tuple) {
        return new DepartmentDTO(
                ((Number) tuple[0]).intValue(),
                tuple[1].toString()
        );
    }

}
