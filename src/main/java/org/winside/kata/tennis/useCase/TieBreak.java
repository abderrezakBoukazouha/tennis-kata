package org.winside.kata.tennis.useCase;

import java.util.Random;

import static org.winside.kata.tennis.useCase.Printer.*;

public record TieBreak(Game game) {

    public void play() {
        printStartTieBreak();
        printCurrentTieBreakScore(game().getFirstPlayer(), game().getSecondPlayer());

        while (tieBreakStillGoing()) {
            playRandomTieBreakPoint();
            printCurrentTieBreakScore(game().getFirstPlayer(), game().getSecondPlayer());
        }

        tieBreakWinner();
    }

    private void playRandomTieBreakPoint() {
        int tieBreakWinnerIndex = new Random().nextInt(game().getPlayers().length);
        game().getPlayers()[tieBreakWinnerIndex].increaseTieBreakPointWon();
    }

    private boolean tieBreakStillGoing() {
        int differencePointWon = Math.abs(game().getFirstPlayer().getTieBreakPointWon() - game().getSecondPlayer().getTieBreakPointWon());
        return (game().getFirstPlayer().getTieBreakPointWon() < 7 && game().getSecondPlayer().getTieBreakPointWon() < 7) || differencePointWon < 2;
    }

    private void tieBreakWinner() {
        if (game().getFirstPlayer().getTieBreakPointWon() > game().getSecondPlayer().getTieBreakPointWon()) {
            printTieBreakWinner(game().getFirstPlayer());
            game().getFirstPlayer().increaseGameWon();
        } else {
            printTieBreakWinner(game().getSecondPlayer());
            game().getSecondPlayer().increaseGameWon();
        }
    }
}
