package com.ks.adventure.business.find;

import com.ks.adventure.business.repository.ProvinceRepository;
import com.ks.adventure.dto.ProvinceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceFinder {

    ProvinceRepository provinceRepository;

    public ProvinceFinder(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public List<ProvinceDTO> find() {
        return provinceRepository.findAll();
    }

}
