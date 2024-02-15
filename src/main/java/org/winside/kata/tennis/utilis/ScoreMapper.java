package org.winside.kata.tennis.utilis;

import org.winside.kata.tennis.entities.GamePoint;

public class ScoreMapper {
    private ScoreMapper() {
        // Classe utilisaire (ie uniquement des méthodes statiques) => constructeur privé pour ne pas pouvoir l'instancier
    }

    public static String gamePointMapper(GamePoint gamePoint) {
        return switch (gamePoint) {
            case ZERO -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            case ADVANTAGE -> "AD";
//            case WON -> "WON"; // ?
            default -> throw new RuntimeException("Unknown game point"); // Pas testé
        };
    }
}
