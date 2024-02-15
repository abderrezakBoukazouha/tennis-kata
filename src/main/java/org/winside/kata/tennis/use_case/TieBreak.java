package org.winside.kata.tennis.use_case;

import java.util.Random;

import static org.winside.kata.tennis.utilis.Printer.*;

/* un "TieBreak" est un type de "Game" particulier : tu aurais pu potentiellement partir d'un  "TieBreak extends Game"
 * puis juste redéfinir les méthodes nécessaires (en les redéfinissant protected) :
 * - getPointWinner -> playRandomTieBreakPoint
 * - getGameWinner -> tieBreakWinner
 * - hasGameEnded -> tieBreakStillGoing
 */
public record TieBreak(Game game) {

    private static final Random RANDOM = new Random();

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
        int tieBreakWinnerIndex = RANDOM.nextInt(game().getPlayers().length);
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
