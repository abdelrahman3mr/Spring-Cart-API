package com.codewithmosh.store.testingTutorial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraderTest {

    @Test
    void fiftyNine_ShouldReturn_F(){
        Grader grader = new Grader();

        assertEquals('F',grader.determineLetterGrader(59));
    }

    @Test
    void negativeOne_ShouldReturn_IllegalArgument(){
        Grader grader = new Grader();
        assertThrows(IllegalArgumentException.class, () -> grader.determineLetterGrader(-1));
    }

}