package com.ks.adventure.business.find;

import com.ks.adventure.business.repository.DepartmentRepository;
import com.ks.adventure.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentFinder {

    DepartmentRepository departmentRepository;

    public DepartmentFinder(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> find() {
        return departmentRepository.findAll();
    }

}
