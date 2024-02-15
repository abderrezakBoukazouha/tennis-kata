package org.winside.kata.tennis.entities;

import lombok.Getter;
import lombok.Setter;

import static org.winside.kata.tennis.entities.GamePoint.ZERO;

@Getter
public class Player {

    private final String name;
    @Setter
    private GamePoint gamePoint;
    private int gameWon;
    private int setWon;
    private int tieBreakPointWon;

    public Player(String name) {
        this.name = name;
        this.gamePoint = ZERO;
    }

    // Tu ne faisais que des setGameWon(0) et setTieBreakPointWon(0),
    // autant enlever le "@Setter" de la classe et fournir directement des méthodes explicites de reset
    public void resetGameWon() {
        gameWon = 0;
    }

    public void increaseGameWon() {
        gameWon++;
    }

    public void resetTieBreakPointWon() {
        tieBreakPointWon = 0;
    }

    public void increaseTieBreakPointWon() {
        tieBreakPointWon++;
    }

    public void increaseSetWon() {
        setWon++;
    }
}
