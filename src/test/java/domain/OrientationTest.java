package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {

    @ParameterizedTest
    @EnumSource(Orientation.class)
    void getOrientationByValue(Orientation orientation) {
        Orientation result = Orientation.getOrientationByOrder(orientation.order);
        assertEquals(orientation, result);
    }
}
