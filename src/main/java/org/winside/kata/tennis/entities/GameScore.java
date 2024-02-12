package org.winside.kata.tennis.entities;

public enum GameScore {
    ZERO,
    FIFTEEN,
    THIRTY,
    FORTY,
    ADVANTAGE,
    WON;

    public static void update (Player pointWinner, Player pointLooser) {
        switch (pointWinner.getGameScore()) {
            case ZERO:
                pointWinner.setGameScore(FIFTEEN);
                break;
            case FIFTEEN: pointWinner.setGameScore(THIRTY);
                break;
            case THIRTY: pointWinner.setGameScore(FORTY);
                break;
            case FORTY:
            case ADVANTAGE:
                deuceSituation (pointWinner, pointLooser.getGameScore());
                break;
        }
    }

    // TODO : handle ADVANTAGE - FORTY situation
    private static void deuceSituation(Player firstPlayer, GameScore scorePlayerTwo) {
        switch (scorePlayerTwo) {
            case FORTY:
                firstPlayer.setGameScore(ADVANTAGE);
                break;
            default:
                firstPlayer.setGameScore(WON);
                break;
        }
    }
}
