package com.ks.adventure.controller;

import com.ks.adventure.business.find.ShiftFinder;
import com.ks.adventure.dto.ShiftDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Shifts")
public class ShiftController {

    @Autowired
    ShiftFinder shiftFinder;

    @GetMapping
    public ResponseEntity<List<ShiftDTO>> findAll() {
        return ResponseEntity.ok(shiftFinder.find());
    }

}
