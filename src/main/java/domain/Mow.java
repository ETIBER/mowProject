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
            this.orientation = Orientation.getOrientationByOrder((this.orientation.order + 1) % 4);
        else if (direction == Direction.LEFT)
            this.orientation = Orientation.getOrientationByOrder((this.orientation.order + 3) % 4);
    }
}
