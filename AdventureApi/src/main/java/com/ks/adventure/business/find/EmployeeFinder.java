package com.ks.adventure.business.find;

import com.ks.adventure.business.repository.EmployeeRepository;
import com.ks.adventure.dto.EmployeeDTO;
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
