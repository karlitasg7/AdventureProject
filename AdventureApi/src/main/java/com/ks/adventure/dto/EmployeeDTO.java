package com.ks.adventure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    Integer employeeId;
    String firstName;
    String middleName;
    String lastName;
    String jobTitle;
    Integer departmentId;
    String department;
    LocalDate startDate;
    LocalDate birthDay;
    String phoneNumber;
    Integer emailAddressId;
    String emailAddress;
    String addressLine1;
    String addressLine2;
    String city;
    Integer provinceId;
    String province;
    String postalCode;
    String country;
    Integer shiftID;

}
