package domain;

import java.util.Queue;

public class MowConfiguration extends MowPosition {

    private final Queue<Direction> commandList;

    public MowConfiguration(CartesianCoordinates initialPosition, Orientation initialOrientation, Queue<Direction> commandList) {
        super(initialPosition, initialOrientation);
        this.commandList = commandList;
    }

    public Orientation getInitialOrientation() {
        return super.getOrientation();
    }

    public Queue<Direction> getCommandList() {
        return commandList;
    }

    public CartesianCoordinates getInitialPosition() {
        return super.getPosition();
    }
}
