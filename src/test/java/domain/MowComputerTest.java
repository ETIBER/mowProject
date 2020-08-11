package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MowComputerTest {
    Lawn mockedLawn = mock(Lawn.class);
    Mow mockedMaw = mock(Mow.class);
    CartesianCoordinates cartesianCoordinates;

    @BeforeEach
    void setUp() throws NegativeCoordinateException {
        reset(mockedLawn, mockedMaw);
        cartesianCoordinates = CartesianCoordinates.create(2, 8);
    }

    @Nested
    class initializeMowComputer {
        @Test
        void initializeMowComputer_shouldTrowAnErrorWhenMowNotFoundInLawn() {
            Lawn mockedLawn = mock(Lawn.class);
            Mow mockedMaw = mock(Mow.class);
            when(mockedLawn.findMow(mockedMaw)).thenReturn(null);
            Assertions.assertThrows(MowNotInLawnException.class, () -> {
                MowComputer.initializeMowComputer(mockedLawn, mockedMaw);
            });
        }

        @Test
        void initializeMowComputer_shouldReturnMowComputerWhenMowIsFounded() throws MowNotInLawnException {

            when(mockedLawn.findMow(mockedMaw)).thenReturn(cartesianCoordinates);
            MowComputer mowComputer = MowComputer.initializeMowComputer(mockedLawn, mockedMaw);
            assertEquals(mowComputer.getPosition(), cartesianCoordinates);
        }
    }

    @Nested
    class MoveMow {
        @Test
        void whenDirectionIsLeft_ItShouldTurnMow() {
            MowComputer mowComputer = new MowComputer(mockedLawn, mockedMaw, cartesianCoordinates);
            mowComputer.moveMow(Direction.LEFT);
            verify(mockedMaw).turn(Direction.LEFT);
        }

        @Test
        void whenDirectionIsRight_ItShouldTurnMow() {
            MowComputer mowComputer = new MowComputer(mockedLawn, mockedMaw, cartesianCoordinates);
            mowComputer.moveMow(Direction.RIGHT);
            verify(mockedMaw).turn(Direction.RIGHT);

        }
        @Nested
        class whenDirectionIsForward {

            @Test
            void itShouldTryToMove() throws NegativeCoordinateException, OutOfLawnException, UsedCoordinateException {
                when(mockedMaw.getOrientation()).thenReturn(Orientation.NORTH);
                CartesianCoordinates initialPosition = CartesianCoordinates.create(1, 1);
                CartesianVector expectedVector = Orientation.NORTH.associatedVector;
                MowComputer mowComputer = new MowComputer(mockedLawn, mockedMaw, initialPosition);
                mowComputer.moveMow(Direction.FORWARD);
                verify(mockedLawn).setMowPosition(mockedMaw, initialPosition, expectedVector);
            }
            @Test
            void itShouldStayInTheSamePositionIfSetMowPositionThrowOutOfLawnException() throws NegativeCoordinateException, OutOfLawnException, UsedCoordinateException {
                when(mockedMaw.getOrientation()).thenReturn(Orientation.NORTH);
                CartesianCoordinates initialPosition = CartesianCoordinates.create(1, 1);
                CartesianVector expectedVector = Orientation.NORTH.associatedVector;
                MowComputer mowComputer = new MowComputer(mockedLawn, mockedMaw, initialPosition);
                when(mockedLawn.setMowPosition(mockedMaw, initialPosition, expectedVector)).thenThrow(OutOfLawnException.class);
                CartesianCoordinates newCoordinates = mowComputer.moveMow(Direction.FORWARD);
                assertEquals(initialPosition, newCoordinates);
            }
            @Test
            void itShouldStayInTheSamePositionIfSetMowPositionThrowUsedCoordinateException() throws NegativeCoordinateException, OutOfLawnException, UsedCoordinateException {
                when(mockedMaw.getOrientation()).thenReturn(Orientation.NORTH);
                CartesianCoordinates initialPosition = CartesianCoordinates.create(1, 1);
                CartesianVector expectedVector = Orientation.NORTH.associatedVector;
                MowComputer mowComputer = new MowComputer(mockedLawn, mockedMaw, initialPosition);
                when(mockedLawn.setMowPosition(mockedMaw, initialPosition, expectedVector)).thenThrow(UsedCoordinateException.class);
                CartesianCoordinates newCoordinates = mowComputer.moveMow(Direction.FORWARD);
                assertEquals(initialPosition, newCoordinates);
            }
            @Test
            void itShouldSetThePositionIfSetMowPositionSucceed() throws NegativeCoordinateException, OutOfLawnException, UsedCoordinateException {
                when(mockedMaw.getOrientation()).thenReturn(Orientation.NORTH);
                CartesianCoordinates initialPosition = CartesianCoordinates.create(1, 1);
                CartesianCoordinates newPosition = CartesianCoordinates.create(1, 2);
                CartesianVector expectedVector = Orientation.NORTH.associatedVector;
                MowComputer mowComputer = new MowComputer(mockedLawn, mockedMaw, initialPosition);
                when(mockedLawn.setMowPosition(mockedMaw, initialPosition, expectedVector)).thenReturn(newPosition);
                CartesianCoordinates newCoordinates = mowComputer.moveMow(Direction.FORWARD);
                assertEquals(newPosition, mowComputer.getPosition());
                assertEquals(newPosition, newCoordinates);
            }
        }
    }
}
