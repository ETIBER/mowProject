package domain;

import java.util.HashMap;
import java.util.Map;

public class Lawn {
    private final Map<CartesianCoordinates, Mow> lawnArea;
    private final CartesianCoordinates upperRightCornerPosition;

    protected Lawn(Map<CartesianCoordinates, Mow> lawnArea, CartesianCoordinates upperRightCornerPosition) {
        this.lawnArea = lawnArea;
        this.upperRightCornerPosition = upperRightCornerPosition;
    }

    public static Lawn generate(CartesianCoordinates upperRightCornerPosition) {
        Map<CartesianCoordinates, Mow> lawnArea = new HashMap<>();
        return new Lawn(lawnArea, upperRightCornerPosition);
    }

    public Mow findAt(CartesianCoordinates cartesianCoordinates) throws OutOfLawnException {
        if (!cartesianCoordinates.isIn(this.upperRightCornerPosition)) throw new OutOfLawnException();
        return this.lawnArea.get(cartesianCoordinates);
    }

    public CartesianCoordinates findMow(Mow mow) {
        for (Map.Entry<CartesianCoordinates, Mow> entry : this.lawnArea.entrySet()) {
            if (mow == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public CartesianCoordinates setMowPosition(Mow mow, CartesianCoordinates initialPosition, CartesianVector vector) throws OutOfLawnException, UsedCoordinateException {
        try {
            CartesianCoordinates computedPosition = initialPosition.addVector(vector);
            synchronized (this) {
                if (this.findAt(computedPosition) != null) throw new UsedCoordinateException();
                this.lawnArea.remove(initialPosition);
                this.lawnArea.put(computedPosition, mow);
            }
            return computedPosition;
        } catch (NegativeCoordinateException e) {
            throw new OutOfLawnException();
        }
    }

    public void addMow(CartesianCoordinates initialPosition, Mow mow) throws OutOfLawnException, UsedCoordinateException {
        if (this.findAt(initialPosition) != null) throw new UsedCoordinateException();
        this.lawnArea.put(initialPosition, mow);
    }

    public int getMowQuantity() {
        return lawnArea.size();
    }

    public int getWidth() {
        return upperRightCornerPosition.getX() + 1;
    }

    public int getHeight() {
        return upperRightCornerPosition.getY() + 1;
    }
}
