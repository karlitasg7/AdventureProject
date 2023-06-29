package com.ks.adventure.business.repository;

import com.ks.adventure.dto.ShiftDTO;

import java.util.List;

public interface ShiftRepository {

    List<ShiftDTO> findAll();

}
