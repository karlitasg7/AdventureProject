package com.ks.adventure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    Integer employeeId;
    String name;
    String jobTitle;
    Integer departmentId;
    String department;
    LocalDate startDate;
    LocalDate birtDay;
    String phoneNumber;
    String emailAddress;
    String address;

}
