package com.ks.adventure.dto;

import com.ks.adventure.shared.WordMother;

import java.time.LocalDate;

public class SalesDTOMother {

    public static SalesDTO random() {
        return new SalesDTO(
                LocalDate.now(),
                WordMother.random(),
                WordMother.random(),
                WordMother.random(),
                10.00,
                5.00,
                15.00
        );
    }

}
