package org.winside.kata.tennis;

import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.useCase.DecideGame;

public class main {

    public static void main(String[] args) {
        Player firstPlayer = new Player("NOVAK");
        Player secondPlayer = new Player("RAFA");
        DecideGame decideGame = new DecideGame(new Player[]{firstPlayer, secondPlayer});

        decideGame.Play();
    }
}
