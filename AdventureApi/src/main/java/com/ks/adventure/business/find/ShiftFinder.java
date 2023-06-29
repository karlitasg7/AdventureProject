package com.ks.adventure.business.find;

import com.ks.adventure.business.repository.ShiftsRepository;
import com.ks.adventure.dto.ShiftDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftFinder {

    ShiftsRepository shiftsRepository;

    public ShiftFinder(ShiftsRepository shiftsRepository) {
        this.shiftsRepository = shiftsRepository;
    }

    public List<ShiftDTO> find() {
        return shiftsRepository.findAll();
    }

}
