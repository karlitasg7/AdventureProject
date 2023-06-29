package com.ks.adventure.update;

import com.ks.adventure.EmployeeRequestMother;
import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.exceptions.EntityNotFoundException;
import com.ks.adventure.shared.EmployeeModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class TestEmployeeUpdaterShould extends EmployeeModuleUnitTestCase {

    @Test
    void updateAndExistingEmployee() throws EntityNotFoundException {

        int employeeId = 1;
        EmployeeRequest employeeRequest = EmployeeRequestMother.random();

        when(repository.existById(employeeId)).thenReturn(Optional.of(employeeId));

        employeeUpdater.update(employeeId, employeeRequest);

        verify(repository, times(1)).update(employeeId, employeeRequest);

    }

    @Test
    void throwExceptionWhenNotExistEmployee() {
        int employeeId = 1;
        EmployeeRequest employeeRequest = EmployeeRequestMother.random();

        when(repository.existById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> employeeUpdater.update(employeeId, employeeRequest),
                "must return a exception");

    }

}
