package org.winside.kata.tennis.use_case;

import org.winside.kata.tennis.utilis.Printer;

public class Match {

    private final Set set;

    public Match(Set set) {
        this.set = set;
    }

    public void play() {

        Printer.printStartMatch();
        // Condition un peu longue dans ton while, il aurait été préférable de définir une méthode type "hasMatchEnded" comme dans tes autres classes
        while (set.getGame().getFirstPlayer().getSetWon() < 2 && set.getGame().getSecondPlayer().getSetWon() < 2) {
            set.play();
        }
        Printer.printEndMatch();
        set.getStats().printFinalMatchScore(); // cf remarques dans Stats
    }
}
