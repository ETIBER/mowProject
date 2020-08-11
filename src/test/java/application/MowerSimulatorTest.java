package application;

import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static domain.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerSimulatorTest {
    @Test
    void runSimulation() throws NegativeCoordinateException {
        List<MowConfiguration> mowConfigurationList = new ArrayList<>();
        LinkedList<Direction> commandListMow1 = new LinkedList<>(List.of(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD));
        LinkedList<Direction> commandListMow2 = new LinkedList<>(List.of(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD));
        mowConfigurationList.add(new MowConfiguration(CartesianCoordinates.create(1, 2), Orientation.NORTH, commandListMow1));
        mowConfigurationList.add(new MowConfiguration(CartesianCoordinates.create(3, 3), Orientation.EAST, commandListMow2));
        MowerSimulator mowerSimulator = MowerSimulator.initialize(CartesianCoordinates.create(5, 5), mowConfigurationList);
        List<MowPosition> mowPositionList = mowerSimulator.execute();
        assertEquals("1 3 N", mowPositionList.get(0).toString());
        assertEquals("5 1 E", mowPositionList.get(1).toString());
    }
}
