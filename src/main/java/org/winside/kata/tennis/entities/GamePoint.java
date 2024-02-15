package org.winside.kata.tennis.entities;

public enum GamePoint {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD"),
    WON("WON");
    // DRAW ?
    private final String label;

    GamePoint(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
