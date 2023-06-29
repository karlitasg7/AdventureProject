package com.ks.adventure.controller;

import com.ks.adventure.business.find.DepartmentFinder;
import com.ks.adventure.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Departments")
public class DepartmentController {

    @Autowired
    DepartmentFinder departmentFinder;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        return ResponseEntity.ok(departmentFinder.find());
    }

}
