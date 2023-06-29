package com.ks.adventure.find;

import com.ks.adventure.dto.EmployeeDTOMother;
import com.ks.adventure.dto.EmployeeDTO;
import com.ks.adventure.shared.EmployeeModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestEmployeeFinderShould extends EmployeeModuleUnitTestCase {

    @Test
    void returnEmployeeList() {

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeDTOList.add(EmployeeDTOMother.random());
        employeeDTOList.add(EmployeeDTOMother.random());
        employeeDTOList.add(EmployeeDTOMother.random());

        when(repository.findAll()).thenReturn(employeeDTOList);

        List<EmployeeDTO> responseList = employeeFinder.find();

        assertEquals(employeeDTOList, responseList, "Response list must be equals");

    }

}
