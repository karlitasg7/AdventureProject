package com.ks.adventure.find;

import com.ks.adventure.dto.SalesDTO;
import com.ks.adventure.dto.SalesDTOMother;
import com.ks.adventure.shared.SalesModuleUnitTestCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestSalesFinderShould extends SalesModuleUnitTestCase {

    @Test
    void returnSalesList() {

        int employeeId = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        List<SalesDTO> salesDTOList = new ArrayList<>();
        salesDTOList.add(SalesDTOMother.random());
        salesDTOList.add(SalesDTOMother.random());
        salesDTOList.add(SalesDTOMother.random());

        when(repository.findByEmployeeAndDate(employeeId, startDate, endDate)).thenReturn(salesDTOList);

        List<SalesDTO> responseList = salesFinder.find(employeeId, startDate, endDate);

        assertEquals(salesDTOList, responseList, "Response list must be equals");

    }

}
