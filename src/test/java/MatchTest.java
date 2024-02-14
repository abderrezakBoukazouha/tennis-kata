import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.entities.Stats;
import org.winside.kata.tennis.useCase.*;
import org.winside.kata.tennis.useCase.Point;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchTest {

    @RepeatedTest(10)
    public void simulateMatch() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");

        Game game = new Game(federer, kygrios);
        TieBreak tieBreak = new TieBreak(game);
        Stats stats = new Stats(new ArrayList<String>());

        Set set = new Set(game, tieBreak, stats);
        Match match = new Match(set);

        // WHEN
        match.play();

        // THEN
        assertTrue(federer.getSetWon() + kygrios.getSetWon() <= 3, "Match in 3 sets");

    }
}
