package com.ks.adventure.business.repository;

import com.ks.adventure.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentRepository {

    List<DepartmentDTO> findAll();

}
