package org.winside.kata.tennis.utilis;

import org.winside.kata.tennis.entities.GamePoint;

public class ScoreMapper {

    public static String gamePointMapper(GamePoint gamePoint) {
        switch (gamePoint) {
            case ZERO -> {
                return "0";
            }
            case FIFTEEN -> {
                return "15";
            }
            case THIRTY -> {
                return "30";
            }
            case FORTY -> {
                return "40";
            }
            case ADVANTAGE -> {
                return "AD";
            }
            default -> throw new RuntimeException("Unknown game point");
        }
    }
}
