package domain;

public class MowComputer {
    private final Lawn lawn;
    private final Mow mow;
    private CartesianCoordinates position;

    public MowComputer(Lawn lawn, Mow mow, CartesianCoordinates cartesianCoordinates) {
        this.lawn = lawn;
        this.mow = mow;
        this.position = cartesianCoordinates;
    }

    public static MowComputer initializeMowComputer(Lawn lawn, Mow mow) throws MowNotInLawnException {
        CartesianCoordinates cartesianCoordinates = lawn.findMow(mow);
        if(cartesianCoordinates == null){
            throw new MowNotInLawnException();
        }
        return new MowComputer(lawn,mow,cartesianCoordinates);

    }

    public CartesianCoordinates getPosition() {
        return position;
    }

    public CartesianCoordinates moveMow(Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT ){
            this.mow.turn(direction);
            return this.position;
        }
        try {
            this.position = lawn.setMowPosition(mow,position, mow.getOrientation().associatedVector);
        } catch (OutOfLawnException | UsedCoordinateException ignored) {
        }
        return this.position;

    }
}
