package com.ks.adventure.business.create;

import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.business.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreator {

    EmployeeRepository employeeRepository;

    public EmployeeCreator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Integer create(EmployeeRequest request) {
        return employeeRepository.create(request);
    }

}
