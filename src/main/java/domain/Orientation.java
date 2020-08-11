package domain;

import java.util.EnumSet;

public enum Orientation {
    NORTH("N", 0, new CartesianVector(0, 1)),
    SOUTH("S", 2, new CartesianVector(0, -1)),
    WEST("W", 3, new CartesianVector(-1, 0)),
    EAST("E", 1, new CartesianVector(1, 0));

    public final String value;
    public final Integer order;
    public final CartesianVector associatedVector;

    Orientation(String value, Integer order, CartesianVector associatedVector) {
        this.value = value;
        this.order = order;
        this.associatedVector = associatedVector;
    }

    static Orientation getOrientationByOrder(Integer order) {
        return EnumSet.allOf(Orientation.class).stream().filter(orientation -> orientation.order.equals(order)).findFirst().orElseThrow();
    }

    public static Orientation getOrientationByValue(String value) {
        return EnumSet.allOf(Orientation.class).stream().filter(orientation -> orientation.value.equals(value)).findFirst().orElseThrow();
    }
}
