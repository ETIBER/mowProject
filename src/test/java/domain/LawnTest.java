package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class LawnTest {
    Mow mockedMow = mock(Mow.class);
    CartesianCoordinates mockedCartesianCoordinates = mock(CartesianCoordinates.class);

    @BeforeEach
    void setUp() {
        reset(mockedMow, mockedCartesianCoordinates);
    }

    @Nested
    class generate {
        int width = 4;
        int height = 3;
        CartesianCoordinates upperRightCornerPosition;

        @BeforeEach
        void setUp() throws NegativeCoordinateException {
            upperRightCornerPosition = CartesianCoordinates.create(width - 1, height - 1);
        }

        @Test
        void generate_shouldReturnALawnWithoutMow() throws NegativeCoordinateException {
            Lawn lawn = Lawn.generate(upperRightCornerPosition);
            assertEquals(0, lawn.getMowQuantity());
        }

        @Test
        void generate_shouldReturnALawnOfCorrectWidth() throws NegativeCoordinateException {
            Lawn lawn = Lawn.generate(CartesianCoordinates.create(width - 1, height - 1));
            assertEquals(width, lawn.getWidth());
        }

        @Test
        void generate_shouldReturnALawnOfCorrectHeight() throws NegativeCoordinateException {
            Lawn lawn = Lawn.generate(CartesianCoordinates.create(width - 1, height - 1));
            assertEquals(height, lawn.getHeight());
        }
    }

    @Nested
    class findAt {
        int width = 4;
        int height = 3;
        CartesianCoordinates upperRightCornerPosition;
        Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
        Lawn lawn;

        @BeforeEach
        void setUp() throws NegativeCoordinateException {
            upperRightCornerPosition = CartesianCoordinates.create(width - 1, height - 1);
        }

        @Test
        void findAt_shouldReturnNullWhenNullAtPosition() throws NegativeCoordinateException, OutOfLawnException {
            lawn = new Lawn(lawnArea, upperRightCornerPosition);
            assertNull(lawn.findAt(CartesianCoordinates.create(0, 0)));
        }

        @Test
        void findAt_shouldReturnMowWhenMowAtPosition() throws NegativeCoordinateException, OutOfLawnException {
            CartesianCoordinates mowPosition = CartesianCoordinates.create(0, 0);
            lawnArea.put(mowPosition, mockedMow);
            lawn = new Lawn(lawnArea, upperRightCornerPosition);
            assertEquals(mockedMow, lawn.findAt(mowPosition));
        }

        @Test
        void findAt_shouldTrowOutOfLawnExceptionWhenCoordinateNotInLawn() {
            lawn = new Lawn(lawnArea, upperRightCornerPosition);
            Assertions.assertThrows(OutOfLawnException.class, () -> {
                lawn.findAt(CartesianCoordinates.create(0, 6));
            });
        }
    }

    @Nested
    class findMow {
        int width = 4;
        int height = 3;
        CartesianCoordinates upperRightCornerPosition;

        @BeforeEach
        void setUp() throws NegativeCoordinateException {
            upperRightCornerPosition = CartesianCoordinates.create(width - 1, height - 1);
        }

        @Test
        void findMow_shouldReturnNullWhenMowNotFound() {
            Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
            Lawn lawn = new Lawn(lawnArea, upperRightCornerPosition);
            assertNull(lawn.findMow(mockedMow));
        }

        @Test
        void findMow_shouldReturnCartesianCoordinatesOfMowWhenFound() throws NegativeCoordinateException {
            Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
            CartesianCoordinates mowCoordinates = CartesianCoordinates.create(2, 2);
            lawnArea.put(mowCoordinates, mockedMow);
            Lawn lawn = new Lawn(lawnArea, upperRightCornerPosition);
            assertEquals(mowCoordinates, lawn.findMow(mockedMow));
        }
    }

    @Nested
    class setMowPosition {
        int width = 4;
        int height = 3;
        CartesianCoordinates upperRightCornerPosition;

        @BeforeEach
        void setUp() throws NegativeCoordinateException {
            upperRightCornerPosition = CartesianCoordinates.create(width - 1, height - 1);
        }

        @Test
        void setMowPosition_shouldThrowAnOutOfLawnExceptionIfAddVectorThrow() throws NegativeCoordinateException {
            Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
            Lawn lawn = new Lawn(lawnArea, upperRightCornerPosition);
            CartesianVector vector = new CartesianVector(0, 1);
            when(mockedCartesianCoordinates.addVector(vector)).thenThrow(NegativeCoordinateException.class);
            Assertions.assertThrows(OutOfLawnException.class, () -> {
                lawn.setMowPosition(mockedMow, mockedCartesianCoordinates, vector);
            });
        }

        @Test
        void setMowPosition_shouldThrowAnOutOfLawnExceptionIfPositionDoesNotExist() throws NegativeCoordinateException {
            Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
            Lawn lawn = new Lawn(lawnArea, upperRightCornerPosition);
            CartesianVector vector = new CartesianVector(0, 1);
            when(mockedCartesianCoordinates.addVector(vector)).thenReturn(CartesianCoordinates.create(10, 10));
            Assertions.assertThrows(OutOfLawnException.class, () -> {
                lawn.setMowPosition(mockedMow, mockedCartesianCoordinates, vector);
            });
        }

        @Test
        void setMowPosition_shouldThrowAnPositionOccupiedExceptionWhenMowInPosition() throws NegativeCoordinateException {
            Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
            Mow otherMow = mock(Mow.class);
            CartesianCoordinates otherMowCoordinates = CartesianCoordinates.create(2, 1);
            lawnArea.put(otherMowCoordinates, otherMow);
            Lawn lawn = new Lawn(lawnArea, upperRightCornerPosition);
            CartesianVector vector = new CartesianVector(0, 1);
            when(mockedCartesianCoordinates.addVector(vector)).thenReturn(otherMowCoordinates);
            Assertions.assertThrows(UsedCoordinateException.class, () -> {
                lawn.setMowPosition(mockedMow, mockedCartesianCoordinates, vector);
            });
        }

        @Test
        void setMowPosition_shouldMoveMowWhenNoErrorOccurred() throws NegativeCoordinateException, OutOfLawnException, UsedCoordinateException {
            Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
            CartesianCoordinates newCoordinates = CartesianCoordinates.create(2, 1);
            lawnArea.put(mockedCartesianCoordinates, mockedMow);
            Lawn lawn = new Lawn(lawnArea, upperRightCornerPosition);
            CartesianVector vector = new CartesianVector(0, 1);
            when(mockedCartesianCoordinates.addVector(vector)).thenReturn(newCoordinates);

            CartesianCoordinates result = lawn.setMowPosition(mockedMow, mockedCartesianCoordinates, vector);

            assertNull(lawnArea.get(mockedCartesianCoordinates));
            assertEquals(mockedMow, lawnArea.get(newCoordinates));
            assertEquals(result, newCoordinates);
        }

    }
}
