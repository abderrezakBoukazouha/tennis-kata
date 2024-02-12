package org.winside.kata.tennis.useCase;

import org.winside.kata.tennis.entities.GameScore;
import org.winside.kata.tennis.entities.Player;

import java.util.Arrays;
import java.util.Random;

public class DecideGame {

    Player[] players;

   public  DecideGame(Player [] players) {
        this.players = players;
    }

    public void Play() {
        int niemePoint = 1;

        System.out.println("Game Started");

        while (hasGameEnded()) {
            Player pointWinner = randomPointWinner();
            Player pointLoser = Arrays.stream(players).filter(player -> !player.getName().equals(pointWinner.getName())).findFirst().get();

            GameScore.update(pointWinner, pointLoser);
            printScore(pointWinner, niemePoint);
        }
        System.out.println("Game won by : " +  Arrays.stream(players).filter(player -> player.getGameScore().equals(GameScore.WON)).findAny().get().getName());
    }

    private Player randomPointWinner() {
        int randomIndex = new Random().nextInt(players.length);
        return players[randomIndex];
    }

    private void printScore(Player pointWinner, int niemePoint) {
        System.out.println("Point " + niemePoint + " won by : " + pointWinner.getName());
        System.out.println("actual score : " + players[0].getGameScore() + " - " + players[1].getGameScore());
    }

    private boolean hasGameEnded() {
        return Arrays.stream(players).noneMatch(player -> player.getGameScore().equals(GameScore.WON));
    }
}
