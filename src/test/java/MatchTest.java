import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.entities.Stats;
import org.winside.kata.tennis.use_case.Game;
import org.winside.kata.tennis.use_case.Match;
import org.winside.kata.tennis.use_case.Set;
import org.winside.kata.tennis.use_case.TieBreak;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchTest {

    @RepeatedTest(10)
    void simulateMatch() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");

        Game game = new Game(federer, kygrios);
        TieBreak tieBreak = new TieBreak(game);
        Stats stats = new Stats(new ArrayList<String>());

        Set set = new Set(game, tieBreak, stats);
        Match match = new Match(set); // mock des sous-parties

        // WHEN
        match.play();

        // THEN
        assertTrue(federer.getSetWon() + kygrios.getSetWon() <= 3, "Match in 3 sets");

    }
}
