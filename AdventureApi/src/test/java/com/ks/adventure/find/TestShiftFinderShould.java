package com.ks.adventure.find;

import com.ks.adventure.dto.ShiftDTO;
import com.ks.adventure.dto.ShiftDTOMother;
import com.ks.adventure.shared.ShiftsModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestShiftFinderShould extends ShiftsModuleUnitTestCase {

    @Test
    void returnShiftList() {

        List<ShiftDTO> shiftDTOS = new ArrayList<>();
        shiftDTOS.add(ShiftDTOMother.random());
        shiftDTOS.add(ShiftDTOMother.random());
        shiftDTOS.add(ShiftDTOMother.random());

        when(repository.findAll()).thenReturn(shiftDTOS);

        List<ShiftDTO> responseList = shiftFinder.find();

        assertEquals(shiftDTOS, responseList, "Response list must be equals");

    }

}
