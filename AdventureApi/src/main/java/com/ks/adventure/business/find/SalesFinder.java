package com.ks.adventure.business.find;

import com.ks.adventure.business.repository.SalesRepository;
import com.ks.adventure.dto.SalesDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesFinder {

    SalesRepository salesRepository;

    public SalesFinder(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<SalesDTO> find(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        return salesRepository.findByEmployeeAndDate(employeeId, startDate, endDate);
    }

}
