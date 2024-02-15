import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.entities.Stats;
import org.winside.kata.tennis.use_case.Game;
import org.winside.kata.tennis.use_case.Set;
import org.winside.kata.tennis.use_case.TieBreak;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SetTest {

    @RepeatedTest(5)
    void testSetRules() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");

        Game game = new Game(federer, kygrios);
        TieBreak tieBreak = new TieBreak(game);
        Stats stats = new Stats(new ArrayList<String>());

        Set set = new Set(game, tieBreak, stats);  // mock des sous-parties, t'aurais permis de plus facilement tester le tiebreak

        // WHEN
        set.play();

        // THEN
        assertTrue(set.getGamePlayed() >= 6);
        assertEquals(1, federer.getSetWon() + kygrios.getSetWon());
    }
}
