package infrastructure;

import domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class MowerSimulationConfigurationBuilder {
    private final List<MowConfiguration> mowConfigurationList;
    private final String separator = " ";
    private CartesianCoordinates upperRightCornerPosition;

    public MowerSimulationConfigurationBuilder() {
        this.mowConfigurationList = new ArrayList<>();
    }

    public void withUpperRightCornerPosition(String upperRightCornerPosition) throws InvalidLineException {
        String[] coordinates = upperRightCornerPosition.split(separator);
        try {
            this.upperRightCornerPosition = CartesianCoordinates.create(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
        } catch (NegativeCoordinateException e) {
            throw new InvalidLineException("withUpperRightCornerPosition", upperRightCornerPosition);
        }
    }

    public void withMowConfiguration(String mowInitialConfiguration, String mowCommandList) throws InvalidLineException {
        String[] mowInitialConfigurationElements = mowInitialConfiguration.split(separator);
        CartesianCoordinates initialPosition = null;
        try {
            initialPosition = CartesianCoordinates.create(Integer.valueOf(mowInitialConfigurationElements[0]), Integer.valueOf(mowInitialConfigurationElements[1]));
        } catch (NegativeCoordinateException e) {
            throw new InvalidLineException("withMowConfiguration - initialPosition", mowInitialConfiguration);
        }
        Orientation initialOrientation;
        try {
            initialOrientation = Orientation.getOrientationByValue(mowInitialConfigurationElements[2]);
        } catch (NoSuchElementException e) {
            throw new InvalidLineException("withMowConfiguration - orientation", mowInitialConfiguration);
        }
        Queue<Direction> commands;
        try {
            commands = mowCommandList.codePoints()
                    .mapToObj(c -> String.valueOf((char) c)).map(Direction::getDirectionByValue).collect(Collectors.toCollection(PriorityQueue::new));
        } catch (NoSuchElementException e) {
            throw new InvalidLineException("withMowConfiguration - commands", mowCommandList);
        }
        this.mowConfigurationList.add(new MowConfiguration(initialPosition, initialOrientation, commands));
    }

    MowerSimulationConfiguration build() {
        return new MowerSimulationConfiguration(upperRightCornerPosition, mowConfigurationList);
    }
}
