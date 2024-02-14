package org.winside.kata.tennis.useCase;

import org.winside.kata.tennis.entities.Player;

import static org.winside.kata.tennis.entities.GamePoint.*;

public class Point {
    public void play(Player pointWinner, Player pointLooser) {
        switch (pointWinner.getGamePoint()) {
            case ZERO -> pointWinner.setGamePoint(FIFTEEN);
            case FIFTEEN -> pointWinner.setGamePoint(THIRTY);
            case THIRTY -> pointWinner.setGamePoint(FORTY);
            case FORTY -> deuceSituation(pointWinner, pointLooser);
            case ADVANTAGE -> advantageSituation(pointWinner, pointLooser);
            default -> throw new RuntimeException("Unknown situation");
        }
    }

    private void advantageSituation(Player pointWinner, Player pointLooser) {
        if (pointWinner.getGamePoint() == ADVANTAGE && pointLooser.getGamePoint() == FORTY) {
            pointWinner.setGamePoint(FORTY);
            pointLooser.setGamePoint(FORTY);
        }
        if (pointLooser.getGamePoint() == FORTY) {
            pointWinner.setGamePoint(WON);
        }
    }

    private void deuceSituation(Player pointWinner, Player pointLooser) {

        if (pointLooser.getGamePoint() == FORTY) {
            pointWinner.setGamePoint(ADVANTAGE);

        } else if (pointLooser.getGamePoint() == ADVANTAGE) {
            pointWinner.setGamePoint(FORTY);
            pointLooser.setGamePoint(FORTY);

        } else pointWinner.setGamePoint(WON);
    }
}
