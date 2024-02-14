import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.winside.kata.tennis.entities.GamePoint;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.useCase.Point;

import java.util.stream.Stream;

import static org.winside.kata.tennis.entities.GamePoint.*;

public class PointTest {

    Player federer = new Player("federer");
    Player kygrios = new Player("kygrios");

    @Test
    public void deuceSituation() {
        // GIVEN
        federer.setGamePoint(FORTY);
        kygrios.setGamePoint(FORTY);
        Point point = new Point();

        // WHEN
        point.play(federer, kygrios);

        // THEN
        Assertions.assertEquals(ADVANTAGE, federer.getGamePoint());
    }

    @Test
    public void adventageSituation() {
        // GIVEN
        federer.setGamePoint(FORTY);
        kygrios.setGamePoint(ADVANTAGE);
        Point point = new Point();

        // WHEN

        point.play(federer, kygrios);

        // THEN
        Assertions.assertEquals(FORTY, federer.getGamePoint());
        Assertions.assertEquals(FORTY, kygrios.getGamePoint());
    }

    @ParameterizedTest
    @MethodSource("gamePointData")
    public void gamePointSituation(GamePoint federerScore, GamePoint kygriosScore) {
        // GIVEN
        federer.setGamePoint(federerScore);
        kygrios.setGamePoint(kygriosScore);
        Point point = new Point();

        // WHEN
        point.play(federer, kygrios);

        // THEN
        Assertions.assertEquals(WON, federer.getGamePoint());
    }

    static Stream<Arguments> gamePointData() {
        return Stream.of(
                Arguments.of(FORTY, ZERO),
                Arguments.of(FORTY, FIFTEEN),
                Arguments.of(FORTY, THIRTY),
                Arguments.of(ADVANTAGE, FORTY)

        );
    }
}
