package com.ks.adventure.dto;

import com.ks.adventure.shared.IntegerMother;
import com.ks.adventure.shared.WordMother;

public class ProvinceDTOMother {

    public static ProvinceDTO random() {
        return new ProvinceDTO(
                IntegerMother.random(),
                WordMother.random()
        );
    }

}
