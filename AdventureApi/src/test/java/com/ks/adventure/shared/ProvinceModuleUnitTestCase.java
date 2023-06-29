package com.ks.adventure.shared;

import com.ks.adventure.business.find.ProvinceFinder;
import com.ks.adventure.business.repository.ProvinceRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class ProvinceModuleUnitTestCase {

    protected ProvinceRepository repository;

    protected ProvinceFinder provinceFinder;

    @BeforeEach
    protected void setUp() {
        repository = mock(ProvinceRepository.class);

        provinceFinder = new ProvinceFinder(repository);

    }

}
