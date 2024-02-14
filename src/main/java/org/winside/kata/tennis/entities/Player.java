package org.winside.kata.tennis.entities;

import lombok.Getter;
import lombok.Setter;

import static org.winside.kata.tennis.entities.GamePoint.ZERO;

@Getter
@Setter
public class Player {

    private final String name;
    private GamePoint gamePoint;
    private int gameWon;

    private int setWon;

    private int tieBreakPointWon;

    public Player(String name) {
        this.name = name;
        this.gamePoint = ZERO;
    }

    public void increaseGameWon() {
        gameWon++;
    }

    public void increaseTieBreakPointWon() {
        tieBreakPointWon++;
    }

    public void increaseSetWon() {
        setWon++;
    }
}
