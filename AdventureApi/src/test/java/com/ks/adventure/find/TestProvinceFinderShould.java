package com.ks.adventure.find;

import com.ks.adventure.dto.ProvinceDTO;
import com.ks.adventure.dto.ProvinceDTOMother;
import com.ks.adventure.shared.ProvinceModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestProvinceFinderShould extends ProvinceModuleUnitTestCase {

    @Test
    void returnProvinceList() {

        List<ProvinceDTO> provinceDTOS = new ArrayList<>();
        provinceDTOS.add(ProvinceDTOMother.random());
        provinceDTOS.add(ProvinceDTOMother.random());
        provinceDTOS.add(ProvinceDTOMother.random());

        when(repository.findAll()).thenReturn(provinceDTOS);

        List<ProvinceDTO> responseList = provinceFinder.find();

        assertEquals(provinceDTOS, responseList, "Response list must be equals");

    }

}
