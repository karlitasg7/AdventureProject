package com.ks.adventure.business.repository;

import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Integer create(EmployeeRequest employeeRequest);

    void update(Integer id, EmployeeRequest employeeRequest);

    void delete(Integer id);

    Optional<Integer> existById(Integer id);

    List<EmployeeDTO> findAll();

}
