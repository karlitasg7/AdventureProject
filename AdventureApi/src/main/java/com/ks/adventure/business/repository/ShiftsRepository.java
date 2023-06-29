package com.ks.adventure.business.repository;

import com.ks.adventure.dto.ShiftDTO;

import java.util.List;

public interface ShiftsRepository {

    List<ShiftDTO> findAll();

}
