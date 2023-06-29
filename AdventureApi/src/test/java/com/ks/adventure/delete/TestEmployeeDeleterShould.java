package com.ks.adventure.delete;

import com.ks.adventure.exceptions.EntityNotFoundException;
import com.ks.adventure.shared.EmployeeModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

final class TestProjectDeleterShould extends EmployeeModuleUnitTestCase {

    @Test
    void deleteAndExistingEmployee() throws EntityNotFoundException {

        int employeeId = 1;

        when(repository.existById(employeeId)).thenReturn(Optional.of(employeeId));

        employeeDeleter.delete(employeeId);

        verify(repository, times(1)).existById(employeeId);

    }

    @Test
    void throwExceptionWhenNotExistEmployee() {
        when(repository.existById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> employeeDeleter.delete(1),
                "must return a exception");

    }

}
