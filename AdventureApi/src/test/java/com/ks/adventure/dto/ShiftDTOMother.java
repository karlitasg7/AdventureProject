package com.ks.adventure.dto;

import com.ks.adventure.shared.IntegerMother;
import com.ks.adventure.shared.WordMother;

public class ShiftDTOMother {

    public static ShiftDTO random() {
        return new ShiftDTO(
                IntegerMother.random(),
                WordMother.random()
        );
    }

}
