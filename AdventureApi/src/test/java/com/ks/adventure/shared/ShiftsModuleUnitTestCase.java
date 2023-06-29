package com.ks.adventure.shared;

import com.ks.adventure.business.find.ShiftFinder;
import com.ks.adventure.business.repository.ShiftRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class ShiftsModuleUnitTestCase {

    protected ShiftRepository repository;

    protected ShiftFinder shiftFinder;

    @BeforeEach
    protected void setUp() {
        repository = mock(ShiftRepository.class);

        shiftFinder = new ShiftFinder(repository);

    }

}
