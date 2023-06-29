package com.ks.adventure.config;

import com.ks.adventure.business.repository.*;
import com.ks.adventure.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRepository {

    @Bean
    EmployeeRepository employeeRepository(EntityManager entityManager) {
        return new SQLEmployeeRepository(entityManager);
    }

    @Bean
    DepartmentRepository departmentRepository(EntityManager entityManager) {
        return new SQLDepartmentRepository(entityManager);
    }

    @Bean
    ProvinceRepository provinceRepository(EntityManager entityManager) {
        return new SQLProvinceRepository(entityManager);
    }

    @Bean
    ShiftRepository shiftRepository(EntityManager entityManager) {
        return new SQLShiftRepository(entityManager);
    }

    @Bean
    SalesRepository salesRepository(EntityManager entityManager) {
        return new SQLSalesRepository(entityManager);
    }

}
