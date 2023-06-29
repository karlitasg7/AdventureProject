package com.ks.adventure.repository;

import com.ks.adventure.business.repository.SalesRepository;
import com.ks.adventure.dto.SalesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SQLSalesRepository implements SalesRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SQLSalesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<SalesDTO> findByEmployeeAndDate(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        StoredProcedureQuery spFindAll = entityManager.createStoredProcedureQuery("HumanResources.findAllSalesByEmployee");

        spFindAll.registerStoredProcedureParameter("BusinessEntityID", Integer.class, ParameterMode.IN);
        spFindAll.setParameter("BusinessEntityID", employeeId);

        spFindAll.registerStoredProcedureParameter("StartDate", LocalDate.class, ParameterMode.IN);
        spFindAll.setParameter("StartDate", startDate);

        spFindAll.registerStoredProcedureParameter("EndDate", LocalDate.class, ParameterMode.IN);
        spFindAll.setParameter("EndDate", endDate);


        List<Object[]> results = spFindAll.getResultList();

        return results
                .stream()
                .map(SQLSalesRepository::getResumeDetail)
                .collect(Collectors.toList());

    }

    private static SalesDTO getResumeDetail(Object[] tuple) {
        return new SalesDTO(
                ((Date) tuple[0]).toLocalDate(),
                tuple[1].toString(),
                tuple[2].toString(),
                tuple[3].toString(),
                ((Number) tuple[4]).doubleValue(),
                ((Number) tuple[5]).doubleValue(),
                ((Number) tuple[6]).doubleValue()
        );
    }

}
