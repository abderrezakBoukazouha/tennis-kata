import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.use_case.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.winside.kata.tennis.entities.GamePoint.WON;

class GameTest {
    @RepeatedTest(20)
    void gameRulesTest() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");

        Game game = new Game(federer, kygrios);

        // WHEN
        game.play();

        // THEN
        assertEquals(1, federer.getGameWon() + kygrios.getGameWon());
        assertTrue(game.getPointPlayed() >= 4);
        assertTrue(federer.getGamePoint().equals(WON) || kygrios.getGamePoint().equals(WON));
    }
}

