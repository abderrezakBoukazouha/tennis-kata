import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.entities.Stats;
import org.winside.kata.tennis.useCase.Game;
import org.winside.kata.tennis.useCase.Point;
import org.winside.kata.tennis.useCase.Set;
import org.winside.kata.tennis.useCase.TieBreak;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {

    @RepeatedTest(5)
    public void testSetRules() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");
        Point point = new Point();

        Game game = new Game(federer, kygrios);
        TieBreak tieBreak = new TieBreak(game);
        Stats stats = new Stats(new ArrayList<String>());

        Set set = new Set(game, tieBreak, stats);

        // WHEN
        set.play();

        // THEN
        assertTrue(set.getGamePlayed() >= 6);
        assertEquals(1, federer.getSetWon() + kygrios.getSetWon());
    }
}
