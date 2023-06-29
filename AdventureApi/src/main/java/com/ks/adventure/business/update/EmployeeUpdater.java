package com.ks.adventure.business.update;

import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.business.repository.EmployeeRepository;
import com.ks.adventure.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUpdater {

    EmployeeRepository employeeRepository;

    public EmployeeUpdater(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void update(Integer id, EmployeeRequest request) throws EntityNotFoundException {

        employeeRepository
                .existById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employeeRepository.update(id, request);

    }

}
