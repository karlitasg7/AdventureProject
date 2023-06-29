package com.ks.adventure.shared;

import com.ks.adventure.business.find.SalesFinder;
import com.ks.adventure.business.repository.SalesRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class SalesModuleUnitTestCase {

    protected SalesRepository repository;

    protected SalesFinder salesFinder;

    @BeforeEach
    protected void setUp() {
        repository = mock(SalesRepository.class);

        salesFinder = new SalesFinder(repository);

    }

}
