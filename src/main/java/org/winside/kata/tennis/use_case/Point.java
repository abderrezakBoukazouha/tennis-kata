package org.winside.kata.tennis.use_case;

import org.winside.kata.tennis.entities.Player;

import static org.winside.kata.tennis.entities.GamePoint.*;

public class Point {
    private Point() {
    }

    // Les méthodes sont utilisés directement et uniquement sur les attributs passés en paramètre de méthode.
    // Etant donné qu'il n'y a pas de "logique" à proprement parler dans le "Point" lui-même, il aurait pu s'agir de méthode statiques
    public static void play(Player pointWinner, Player pointLooser) {
        switch (pointWinner.getGamePoint()) {
            case ZERO -> pointWinner.setGamePoint(FIFTEEN);
            case FIFTEEN -> pointWinner.setGamePoint(THIRTY);
            case THIRTY -> pointWinner.setGamePoint(FORTY);
            case FORTY -> deuceSituation(pointWinner, pointLooser);
//            case ADVANTAGE -> advantageSituation(pointWinner, pointLooser);
            case ADVANTAGE -> pointWinner.setGamePoint(WON);
            default -> throw new RuntimeException("Unknown situation"); // pour un kata OK, pour une "vrai appli", on préfèrera créer une exception interne à l'appli, pour potentiellement mieux la gérer ailleurs
        }
    }

    // méthode superflue : un joueur ayant l'avantage et marquant un point est forcément gagnant
 /*   private void advantageSituation(Player pointWinner, Player pointLooser) {
        if (pointWinner.getGamePoint() == ADVANTAGE && pointLooser.getGamePoint() == FORTY) {
            pointWinner.setGamePoint(FORTY);
            pointLooser.setGamePoint(FORTY);
        }
        if (pointLooser.getGamePoint() == FORTY) {
            pointWinner.setGamePoint(WON);
        }
    }*/

    private static void deuceSituation(Player pointWinner, Player pointLooser) {

        if (pointLooser.getGamePoint() == FORTY) {
            pointWinner.setGamePoint(ADVANTAGE);

        } else if (pointLooser.getGamePoint() == ADVANTAGE) {
            pointWinner.setGamePoint(FORTY);
            pointLooser.setGamePoint(FORTY);

        } else pointWinner.setGamePoint(WON);
    }
}
