package com.ks.adventure.dto;

import com.ks.adventure.shared.IntegerMother;
import com.ks.adventure.shared.WordMother;

public class DepartmentDTOMother {

    public static DepartmentDTO random() {
        return new DepartmentDTO(
                IntegerMother.random(),
                WordMother.random()
        );
    }

}
