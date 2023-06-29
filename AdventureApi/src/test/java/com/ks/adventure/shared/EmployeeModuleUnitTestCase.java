package com.ks.adventure.shared;

import com.ks.adventure.business.delete.EmployeeDeleter;
import com.ks.adventure.business.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class EmployeeModuleUnitTestCase {

    protected EmployeeRepository repository;

    protected EmployeeDeleter employeeDeleter;

    @BeforeEach
    protected void setUp() {
        repository = mock(EmployeeRepository.class);

        employeeDeleter = new EmployeeDeleter(repository);

    }

}
