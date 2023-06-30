package com.ks.adventure.dto;

import com.ks.adventure.shared.IntegerMother;
import com.ks.adventure.shared.WordMother;

import java.time.LocalDate;

public class EmployeeDTOMother {

    public static EmployeeDTO random() {
        return new EmployeeDTO(
                IntegerMother.random(),
                WordMother.random(),
                WordMother.random(),
                WordMother.random(),
                WordMother.random(),
                IntegerMother.random(),
                WordMother.random(),
                LocalDate.now(),
                LocalDate.now(),
                WordMother.random(),
                IntegerMother.random(),
                WordMother.random(),
                WordMother.random(),
                WordMother.random(),
                WordMother.random(),
                IntegerMother.random(),
                WordMother.random(),
                WordMother.random(),
                WordMother.random(),
                IntegerMother.random()
        );
    }

}
