package com.ks.adventure.create;

import com.ks.adventure.EmployeeRequestMother;
import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.shared.EmployeeModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestEmployeeCreatorShould extends EmployeeModuleUnitTestCase {

    @Test
    void createNewEmployee() {

        EmployeeRequest employeeRequest = EmployeeRequestMother.random();

        when(repository.create(employeeRequest)).thenReturn(1);

        int response = employeeCreator.create(employeeRequest);

        assertEquals(1, response, "Id must be equals");

    }

}
