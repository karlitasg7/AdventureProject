package com.ks.adventure.controller;

import com.ks.adventure.business.find.ProvinceFinder;
import com.ks.adventure.dto.ProvinceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Provinces")
public class ProvinceController {

    @Autowired
    ProvinceFinder provinceFinder;

    @GetMapping
    public ResponseEntity<List<ProvinceDTO>> findAll() {
        return ResponseEntity.ok(provinceFinder.find());
    }

}
