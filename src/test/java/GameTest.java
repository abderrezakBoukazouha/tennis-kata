import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.useCase.Game;
import org.winside.kata.tennis.useCase.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.winside.kata.tennis.entities.GamePoint.WON;

public class GameTest {
    @RepeatedTest(20)
    public void gameRulesTest() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");
        Point point = new Point();

        Game game = new Game(federer, kygrios);

        // WHEN
        game.play();

        // THEN
        assertEquals(1, federer.getGameWon() + kygrios.getGameWon());
        assertTrue(game.getPointPlayed() >= 4);
        assertTrue(federer.getGamePoint().equals(WON) || kygrios.getGamePoint().equals(WON));
    }
}

