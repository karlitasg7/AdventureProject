package com.karlasequen.adventure.business.find;

import com.karlasequen.adventure.business.repository.EmployeeRepository;
import com.karlasequen.adventure.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeFinder {

    EmployeeRepository employeeRepository;

    public EmployeeFinder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> find() {
        return employeeRepository.findAll();
    }

}
