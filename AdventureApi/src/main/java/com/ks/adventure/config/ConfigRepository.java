package com.ks.adventure.config;

import com.ks.adventure.business.repository.EmployeeRepository;
import com.ks.adventure.repository.SQLEmployeeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRepository {

    @Bean
    EmployeeRepository employeeRepository(EntityManager entityManager) {
        return new SQLEmployeeRepository(entityManager);
    }

}
