package org.winside.kata.tennis.utilis;

import org.winside.kata.tennis.entities.Player;

import java.util.Arrays;

import static org.winside.kata.tennis.entities.GamePoint.WON;

public class Printer {
    private Printer() {
        // Classe utilisaire (ie uniquement des méthodes statiques) => constructeur privé pour ne pas pouvoir l'instancier
    }

    public static void printGameWinner(Player player) {
        // Possible d'utiliser la fonctionnalité "text bloc" introduite en Java 15
        var message = """
                
                /#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#
                Game won by : %s
                /#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#
                """.formatted(player.getName());
        System.out.println(message);
//        System.out.println();
//        System.out.println("/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#");
//        System.out.println("Game won by : " + player.getName());
//        System.out.println("/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#/#");
//        System.out.println();
    }

    public static void printPointScore(Player[] players, Player pointWinner, int pointPlayed) {
        if (Arrays.stream(players).anyMatch(player -> player.getGamePoint().equals(WON))) {
            return;
        }
        var message = """
                Point %d won by : %s
                actual score : %s - %s"""
                .formatted(
                        pointPlayed,
                        pointWinner.getName(),
                        players[0].getGamePoint(), // override toString sur l'Enum permet de se passer de gamePointMapper
                        players[1].getGamePoint()
                );
        System.out.println(message);
//        System.out.println("Point " + pointPlayed + " won by : " + pointWinner.getName());
//        System.out.println("actual score : " + gamePointMapper(players[0].getGamePoint())
//                + " - " + gamePointMapper(players[1].getGamePoint()));
    }

    public static void printGameScore(Player firstPlayer, Player secondPlayer) {
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println("current Set score : " + firstPlayer.getName() + " " + firstPlayer.getGameWon() +
                " - " + secondPlayer.getGameWon() + " " + secondPlayer.getName());
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
    }

    public static void printSetScore(Player firstPlayer, Player secondPlayer) {
        System.out.println("********************************************");
        System.out.println("Match score : " + firstPlayer.getName() + " " + firstPlayer.getSetWon() +
                " - " + secondPlayer.getSetWon() + " " + secondPlayer.getName());
        System.out.println("********************************************");
    }

    public static void printCurrentTieBreakScore(Player firstPlayer, Player secondPlayer) {
        System.out.println("Current Tie Bkreak score : " + firstPlayer.getName() + " " + firstPlayer.getTieBreakPointWon() +
                " - " + secondPlayer.getTieBreakPointWon() + " " + secondPlayer.getName());
    }

    public static void printTieBreakWinner(Player firstPlayer) {
        System.out.println("Tie Bkreak winner : " + firstPlayer.getName());
    }

    public static void printStartTieBreak() {
        System.out.println("########### TIE BREAK ###########");
    }

    public static void printStartMatch() {
        System.out.println("Match started");
    }

    public static void printEndMatch() {
        System.out.println("Final score");
    }
}
