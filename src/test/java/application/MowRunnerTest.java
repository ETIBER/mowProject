package application;

import domain.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;


public class MowRunnerTest {
    @Nested
    class initialize {
        @Test
        void itShouldReturnMowRunnerWithMowAtInitialPosition() throws NegativeCoordinateException {
            CartesianCoordinates upperRightCornerPosition = CartesianCoordinates.create(3, 3);
            CartesianCoordinates initialMowPosition = CartesianCoordinates.create(1, 1);
            LinkedList<Direction> commandList = new LinkedList<>(List.of(Direction.FORWARD, Direction.RIGHT));
            MowConfiguration mowConfiguration = new MowConfiguration(initialMowPosition, Orientation.NORTH, commandList);
            Lawn lawn = Lawn.generate(upperRightCornerPosition);
            MowRunner mowRunner = MowRunner.initialize(lawn, mowConfiguration);
            assertEquals(initialMowPosition, mowRunner.getMowPosition().getPosition());
        }
    }

    @Nested
    class run {
        @Test
        void itShouldCallMoveMowForEachCommands() {
            List<Direction> initialCommandList = List.of(Direction.FORWARD, Direction.RIGHT);
            LinkedList<Direction> commandList = new LinkedList<>(initialCommandList);

            MowComputer mowComputer = mock(MowComputer.class);
            MowRunner mowRunner = new MowRunner(mowComputer, commandList);

            mowRunner.run();

            InOrder inOrder = inOrder(mowComputer);
            inOrder.verify(mowComputer).moveMow(initialCommandList.get(0));
            inOrder.verify(mowComputer).moveMow(initialCommandList.get(1));
        }
    }
}
