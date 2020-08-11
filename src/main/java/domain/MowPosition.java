package domain;

public class MowPosition {
    private final CartesianCoordinates position;
    private final Orientation orientation;

    public MowPosition(CartesianCoordinates position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public CartesianCoordinates getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", this.position.getX(), this.position.getY(), this.orientation.value);
    }

}
