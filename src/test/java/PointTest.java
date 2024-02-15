import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.winside.kata.tennis.entities.GamePoint;
import org.winside.kata.tennis.entities.Player;
import org.winside.kata.tennis.use_case.Point;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.winside.kata.tennis.entities.GamePoint.*;

class PointTest {
    /* Noms des méthodes à revoir :
     * - dans les "deuceSituation" et "advantageSituation", tu rentres dans les deux cas dans la méthode "Point.deuceSituation(...)"
     * - pas forcément assez explicite : tu peux (et c'est même conseillé en clean code) mettre une "phrase descriptive" en nom de méthode de test. Ex: void should_calculate_resulting_score()
     * Tu aurais potentiellement pu réunir tous ces tests en un seul test paramétré prenant en entrée 2 scores initiaux et 2 scores attendus après calcul
     */
    Player federer = new Player("federer");
    Player kygrios = new Player("kygrios");

    @Test
    void deuceSituation() {
        // GIVEN
        federer.setGamePoint(FORTY);
        kygrios.setGamePoint(FORTY);

        // WHEN
        Point.play(federer, kygrios);

        // THEN
        assertEquals(ADVANTAGE, federer.getGamePoint());
    }

    @Test
    void advantageSituation() {
        // GIVEN
        federer.setGamePoint(FORTY);
        kygrios.setGamePoint(ADVANTAGE);

        // WHEN

        Point.play(federer, kygrios);

        // THEN
        assertEquals(FORTY, federer.getGamePoint());
        assertEquals(FORTY, kygrios.getGamePoint());
    }

    @ParameterizedTest
    @MethodSource("gamePointData")
    void gamePointSituation(GamePoint federerScore, GamePoint kygriosScore) {
        // GIVEN
        federer.setGamePoint(federerScore);
        kygrios.setGamePoint(kygriosScore);

        // WHEN
        Point.play(federer, kygrios);

        // THEN
        assertEquals(WON, federer.getGamePoint());
    }

    static Stream<Arguments> gamePointData() {
        return Stream.of(
                Arguments.of(FORTY, ZERO),
                Arguments.of(FORTY, FIFTEEN),
                Arguments.of(FORTY, THIRTY),
                Arguments.of(ADVANTAGE, FORTY)
        );
    }


    @ParameterizedTest
    @MethodSource("initialPoints")
    void should_calculate_resulting_score(GamePoint federerInitial, GamePoint kygriosInitial, GamePoint federerExpected, GamePoint kygriosExpected) {
        // GIVEN
        federer.setGamePoint(federerInitial);
        kygrios.setGamePoint(kygriosInitial);

        // WHEN
        Point.play(federer, kygrios);

        // THEN
        assertEquals(federerExpected, federer.getGamePoint());
        assertEquals(kygriosExpected, kygrios.getGamePoint());
    }

    static Stream<Arguments> initialPoints() {
        return Stream.of(
                Arguments.of(ZERO, ZERO, FIFTEEN, ZERO),
                Arguments.of(ZERO, FIFTEEN, FIFTEEN, FIFTEEN),
                Arguments.of(ZERO, THIRTY, FIFTEEN, THIRTY),
                Arguments.of(ZERO, FORTY, FIFTEEN, FORTY),
                Arguments.of(FIFTEEN, ZERO, THIRTY, ZERO),
                Arguments.of(FIFTEEN, FIFTEEN, THIRTY, FIFTEEN),
                Arguments.of(FIFTEEN, THIRTY, THIRTY, THIRTY),
                Arguments.of(FIFTEEN, FORTY, THIRTY, FORTY),
                Arguments.of(THIRTY, ZERO, FORTY, ZERO),
                Arguments.of(THIRTY, FIFTEEN, FORTY, FIFTEEN),
                Arguments.of(THIRTY, THIRTY, FORTY, THIRTY),
                Arguments.of(THIRTY, FORTY, FORTY, FORTY),
                Arguments.of(FORTY, ZERO, WON, ZERO),
                Arguments.of(FORTY, FIFTEEN, WON, FIFTEEN),
                Arguments.of(FORTY, THIRTY, WON, THIRTY),
                Arguments.of(FORTY, FORTY, ADVANTAGE, FORTY),
                Arguments.of(FORTY, ADVANTAGE, FORTY, FORTY),
                Arguments.of(ADVANTAGE, FORTY, WON, FORTY)
        );
    }
}
