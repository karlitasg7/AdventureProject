package com.ks.adventure.shared;

import com.ks.adventure.business.find.DepartmentFinder;
import com.ks.adventure.business.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class DepartmentModuleUnitTestCase {

    protected DepartmentRepository repository;

    protected DepartmentFinder departmentFinder;

    @BeforeEach
    protected void setUp() {
        repository = mock(DepartmentRepository.class);

        departmentFinder = new DepartmentFinder(repository);

    }

}
