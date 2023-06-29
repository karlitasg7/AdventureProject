package com.ks.adventure;

import com.ks.adventure.business.EmployeeRequest;
import com.ks.adventure.shared.IntegerMother;
import com.ks.adventure.shared.WordMother;

import java.time.LocalDate;

public class EmployeeRequestMother {

    public static EmployeeRequest random() {

        EmployeeRequest employeeRequest = new EmployeeRequest();

        employeeRequest.setFirstName(WordMother.random());
        employeeRequest.setMiddleName(WordMother.random());
        employeeRequest.setLastName(WordMother.random());
        employeeRequest.setBirthday(LocalDate.now());
        employeeRequest.setPhoneNumber(WordMother.random());
        employeeRequest.setEmailAddress(WordMother.random());
        employeeRequest.setAddressLine1(WordMother.random());
        employeeRequest.setAddressLine2(WordMother.random());
        employeeRequest.setCity(WordMother.random());
        employeeRequest.setEmailAddress(WordMother.random());
        employeeRequest.setPostalCode(WordMother.random());
        employeeRequest.setProvinceId(IntegerMother.random());
        employeeRequest.setJobTitle(WordMother.random());
        employeeRequest.setDepartmentId(IntegerMother.random());
        employeeRequest.setShiftId(IntegerMother.random());
        employeeRequest.setStartDate(LocalDate.now());

        return employeeRequest;
    }

}
