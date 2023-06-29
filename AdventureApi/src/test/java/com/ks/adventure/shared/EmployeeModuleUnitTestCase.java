package com.ks.adventure.shared;

import com.ks.adventure.business.create.EmployeeCreator;
import com.ks.adventure.business.delete.EmployeeDeleter;
import com.ks.adventure.business.find.EmployeeFinder;
import com.ks.adventure.business.repository.EmployeeRepository;
import com.ks.adventure.business.update.EmployeeUpdater;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class EmployeeModuleUnitTestCase {

    protected EmployeeRepository repository;

    protected EmployeeDeleter employeeDeleter;
    protected EmployeeUpdater employeeUpdater;
    protected EmployeeCreator employeeCreator;
    protected EmployeeFinder employeeFinder;

    @BeforeEach
    protected void setUp() {
        repository = mock(EmployeeRepository.class);

        employeeDeleter = new EmployeeDeleter(repository);
        employeeUpdater = new EmployeeUpdater(repository);
        employeeCreator = new EmployeeCreator(repository);
        employeeFinder = new EmployeeFinder(repository);

    }

}
