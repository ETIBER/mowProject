package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowTest {
    @Nested
    class turn {
        @ParameterizedTest(name = "{index} when initial orientation is {0} and turn {1}, it should set orientation to {2}")
        @CsvSource({"NORTH,LEFT,WEST","NORTH,RIGHT,EAST","EAST,LEFT,NORTH","EAST,RIGHT,SOUTH","SOUTH,LEFT,EAST","SOUTH,RIGHT,WEST","WEST,LEFT,SOUTH","WEST,RIGHT,NORTH"})
        void itShouldSetOrientation(Orientation initialOrientation, Direction direction, Orientation finalOrientation) {
            Mow mow = new Mow(initialOrientation);
            mow.turn(direction);
            assertEquals(finalOrientation, mow.getOrientation());
        }
    }
}
