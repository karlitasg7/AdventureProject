package com.karlasequen.adventure.business.update;

import com.karlasequen.adventure.business.EmployeeRequest;
import com.karlasequen.adventure.business.repository.EmployeeRepository;
import com.karlasequen.adventure.exceptions.EntityNotPersistedException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUpdater {

    EmployeeRepository employeeRepository;

    public EmployeeUpdater(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void update(Integer id, EmployeeRequest request) throws EntityNotPersistedException {

        employeeRepository
                .existById(id)
                .orElseThrow(() -> new EntityNotPersistedException("Employee not found"));

        employeeRepository.update(id, request);

        if (request.getChangeJobPosition()) {
            employeeRepository.addJobHistory(
                    id,
                    request.getDepartmentId(),
                    request.getShiftId(),
                    request.getStartDate()
            );
        }

    }

}
