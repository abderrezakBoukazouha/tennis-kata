package org.winside.kata.tennis.useCase;

public class Match {

    private final Set set;

    public Match(Set set) {
        this.set = set;
    }

    public void play() {

        Printer.printStartMatch();
        while (set.getGame().getFirstPlayer().getSetWon() < 2 && set.getGame().getSecondPlayer().getSetWon() < 2) {
            set.play();
        }
        Printer.printEndMatch();
        set.getStats().printFinalMatchScore();
    }
}
