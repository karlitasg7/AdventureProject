package com.karlasequen.adventure.repository;

import com.karlasequen.adventure.business.EmployeeRequest;
import com.karlasequen.adventure.business.repository.EmployeeRepository;
import com.karlasequen.adventure.dto.EmployeeDTO;
import io.hypersistence.utils.hibernate.query.ListResultTransformer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SQLEmployeeRepository implements EmployeeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SQLEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Integer create(EmployeeRequest employeeRequest) {

        entityManager.createNativeQuery("""
                """);

        return null;
    }

    @Override
    public void addJobHistory(Integer employeeId, Integer departmentId, Integer shiftId, LocalDate startDate) {

    }

    @Override
    public void update(Integer id, EmployeeRequest employeeRequest) {

    }

    @Override
    public void delete(Integer id) {

        entityManager.createNativeQuery("""
                        UPDATE HumanResources.Employee
                        SET CurrentFlag = 0
                        WHERE BusinessEntityID = ?;
                        """)
                .setParameter(1, id)
                .executeUpdate();

    }

    @Override
    public Optional<Integer> existById(Integer id) {

        int result = Integer.parseInt(entityManager.createNativeQuery("""
                        SELECT COUNT(BusinessEntityID)
                        FROM HumanResources.Employee
                        WHERE BusinessEntityID = ?
                        """)
                .setParameter(1, id)
                .getSingleResult()
                .toString()
        );

        if (result == 0) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return entityManager.createNativeQuery("""
                        SELECT  t0.BusinessEntityID
                                , CONCAT(t1.FirstName, t1.MiddleName, t1.LastName) AS name
                                , t0.JobTitle
                                , t3.DepartmentID
                                , t3.Name                                          AS department
                                , t2.StartDate
                                , t0.BirthDate
                                , t4.PhoneNumber
                                , t5.EmailAddress
                                , CONCAT(t7.AddressLine1, ' ', t7.AddressLine2, ' ', t7.City, ' ', t8.Name, ' ', t7.PostalCode, ' ',
                                t9.Name)                                  AS address
                        FROM HumanResources.Employee                  t0
                        JOIN Person.Person                            t1
                          ON t0.BusinessEntityID = t1.BusinessEntityID
                        JOIN HumanResources.EmployeeDepartmentHistory t2
                          ON t0.BusinessEntityID = t2.BusinessEntityID
                        JOIN (
                            SELECT BusinessEntityID
                                , MAX(StartDate) AS MaxStartDate
                            FROM HumanResources.EmployeeDepartmentHistory
                            GROUP BY BusinessEntityID) AS           maxdh
                          ON t2.BusinessEntityID = maxdh.BusinessEntityID
                         AND t2.StartDate = maxdh.MaxStartDate
                        JOIN HumanResources.Department                t3
                          ON t2.DepartmentID = t3.DepartmentID
                        JOIN Person.PersonPhone                       t4
                          ON t1.BusinessEntityID = t4.BusinessEntityID
                        JOIN Person.EmailAddress                      t5
                          ON t1.BusinessEntityID = t5.BusinessEntityID
                        JOIN Person.BusinessEntityAddress             t6
                          ON t6.BusinessEntityID = t0.BusinessEntityID
                         AND t6.AddressTypeID = 2 -- Home Address
                        JOIN Person.Address                           t7
                          ON t6.AddressID = t7.AddressID
                        JOIN Person.StateProvince                     t8
                          ON t7.StateProvinceID = t8.StateProvinceID
                        JOIN Person.CountryRegion                     t9
                          ON t8.CountryRegionCode = t9.CountryRegionCode
                        WHERE t0.CurrentFlag = 1
                                                                            """)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((ListResultTransformer) (tuple, aliases) -> getResumeDetail(tuple))
                .getResultList();
    }

    @NotNull
    private static EmployeeDTO getResumeDetail(Object[] tuple) {
        return new EmployeeDTO(
                ((Number) tuple[0]).intValue(),
                tuple[1].toString(),
                tuple[2].toString(),
                ((Number) tuple[3]).intValue(),
                tuple[4].toString(),
                ((Date) tuple[5]).toLocalDate(),
                ((Date) tuple[6]).toLocalDate(),
                tuple[7].toString(),
                tuple[8].toString(),
                tuple[9].toString()
        );
    }

}
