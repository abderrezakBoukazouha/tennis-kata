package org.winside.kata.tennis.useCase;

import lombok.Getter;
import org.winside.kata.tennis.entities.Stats;

import static org.winside.kata.tennis.useCase.Printer.printGameScore;
import static org.winside.kata.tennis.useCase.Printer.printSetScore;

@Getter
public class Set {
    private final TieBreak tieBreak;
    private final Game game;
    private final Stats stats;
    private boolean tiebreakCondition = false;
    private int gamePlayed = 0;

    public Set(Game game, TieBreak tieBreak, Stats stats) {
        this.tieBreak = tieBreak;
        this.game = game;
        this.stats = stats;
    }

    public void play() {
        printSetScore(getGame().getFirstPlayer(), getGame().getSecondPlayer());
        resetGameScore();

        while (hasSetEnded() && gamePlayed <= 12) {
            gamePlayed++;
            game.play();
            printGameScore(getGame().getFirstPlayer(), getGame().getSecondPlayer());
        }

        playTieBreakIfNecessary();
        increaseSetScore();
        printSetScore(getGame().getFirstPlayer(), getGame().getSecondPlayer());
    }

    public void playTieBreakIfNecessary() {
        if (tiebreakCondition) {
            resetTieBreakScore();
            tieBreak.play();
            tiebreakCondition = false;
        }
    }

    private void increaseSetScore() {
        int differenceGameWon = Math.abs(getGame().getFirstPlayer().getGameWon() - getGame().getSecondPlayer().getGameWon());

        if (getGame().getFirstPlayer().getGameWon() == 7) {
            getGame().getFirstPlayer().increaseSetWon();
        }

        if (getGame().getSecondPlayer().getGameWon() == 7) {
            getGame().getSecondPlayer().increaseSetWon();
        }

        if (getGame().getFirstPlayer().getGameWon() == 6 && differenceGameWon >= 2) {
            getGame().getFirstPlayer().increaseSetWon();
        }

        if (getGame().getSecondPlayer().getGameWon() == 6 && differenceGameWon >= 2) {
            getGame().getSecondPlayer().increaseSetWon();
        }
        saveScore();
    }

    public void saveScore() {
        int gameWonFirstPlayer = getGame().getFirstPlayer().getGameWon();
        int gameWonSecondPlayer = getGame().getSecondPlayer().getGameWon();
        String setScore = gameWonFirstPlayer + " - " + gameWonSecondPlayer;

        stats.globalScore().add(setScore);
    }

    private boolean hasSetEnded() {
        // Tie-break condition
        if (getGame().getFirstPlayer().getGameWon() == 6 && getGame().getSecondPlayer().getGameWon() == 6) {
            this.tiebreakCondition = true;
            return false;
        }

        int differenceGameWon = Math.abs(getGame().getFirstPlayer().getGameWon() - getGame().getSecondPlayer().getGameWon());
        return (getGame().getFirstPlayer().getGameWon() < 6 && getGame().getSecondPlayer().getGameWon() < 6) || differenceGameWon < 2;

    }

    private void resetGameScore() {
        getGame().getFirstPlayer().setGameWon(0);
        getGame().getSecondPlayer().setGameWon(0);
        gamePlayed = 0;
    }

    public void resetTieBreakScore() {
        getGame().getFirstPlayer().setTieBreakPointWon(0);
        getGame().getSecondPlayer().setTieBreakPointWon(0);
    }
}

