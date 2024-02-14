package org.winside.kata.tennis.entities;

import java.util.List;

public record Stats(List<String> globalScore) {

    public void printFinalMatchScore() {
        for (String setScore : globalScore) {
            System.out.print(setScore + "   ");

        }
    }
}
