package com.ks.adventure.business.repository;

import com.ks.adventure.dto.SalesDTO;

import java.time.LocalDate;
import java.util.List;

public interface SalesRepository {

    List<SalesDTO> findByEmployeeAndDate(Integer employeeId, LocalDate startDate, LocalDate endDate);

}
