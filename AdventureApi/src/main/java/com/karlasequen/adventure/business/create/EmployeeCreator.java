package com.karlasequen.adventure.business.create;

import com.karlasequen.adventure.business.EmployeeRequest;
import com.karlasequen.adventure.business.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreator {

    EmployeeRepository employeeRepository;

    public EmployeeCreator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Integer create(EmployeeRequest request) {
        Integer employeeId = employeeRepository.create(request);

        employeeRepository.addJobHistory(
                employeeId,
                request.getDepartmentId(),
                request.getShiftId(),
                request.getStartDate()
        );

        return employeeId;

    }

}
