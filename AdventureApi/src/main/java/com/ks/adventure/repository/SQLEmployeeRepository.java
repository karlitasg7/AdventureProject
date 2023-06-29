package com.ks.adventure.repository;

import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.business.repository.EmployeeRepository;
import com.ks.adventure.dto.EmployeeDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SQLEmployeeRepository implements EmployeeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SQLEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void registerAndAddParameters(StoredProcedureQuery storedProcedureQuery, EmployeeRequest employeeRequest) {
        storedProcedureQuery.registerStoredProcedureParameter("FirstName", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("FirstName", employeeRequest.getFirstName());

        storedProcedureQuery.registerStoredProcedureParameter("MiddleName", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("MiddleName", employeeRequest.getMiddleName());

        storedProcedureQuery.registerStoredProcedureParameter("LastName", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("LastName", employeeRequest.getLastName());

        storedProcedureQuery.registerStoredProcedureParameter("JobTitle", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("JobTitle", employeeRequest.getJobTitle());

        storedProcedureQuery.registerStoredProcedureParameter("BirthDay", Date.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("BirthDay", employeeRequest.getBirthday());

        storedProcedureQuery.registerStoredProcedureParameter("PhoneNumber", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("PhoneNumber", employeeRequest.getPhoneNumber());

        storedProcedureQuery.registerStoredProcedureParameter("EmailAddress", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("EmailAddress", employeeRequest.getEmailAddress());

        storedProcedureQuery.registerStoredProcedureParameter("AddressLine1", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("AddressLine1", employeeRequest.getAddressLine1());

        storedProcedureQuery.registerStoredProcedureParameter("AddressLine2", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("AddressLine2", employeeRequest.getAddressLine2());

        storedProcedureQuery.registerStoredProcedureParameter("City", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("City", employeeRequest.getCity());

        storedProcedureQuery.registerStoredProcedureParameter("PostalCode", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("PostalCode", employeeRequest.getPostalCode());

        storedProcedureQuery.registerStoredProcedureParameter("Province", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("Province", employeeRequest.getProvinceId());

        storedProcedureQuery.registerStoredProcedureParameter("DepartmentId", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("DepartmentId", employeeRequest.getDepartmentId());

        storedProcedureQuery.registerStoredProcedureParameter("ShiftID", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("ShiftID", employeeRequest.getShiftId());

        storedProcedureQuery.registerStoredProcedureParameter("StartDate", Date.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("StartDate", employeeRequest.getStartDate());
    }

    @Override
    @Transactional
    public Integer create(EmployeeRequest employeeRequest) {

        StoredProcedureQuery spInsertEmployee = entityManager.createStoredProcedureQuery("HumanResources.insertEmployee");

        registerAndAddParameters(spInsertEmployee, employeeRequest);

        spInsertEmployee.execute();

        return Integer.parseInt(spInsertEmployee.getSingleResult().toString());

    }

    @Override
    @Transactional
    public void update(Integer id, EmployeeRequest employeeRequest) {

        StoredProcedureQuery spUpdateEmployee = entityManager.createStoredProcedureQuery("HumanResources.updateEmployee");

        spUpdateEmployee.registerStoredProcedureParameter("BusinessEntityID", Integer.class, ParameterMode.IN);
        spUpdateEmployee.setParameter("BusinessEntityID", id);

        registerAndAddParameters(spUpdateEmployee, employeeRequest);

        spUpdateEmployee.execute();
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        StoredProcedureQuery spDeleteEmployee = entityManager.createStoredProcedureQuery("HumanResources.deleteEmployee");

        spDeleteEmployee.registerStoredProcedureParameter("BusinessEntityID", Integer.class, ParameterMode.IN);
        spDeleteEmployee.setParameter("BusinessEntityID", id);

        spDeleteEmployee.execute();

    }

    @Override
    public Optional<Integer> existById(Integer id) {

        StoredProcedureQuery spExist = entityManager.createStoredProcedureQuery("HumanResources.existById");

        spExist.registerStoredProcedureParameter("BusinessEntityID", Integer.class, ParameterMode.IN);
        spExist.setParameter("BusinessEntityID", id);

        spExist.execute();

        int result = Integer.parseInt(spExist.getSingleResult().toString());

        if (result == 0) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public List<EmployeeDTO> findAll() {

        StoredProcedureQuery spFindAll = entityManager.createStoredProcedureQuery("HumanResources.findAll");

        List<Object[]> results = spFindAll.getResultList();

        return results
                .stream()
                .map(SQLEmployeeRepository::getResumeDetail)
                .collect(Collectors.toList());

    }

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
