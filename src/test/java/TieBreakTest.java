import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.use_case.Game;
import org.winside.kata.tennis.use_case.TieBreak;

class TieBreakTest {

    @RepeatedTest(20)
    void tieBreakRulesTest() {
        // GIVEN
        Player federer = new Player("federer");
        Player kygrios = new Player("kygrios");
        Game game = new Game(federer, kygrios);

        TieBreak tieBreak = new TieBreak(game);

        // WHEN
        tieBreak.play();

        // THEN
        Assertions.assertTrue(Math.abs(federer.getTieBreakPointWon() - kygrios.getTieBreakPointWon()) >= 2,
                "Difference should always be superior to two points");
        Assertions.assertTrue(federer.getTieBreakPointWon() + kygrios.getTieBreakPointWon() >= 7,
                "Playing at least 7 points");
    }
}
