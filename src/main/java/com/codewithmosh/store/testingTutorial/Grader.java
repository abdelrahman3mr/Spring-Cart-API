package com.codewithmosh.store.testingTutorial;

public class Grader {

    public char determineLetterGrader(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be a positive integer");
        }
        else if (number < 60) {
            return 'F';
        }else if(number < 70) {
            return 'D';
        }else if(number < 80) {
            return 'C';
        }else if(number < 90) {
            return 'B';
        }else {
            return 'A';
        }
    }
}
