package com.ks.adventure.controller;

import com.ks.adventure.business.find.SalesFinder;
import com.ks.adventure.dto.SalesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Employees/{id}/Sales")
public class SalesController {

    @Autowired
    SalesFinder salesFinder;

    @GetMapping
    public ResponseEntity<List<SalesDTO>> find(
            @PathVariable("id") Integer id,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {
        return ResponseEntity.ok(salesFinder.find(id, startDate, endDate));
    }

}
