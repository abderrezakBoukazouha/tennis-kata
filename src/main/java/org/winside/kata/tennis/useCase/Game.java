package org.winside.kata.tennis.useCase;

import org.winside.kata.tennis.entities.GamePoint;
import org.winside.kata.tennis.entities.Player;

import java.util.Arrays;
import java.util.Random;

import static org.winside.kata.tennis.utilis.Printer.printGameWinner;
import static org.winside.kata.tennis.utilis.Printer.printPointScore;

public class Game {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Player[] players;
    private final Point point;
    private int pointPlayed = 0;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.point = new Point();
        this.players = new Player[]{firstPlayer, secondPlayer};
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public int getPointPlayed() {
        return pointPlayed;
    }

    public void play() {
        resetPointScore();
        while (hasGameEnded()) {
            pointPlayed++;
            Player pointWinner = getPointWinner();
            printPointScore(players, pointWinner, pointPlayed);
        }
        printGameWinner(getGameWinner());
        updateGameScore();
    }

    private Player getPointWinner() {
        int winnerIndex = randomWinnerIndex();
        Player pointWinner = players[winnerIndex];
        Player pointLoser = players[randomLooserIndex(winnerIndex)];

        point.play(pointWinner, pointLoser);
        return pointWinner;
    }


    private Player getGameWinner() {
        return Arrays.stream(players).filter(player -> player.getGamePoint().equals(GamePoint.WON)).findAny().get();
    }

    private void updateGameScore() {
        getGameWinner().increaseGameWon();
    }

    private void resetPointScore() {

        Arrays.stream(players).forEach(player -> player.setGamePoint(GamePoint.ZERO));
        pointPlayed = 0;
    }

    private int randomWinnerIndex() {
        return new Random().nextInt(players.length);
    }

    private int randomLooserIndex(int randomWinnerIndex) {
        return 0 == randomWinnerIndex ? 1 : 0;
    }

    private boolean hasGameEnded() {
        return Arrays.stream(players).noneMatch(player -> player.getGamePoint().equals(GamePoint.WON));
    }
}
