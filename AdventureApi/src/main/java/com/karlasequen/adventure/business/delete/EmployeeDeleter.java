package com.karlasequen.adventure.business.delete;

import com.karlasequen.adventure.business.repository.EmployeeRepository;
import com.karlasequen.adventure.exceptions.EntityNotPersistedException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDeleter {

    EmployeeRepository employeeRepository;

    public EmployeeDeleter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void delete(Integer id) throws EntityNotPersistedException {

        employeeRepository
                .existById(id)
                .orElseThrow(() -> new EntityNotPersistedException("Employee not found"));

        employeeRepository.delete(id);

    }

}
