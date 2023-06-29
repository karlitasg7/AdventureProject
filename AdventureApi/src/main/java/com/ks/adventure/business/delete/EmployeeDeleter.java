package com.ks.adventure.business.delete;

import com.ks.adventure.business.repository.EmployeeRepository;
import com.ks.adventure.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDeleter {

    EmployeeRepository employeeRepository;

    public EmployeeDeleter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void delete(Integer id) throws EntityNotFoundException {

        employeeRepository
                .existById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employeeRepository.delete(id);

    }

}
