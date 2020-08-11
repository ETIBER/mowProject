package domain;

public class Mow {
    private Orientation orientation;

    public Mow(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void turn(Direction direction) {
        if (direction == Direction.RIGHT)
            this.orientation = Orientation.getOrientationByValue((this.orientation.value + 1) % 4);
        else if (direction == Direction.LEFT)
            this.orientation = Orientation.getOrientationByValue((this.orientation.value + 3) % 4);
    }
}
