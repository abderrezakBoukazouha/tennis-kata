package org.winside.kata.tennis.use_case;

import lombok.Getter;
import org.winside.kata.tennis.entities.GamePoint;
import org.winside.kata.tennis.entities.Player;

import java.util.Arrays;
import java.util.Random;

import static org.winside.kata.tennis.utilis.Printer.printGameWinner;
import static org.winside.kata.tennis.utilis.Printer.printPointScore;

@Getter
public class Game {

    private final Random random = new Random(); // ne pas redéfinir "new Random()" à chaque appel : en fonction de l'implémentation de la JVM, 2 appels successifs pourraient produire la même valeur
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Player[] players;
    /* Question : pourquoi une Array (Player[]) plutôt qu'une List<Player> ?
     * Les listes ont beaucoup plus de fonctionnalités (ça te permettrait de simplifier tes "Arrays.stream" un peu partout)
     * Plus en utilisant l'interface List tu te laisses la possibilités plus tard de facilement changer l'implémentation que tu utiliserais
     *
     * A ça, je rajouterai : ton array/list ne contient que 2 éléments que tu stockes en plus individuellement dans firstPlayer et secondPlayer
     * ton array n'a donc pas vraiment d'intérêt (et si tu tiens ensuite à vraiment utiliser des streams, tu peux ensuite les retrouver
     * en faisant un simple "Stream.of(firstPlayer, secondPlayer)"
     */
    private int pointPlayed = 0;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.players = new Player[]{firstPlayer, secondPlayer};
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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

        Point.play(pointWinner, pointLoser);
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
        return random.nextInt(players.length);
    }

    private int randomLooserIndex(int randomWinnerIndex) {
        return 0 == randomWinnerIndex ? 1 : 0;
    }

    private boolean hasGameEnded() { // nom inverse de ce que tu vérifies
        return Arrays.stream(players).noneMatch(player -> player.getGamePoint().equals(GamePoint.WON));
    }
}
