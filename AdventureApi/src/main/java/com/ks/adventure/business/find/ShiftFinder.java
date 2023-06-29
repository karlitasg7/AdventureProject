package com.ks.adventure.business.find;

import com.ks.adventure.business.repository.ShiftRepository;
import com.ks.adventure.dto.ShiftDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftFinder {

    ShiftRepository shiftRepository;

    public ShiftFinder(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<ShiftDTO> find() {
        return shiftRepository.findAll();
    }

}
