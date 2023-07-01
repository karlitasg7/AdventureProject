package com.ks.adventure.business;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    String firstName;
    String middleName;
    String lastName;
    LocalDate birthday;

    String phoneNumber;
    String emailAddress;

    String addressLine1;
    String addressLine2;
    String city;
    String postalCode;
    Integer provinceId;

    String jobTitle;
    Integer departmentId;
    Integer shiftId;
    LocalDate startDate;

}
