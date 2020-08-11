package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    @ParameterizedTest
    @EnumSource(Orientation.class)
    void getOrientationByValue(Orientation orientation) {
        Orientation result = Orientation.getOrientationByValue(orientation.value);
        assertEquals(orientation, result);
    }
}
