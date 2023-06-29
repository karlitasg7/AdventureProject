package com.ks.adventure.find;

import com.ks.adventure.dto.DepartmentDTO;
import com.ks.adventure.dto.DepartmentDTOMother;
import com.ks.adventure.shared.DepartmentModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestDepartmentFinderShould extends DepartmentModuleUnitTestCase {

    @Test
    void returnDepartmentList() {

        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        departmentDTOS.add(DepartmentDTOMother.random());
        departmentDTOS.add(DepartmentDTOMother.random());
        departmentDTOS.add(DepartmentDTOMother.random());

        when(repository.findAll()).thenReturn(departmentDTOS);

        List<DepartmentDTO> responseList = departmentFinder.find();

        assertEquals(departmentDTOS, responseList, "Response list must be equals");

    }

}
