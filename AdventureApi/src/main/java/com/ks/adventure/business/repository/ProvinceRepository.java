package com.ks.adventure.business.repository;

import com.ks.adventure.dto.ProvinceDTO;

import java.util.List;

public interface ProvinceRepository {

    List<ProvinceDTO> findAll();

}
