package org.winside.kata.tennis.entities;

import lombok.Getter;
import lombok.Setter;

import static org.winside.kata.tennis.entities.GameScore.ZERO;

@Getter
@Setter
public class Player {

    private GameScore gameScore;

    private String name;

    public Player (String name) {
        this.name = name;
        this.gameScore = ZERO;
    }
}
