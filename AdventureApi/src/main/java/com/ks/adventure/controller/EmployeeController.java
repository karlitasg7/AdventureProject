package com.ks.adventure.controller;

import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.business.create.EmployeeCreator;
import com.ks.adventure.business.delete.EmployeeDeleter;
import com.ks.adventure.business.find.EmployeeFinder;
import com.ks.adventure.business.update.EmployeeUpdater;
import com.ks.adventure.dto.EmployeeDTO;
import com.ks.adventure.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {

    @Autowired
    EmployeeCreator employeeCreator;

    @Autowired
    EmployeeUpdater employeeUpdater;

    @Autowired
    EmployeeDeleter employeeDeleter;

    @Autowired
    EmployeeFinder employeeFinder;

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody EmployeeRequest request) {
        Integer employeeId = employeeCreator.create(request);
        return new ResponseEntity<>(employeeId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody EmployeeRequest request) throws EntityNotFoundException {

        employeeUpdater.update(id, request);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) throws EntityNotFoundException {

        employeeDeleter.delete(id);

        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok(employeeFinder.find());
    }

}
