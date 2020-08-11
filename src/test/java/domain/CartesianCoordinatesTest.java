package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CartesianCoordinatesTest {

    @Nested
    class Create {
        @Test
        void create_should_throw_an_error_when_x_invalid() {
            Assertions.assertThrows(NegativeCoordinateException.class, () -> {
                CartesianCoordinates.create(-3, 5);
            });
        }

        @Test
        void create_should_throw_an_error_when_y_invalid() {
            Assertions.assertThrows(NegativeCoordinateException.class, () -> {
                CartesianCoordinates.create(5, -3);
            });
        }
    }

    @Nested
    class AddVector {
        @ParameterizedTest(name = "{index} when coordinates are {0} {1}, and vector is {2} {3} it should return coordinates {4} {5} ")
        @CsvSource({"1,1,0,1,1,2", "1,1,0,1,1,2", "1,3,0,-1,1,2", "1,1,0,-1,1,0", "1,1,-1,-1,0,0"})
        void addVectorShouldReturnNewCartesianCoordinate(int initialX, int initialY, int vectorX, int vectorY, int finalX, int finalY) throws NegativeCoordinateException {
            assertEquals(CartesianCoordinates.create(finalX, finalY), CartesianCoordinates.create(initialX, initialY).addVector(new CartesianVector(vectorX, vectorY)));
        }

        @Test
        void addVectorShouldThrowNegativeCoordinateExceptionWhenFinalCoordinateInvalid() {
            Assertions.assertThrows(NegativeCoordinateException.class, () -> {
                CartesianCoordinates.create(0, 0).addVector(new CartesianVector(0, -1));
            });
        }
    }

    @Nested
    class isIn {
        @Test
        void isInShouldReturnFalseIfXCoordinateBigger() throws NegativeCoordinateException {
            assertFalse(CartesianCoordinates.create(3, 3).isIn(CartesianCoordinates.create(2, 3)));
        }

        @Test
        void isInShouldReturnFalseIfYCoordinateBigger() throws NegativeCoordinateException {
            assertFalse(CartesianCoordinates.create(2, 4).isIn(CartesianCoordinates.create(2, 3)));
        }

        @Test
        void isInShouldTrueFalseIfYAndXCoordinatesSmallerEqual() throws NegativeCoordinateException {
            assertTrue(CartesianCoordinates.create(2, 3).isIn(CartesianCoordinates.create(2, 3)));
        }

        @Test
        void isInShouldTrueFalseIfYAndXCoordinatesSmallerSmaller() throws NegativeCoordinateException {
            assertTrue(CartesianCoordinates.create(1, 1).isIn(CartesianCoordinates.create(2, 3)));
        }
    }

    @Nested
    class Equals {
        @Test
        void equalsShouldReturnTrueWhenXAndYEqual() throws NegativeCoordinateException {
            assertEquals(CartesianCoordinates.create(2, 3), CartesianCoordinates.create(2, 3));
        }

        @Test
        void equalsShouldReturnFalseWhenXAndYNotEqual() throws NegativeCoordinateException {
            assertNotEquals(CartesianCoordinates.create(2, 3), CartesianCoordinates.create(2, 8));
        }
    }
}
