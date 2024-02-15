package org.winside.kata.tennis.use_case;

import lombok.Getter;
import org.winside.kata.tennis.entities.Stats;

import static org.winside.kata.tennis.utilis.Printer.printGameScore;
import static org.winside.kata.tennis.utilis.Printer.printSetScore;

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
        printSetScore(game.getFirstPlayer(), game.getSecondPlayer());
        resetGameScore();

        while (hasSetEnded() && gamePlayed <= 12) {
            gamePlayed++;
            game.play();
            printGameScore(game.getFirstPlayer(), game.getSecondPlayer());
        }

        playTieBreakIfNecessary();
        increaseSetScore();
        printSetScore(game.getFirstPlayer(), game.getSecondPlayer());
    }

    public void playTieBreakIfNecessary() {
        if (tiebreakCondition) {
            resetTieBreakScore();
            tieBreak.play();
            tiebreakCondition = false;
        }
    }

    private void increaseSetScore() {
        int differenceGameWon = Math.abs(game.getFirstPlayer().getGameWon() - game.getSecondPlayer().getGameWon());

        if (game.getFirstPlayer().getGameWon() == 7) {
            game.getFirstPlayer().increaseSetWon();
        }

        if (game.getSecondPlayer().getGameWon() == 7) {
            game.getSecondPlayer().increaseSetWon();
        }

        if (game.getFirstPlayer().getGameWon() == 6 && differenceGameWon >= 2) {
            game.getFirstPlayer().increaseSetWon();
        }

        if (game.getSecondPlayer().getGameWon() == 6 && differenceGameWon >= 2) {
            game.getSecondPlayer().increaseSetWon();
        }
        saveScore();
    }

    public void saveScore() {
        int gameWonFirstPlayer = game.getFirstPlayer().getGameWon();
        int gameWonSecondPlayer = game.getSecondPlayer().getGameWon();
        String setScore = gameWonFirstPlayer + " - " + gameWonSecondPlayer;

        stats.globalScore().add(setScore); // cf. remarque sur Stats
    }

    private boolean hasSetEnded() {
        // Tie-break condition
        if (game.getFirstPlayer().getGameWon() == 6 && game.getSecondPlayer().getGameWon() == 6) {
            this.tiebreakCondition = true;
            return false;
        }

        int differenceGameWon = Math.abs(game.getFirstPlayer().getGameWon() - game.getSecondPlayer().getGameWon());
        return (game.getFirstPlayer().getGameWon() < 6 && game.getSecondPlayer().getGameWon() < 6) || differenceGameWon < 2;

    }

    private void resetGameScore() {
        // A voir s'il ne serait pas préférable d'avoir juste une méthode "game.resetGame()" qui elle-même réinitialisera les game gagné de chaque joueur
        game.getFirstPlayer().resetGameWon();
        game.getSecondPlayer().resetGameWon();
        gamePlayed = 0;
    }

    public void resetTieBreakScore() {
        // idem
        game.getFirstPlayer().resetTieBreakPointWon();
        game.getSecondPlayer().resetTieBreakPointWon();
    }
}

