package com.karlasequen.adventure.business.repository;

import com.karlasequen.adventure.business.EmployeeRequest;
import com.karlasequen.adventure.dto.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Integer create(EmployeeRequest employeeRequest);

    void addJobHistory(Integer employeeId, Integer departmentId, Integer shiftId, LocalDate startDate);

    void update(Integer id, EmployeeRequest employeeRequest);

    void delete(Integer id);

    Optional<Integer> existById(Integer id);

    List<EmployeeDTO> findAll();

}
