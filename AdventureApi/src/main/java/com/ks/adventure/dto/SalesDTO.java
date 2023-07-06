package com.ks.adventure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SalesDTO {

    LocalDate orderDate;
    String statusName;
    String accountNumber;
    String address;
    String city;
    String province;
    String postalCode;
    String country;
    Double subtotal;
    Double taxAmount;
    Double total;

}
