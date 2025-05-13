package com.codewithmosh.store.testingTutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    @Test
    void twoPlusTwoEqualsFour(){
        SimpleCalculator calculator = new SimpleCalculator();
        assertTrue(4 == calculator.add(2, 2));
    }

}